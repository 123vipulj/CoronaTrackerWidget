package com.vipCodeError.coronatrackerwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import com.vipCodeError.coronatrackerwidget.CoronaData.CoronaFetcher
import com.vipCodeError.coronatrackerwidget.CoronaData.CoronaStateFetcher
import com.vipCodeError.coronatrackerwidget.WidgetList.WidgetServiceClass
import org.json.JSONArray
import java.io.Serializable

/**
 * Implementation of App Widget functionality.
 */
class CoronaStateWise : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Toast.makeText(context, "Widget updated", Toast.LENGTH_SHORT).show();
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidgetState(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action.equals("REFRESH_DATA")){

            val coronaStateFetcher = CoronaStateFetcher()
            val thradsT = Thread(coronaStateFetcher);
            thradsT.start()
            thradsT.join()

            // Construct the RemoteViews object
            val ids = intent?.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,0);

            val arrListState = coronaStateFetcher.dataLoad;
            val remoteViews = RemoteViews(context?.packageName, R.layout.corona_state_wise)
            val intent1 = Intent(context, WidgetServiceClass::class.java)
            intent1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, ids);
            intent1.putExtra("parc_arrayList", arrListState);

            //set adapter on listview
            remoteViews.setRemoteAdapter(R.id.stateWiseList, intent1) // bind adapter to listview
            AppWidgetManager.getInstance(context).updateAppWidget(ids!!, remoteViews)

//            // Instruct the widget manager to update the widget
//            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
            Toast.makeText(context, "Data Updated !!!", Toast.LENGTH_SHORT).show();

        }
        Log.e("onReceive()", (intent!!.action).toString());
    }
}

internal fun updateAppWidgetState(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val coronaStateFetcher = CoronaStateFetcher()
    val thradsT = Thread(coronaStateFetcher);
    thradsT.start()
    thradsT.join()

    // Construct the RemoteViews object
    val arrListState = coronaStateFetcher.dataLoad;
    val remoteViews = RemoteViews(context.packageName, R.layout.corona_state_wise)
    val intent = Intent(context, WidgetServiceClass::class.java)
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
    intent.putExtra("parc_arrayList", arrListState);
    // set pending intent on refresh button
    val intentRef = Intent(context, CoronaStateWise::class.java)
    intentRef.action = "REFRESH_DATA"
    intentRef.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    val pendingIntent = PendingIntent.getBroadcast(context,
        0, intentRef, PendingIntent.FLAG_CANCEL_CURRENT)

    remoteViews.setOnClickPendingIntent(R.id.refresh_data, pendingIntent);

    //set adapter on listview
    remoteViews.setRemoteAdapter(R.id.stateWiseList, intent) // bind adapter to listview

    // Instruct the widget manager to update the widget
    // appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.stateWiseList)
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)

}

