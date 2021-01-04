package com.vipCodeError.coronatrackerwidget.CoronaData;

import org.junit.Test;

import java.io.IOException;

public class CoronaFetcherTest extends CoronaIndiaFetcher {

    @Test
    public void testTestConnectServer() throws IOException {
//        String testDoc = connectServer();
//        System.out.println(testDoc);
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        System.out.println( sdf.format(cal.getTime()) );
        String dummy_str = "02/04/2020 16:02:24";
        System.out.println(dummy_str.split(" ")[1]);

    }


}