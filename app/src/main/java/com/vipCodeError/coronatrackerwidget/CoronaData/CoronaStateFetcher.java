package com.vipCodeError.coronatrackerwidget.CoronaData;

import com.vipCodeError.coronatrackerwidget.Helper.StateWiseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CoronaStateFetcher implements Runnable {
    private String[] dataLoad = new String[9];
    private ArrayList<StateWiseHelper>  stateWiseHelper = new ArrayList<>();

    private String connectServer() throws IOException {

        return Jsoup.connect("https://api.covid19india.org/data.json")
                .ignoreContentType(true).execute().body();
    }


    private void receiveData() throws IOException, JSONException {
        String jsonText = connectServer();

        JSONObject obj = new JSONObject(jsonText);
        JSONArray stateWise = obj.getJSONArray("statewise");

//        JSONObject firstObjInsideStateWise = stateWise.getJSONObject(0);
//        JSONObject deltaObj = firstObjInsideStateWise.getJSONObject("delta");
      
        //state details
        for (int i=1; i< stateWise.length() - 1; i++){
            JSONObject jsonStateObject = stateWise.getJSONObject(i);
            dataLoad[0] = (String) jsonStateObject.get("active");
            dataLoad[1] = (String) jsonStateObject.get("confirmed");
            dataLoad[2] = (String) jsonStateObject.get("deaths");
            dataLoad[3] = (String) jsonStateObject.get("recovered");
            dataLoad[4] = (String) jsonStateObject.get("state");

            //delta
            // dataLoad[5] = String.valueOf(jsonStateObject.getInt("active"));
            dataLoad[5] = String.valueOf(jsonStateObject.get("deltaconfirmed"));
            dataLoad[6] = String.valueOf(jsonStateObject.get("deltadeaths"));
            dataLoad[7] = String.valueOf(jsonStateObject.get("deltarecovered"));

            // store it in arrayList
            stateWiseHelper.add(new StateWiseHelper(dataLoad[0],dataLoad[1],dataLoad[2],dataLoad[3],
                    dataLoad[5],dataLoad[6],dataLoad[7], dataLoad[4]));
        }

    }

    @Override
    public void run() {
        try {
            receiveData();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDataLoad() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i< stateWiseHelper.size();i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("confirmed", stateWiseHelper.get(i).getmConfirmed());
            jsonObject.put("active", stateWiseHelper.get(i).getmActive());
            jsonObject.put("recovered", stateWiseHelper.get(i).getmRecovered());
            jsonObject.put("deaths", stateWiseHelper.get(i).getmDeaths());
            jsonObject.put("state", stateWiseHelper.get(i).getState());
            //delta

            jsonObject.put("deltaconfirmed", stateWiseHelper.get(i).getmDeltaConfirmed());
            jsonObject.put("deltarecovered", stateWiseHelper.get(i).getmDeltaRecovered());
            jsonObject.put("deltadeaths", stateWiseHelper.get(i).getmDeltaDeaths());

            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }
}
