package com.vipCodeError.coronatrackerwidget.CoronaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CoronaJsonTest {

    @Test
    public void CoronaTestCase() throws IOException, JSONException {
        String jsonText = getJson();

        JSONObject obj = new JSONObject(jsonText);
//        JSONArray obj_1 = obj.getJSONArray("cases_time_series");
//
//        JSONObject jsonObject = obj_1.getJSONObject(obj_1.length() -1);
//        System.out.println("Confirmed :: "+jsonObject.get("totalconfirmed"));
//        System.out.println("Deceased :: "+jsonObject.get("totaldeceased"));
//        System.out.println("Recovered :: "+jsonObject.get("totalrecovered"));
//        System.out.println("Date :: " +jsonObject.get("date"));

        // statewise

//        JSONArray obj_state = obj.getJSONArray("statewise");

        JSONArray stateWise = obj.getJSONArray("statewise");

        JSONObject firstObj = stateWise.getJSONObject(0);
        JSONObject deltaObj = firstObj.getJSONObject("delta");

        //state details
        JSONObject jsonStateObject = stateWise.getJSONObject(0);
        assert (((String) firstObj.get("active")).equals("1879"));
        assert (((String) firstObj.get("confirmed")).equals("2113"));
        assert (((String) firstObj.get("deaths")).equals("62"));
        assert (((String) firstObj.get("recovered")).equals("172"));

        System.out.println((String) jsonStateObject.get("active"));
        System.out.println((String) jsonStateObject.get("confirmed"));
        System.out.println((String) jsonStateObject.get("deaths"));
        System.out.println((String) jsonStateObject.get("recovered"));
//        dataLoad[0] = (String) jsonStateObject.get("active");
//        dataLoad[1] = (String) jsonStateObject.get("confirmed");
//        dataLoad[2] = (String) jsonStateObject.get("deaths");
//        dataLoad[3] = (String) jsonStateObject.get("recovered");
//        dataLoad[4] = (String) jsonStateObject.get("state");

        //delta
        System.out.println(String.valueOf(deltaObj.getInt("active")));
        System.out.println(String.valueOf(deltaObj.getInt("confirmed")));
        System.out.println(String.valueOf(deltaObj.getInt("deaths")));
        System.out.println(String.valueOf(deltaObj.getInt("recovered")));
//        dataLoad[5] = String.valueOf(deltaObj.getInt("active"));
//        dataLoad[6] = String.valueOf(deltaObj.getInt("confirmed"));
//        dataLoad[7] = String.valueOf(deltaObj.getInt("deaths"));
//        dataLoad[8] = String.valueOf(deltaObj.getInt("recovered"));
    }


    public String getJson() throws IOException {
        File file = new File("/home/vipul/AndroidProjects/CoronaTrackerWidget/app/src/test/" +
                "java/com/vipCodeError/coronatrackerwidget/CoronaData/corono.json");

        FileInputStream fis=new FileInputStream(file);     //opens a connection to an actual file
        // System.out.println(fis);

        int r=0;
        StringBuilder jsonText = new StringBuilder();
        while((r=fis.read())!=-1)
        {
            jsonText.append((char) r);
            // System.out.print((char)r); //prints the content of the file
        }

        // System.out.println(jsonText);
        return String.valueOf(jsonText);
    }

}
