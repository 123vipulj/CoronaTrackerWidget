package com.vipCodeError.coronatrackerwidget.CoronaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.ParseException;


public class CoronaIndiaFetcher implements Runnable{
    private String[] dataLoad = new String[4];

    private String connectServer() throws IOException {

        return Jsoup.connect("https://api.covid19india.org/data.json")
                .ignoreContentType(true).execute().body();
    }


    private void receiveData() throws IOException, JSONException, ParseException {
        String jsonText = connectServer(); // get json data

        JSONObject obj = new JSONObject(jsonText);
        JSONArray mStateWise = obj.getJSONArray("statewise");

        JSONObject firstArray = mStateWise.getJSONObject(0);

        dataLoad[0] = (String) firstArray.get("confirmed");
        dataLoad[1] = (String) firstArray.get("deaths");
        dataLoad[2] = (String) firstArray.get("recovered");
        dataLoad[3] = (String) firstArray.get("lastupdatedtime");

    }


    @Override
    public void run() {
        try {
            receiveData();
        } catch (IOException | JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    public String[] getDataLoad(){
        return dataLoad;
    }
}
