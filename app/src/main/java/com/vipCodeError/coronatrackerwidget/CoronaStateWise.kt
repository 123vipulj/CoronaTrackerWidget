package com.vipCodeError.coronatrackerwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import com.vipCodeError.coronatrackerwidget.CoronaData.CoronaStateFetcher
import com.vipCodeError.coronatrackerwidget.WidgetAdapter.WidgetServiceClass

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

            val widgetIds: IntArray = AppWidgetManager.getInstance(context).getAppWidgetIds(
                ComponentName(context!!, CoronaStateWise::class.java)
            )

            AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(widgetIds, R.id.stateWiseList)

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
    val arrListState = coronaStateFetcher.dataLoad; // get Json String
    val remoteViews = RemoteViews(context.packageName, R.layout.corona_state_wise)

    val intent = Intent(context, WidgetServiceClass::class.java)
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
    intent.putExtra("parc_arrayList", arrListState); // put json string into putextras

    // set pending intent on refresh button
    val intentRefData = Intent(context, CoronaStateWise::class.java)
    intentRefData.action = "REFRESH_DATA"
    intentRefData.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    val pendingIntent = PendingIntent.getBroadcast(context,
        0, intentRefData, PendingIntent.FLAG_CANCEL_CURRENT)

    remoteViews.setOnClickPendingIntent(R.id.refresh_data, pendingIntent);

    // bind adapter to listview
    remoteViews.setRemoteAdapter(R.id.stateWiseList, intent)

    // Instruct the widget manager to update the widget
    // appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.stateWiseList)
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)

}

