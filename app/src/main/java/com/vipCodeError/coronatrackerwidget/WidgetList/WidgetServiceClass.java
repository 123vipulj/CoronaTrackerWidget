package com.vipCodeError.coronatrackerwidget.WidgetList;

import android.content.Intent;
import android.widget.RemoteViewsService;

import org.json.JSONException;

public class WidgetServiceClass extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        RemoteViewsFactory remoteViewsFactory = null;
        try {
            remoteViewsFactory =  (new WidgetRemoteViewsFactory(this.getApplicationContext(), intent));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return remoteViewsFactory;
    }
    
}
