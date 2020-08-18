package com.vipCodeError.coronatrackerwidget.WidgetAdapter;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.vipCodeError.coronatrackerwidget.CoronaData.CoronaStateFetcher;
import com.vipCodeError.coronatrackerwidget.Helper.WidgetStateWiseHelper;
import com.vipCodeError.coronatrackerwidget.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory
{
    private Context context = null;
    private int appWidgetId;

    private ArrayList<WidgetStateWiseHelper> widgetList;
    private ArrayList<WidgetStateWiseHelper> stateWiseHelpers;

    public WidgetRemoteViewsFactory(Context context, Intent intent) throws JSONException {
        this.context = context;
        widgetList = new ArrayList<>();
        stateWiseHelpers = new ArrayList<>();
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        String jsonTxt = intent.getStringExtra("parc_arrayList");

        // store data into stateWiseHelper arrayList
        storeArrayList(jsonTxt);

        Log.d("AppWidgetId", String.valueOf(appWidgetId));

    }

    private void updateWidgetListView()
    {
        widgetList.clear();
        widgetList.addAll(stateWiseHelpers);
    }

    @Override
    public int getCount()
    {
        return stateWiseHelpers.size();
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public RemoteViews getLoadingView()
    {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position)
    {
        Log.d("WidgetCreatingView", "WidgetCreatingView");
        RemoteViews remoteView = new RemoteViews(context.getPackageName(),
                R.layout.list_view_items);

        Log.d("Loading", widgetList.get(position).getmActive());
        remoteView.setTextViewText(R.id.confirmPerson, stateWiseHelpers.get(position).getmConfirm());
        remoteView.setTextViewText(R.id.activePerson, stateWiseHelpers.get(position).getmActive());
        remoteView.setTextViewText(R.id.recoveredPerson, stateWiseHelpers.get(position).getmRecovered());
        remoteView.setTextViewText(R.id.deceasedPerson, stateWiseHelpers.get(position).getmDeath());
        remoteView.setTextViewText(R.id.state_name, stateWiseHelpers.get(position).getmState());

        remoteView.setTextViewText(R.id.delta_confirm, stateWiseHelpers.get(position).getmConfirmDelta());
        remoteView.setTextViewText(R.id.delta_recover, stateWiseHelpers.get(position).getmRecoveredDelta());
        remoteView.setTextViewText(R.id.delta_deceased, stateWiseHelpers.get(position).getmDeathDelta());

        return remoteView;
    }

    @Override
    public int getViewTypeCount()
    {
        return 1;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public void onCreate()
    {
        updateWidgetListView();
    }

    @Override
    public void onDataSetChanged()
    {
        try {
            CoronaStateFetcher coronaStateFetcher = new CoronaStateFetcher();
            Thread fetchThread = new Thread(coronaStateFetcher);
            fetchThread.start();
            fetchThread.join();
            stateWiseHelpers.clear();

            storeArrayList(coronaStateFetcher.getDataLoad());
        } catch (JSONException | InterruptedException e) {
            e.printStackTrace();
        }
        updateWidgetListView();
    }

    @Override
    public void onDestroy()
    {
        stateWiseHelpers.clear();
    }

    public void storeArrayList(String jsonTxt) throws JSONException {
        assert jsonTxt != null;
        JSONArray jsonArray = new JSONArray(jsonTxt);
        for (int i=0;i< jsonArray.length(); i++){
            JSONObject stateList = (JSONObject) jsonArray.get(i);
            String confirmPerson = (String) stateList.get("confirmed");
            String activePerson = (String) stateList.get("active");
            String recoveredPerson = (String) stateList.get("recovered");
            String deathPerson = (String) stateList.get("deaths");
            String statePerson = (String) stateList.get("state");

            String confirmDelta = (String) stateList.get("deltaconfirmed");
            String recoveredDelta = (String) stateList.get("deltarecovered");
            String deceasedDelta = (String) stateList.get("deltadeaths");

            stateWiseHelpers.add(new WidgetStateWiseHelper(confirmPerson, activePerson, recoveredPerson, deathPerson, statePerson, confirmDelta, recoveredDelta, deceasedDelta));
        }
    }
}