package com.vipCodeError.coronatrackerwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.vipCodeError.coronatrackerwidget.CoronaData.CoronaFetcher
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class CoronaWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    val coronaFetch = CoronaFetcher()
    val thradsT = Thread(coronaFetch);
    thradsT.start()
    thradsT.join()

    val coronaDataFetcher =  listOf(coronaFetch.dataLoad)

    val confirmCases = (coronaDataFetcher[0][0].toInt())
//            coronaDataFetcher[0][4].toInt()).toString();


    val recoveredNow = (coronaDataFetcher[0][2].toInt())
            //+ coronaDataFetcher[0][5].toInt()).toString();
    val totalActive  = (confirmCases.toInt() - recoveredNow.toInt()).toString();


    //val last_updated = coronaDataFetcher[0][6]
    val time_ago = coronaDataFetcher[0][3];

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.corona_widget)
    views.setTextViewText(R.id.confirm_cases, confirmCases.toString())
    views.setTextViewText(R.id.total_recover, recoveredNow.toString())
    views.setTextViewText(R.id.total_active, totalActive)
    //views.setTextViewText(R.id.updated_ago, last_updated)
    views.setTextViewText(R.id.time_ago, time_ago);


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}