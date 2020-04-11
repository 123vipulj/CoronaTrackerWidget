package com.vipCodeError.coronatrackerwidget.CoronaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CoronaFetcher implements Runnable{
    private String[] dataLoad = new String[4];

    private String connectServer() throws IOException {

        return Jsoup.connect("https://api.covid19india.org/data.json")
                .ignoreContentType(true).execute().body();
    }


    private void receiveData() throws IOException, JSONException, ParseException {
        String jsonText = connectServer();

        JSONObject obj = new JSONObject(jsonText);
        JSONArray obj_1 = obj.getJSONArray("statewise");

        JSONObject jsonObject = obj_1.getJSONObject(0);

//        JSONArray obj_2 = obj.getJSONArray("key_values");
//        JSONObject now_cases = obj_2.getJSONObject(0);

        dataLoad[0] = (String) jsonObject.get("confirmed");
        dataLoad[1] = (String) jsonObject.get("deaths");
        dataLoad[2] = (String) jsonObject.get("recovered");
        dataLoad[3] = (String) jsonObject.get("lastupdatedtime");
//        dataLoad[4] = now_cases.getString("confirmeddelta");
//        dataLoad[5] = now_cases.getString("recovereddelta");
//        String time_updated = now_cases.getString("lastupdatedtime");
//        String[] split_time = time_updated.split(" ");

//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        System.out.println( sdf.format(cal.getTime()) +" "+split_time[1] );
//        long difference = sdf.parse(sdf.format(cal.getTime())).getTime() - sdf.parse(split_time[1]).getTime();
//        System.out.println(difference);
//        if ((difference / (60 * 60 * 1000) % 24) < 1){
//            dataLoad[6] = String.valueOf(difference / (60 * 1000) % 60) + " minutes ago";
//        }else {
//            dataLoad[6] = String.valueOf(difference / (60 * 60 * 1000) % 24) + " hours ago";
//            System.out.println(difference / (60 * 60 * 1000) % 24);
//        }

//        dataLoad[7] = split_time[0];

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
