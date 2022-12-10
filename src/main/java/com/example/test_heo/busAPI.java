package com.example.test_heo;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class busAPI {

    //http://moapp-map.com
    //http://localhost:8080/navigation
    @GetMapping("navigation")
    public Integer navigation() throws IOException {
//        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String clientId = "z17ahs6qnw";
        String clientSecret = "9ttttQgaR6XracSDpaKFdRSxweFTue2kb6QIfmAb";

//            System.out.println("주소를 입력해주세요 : ");
//
//            String address = io.readLine();
//            String addr = URLEncoder.encode(address, "UTF-8");
        //35.8852186, 128.6146397 경대 정문
        //35.877557, 128.629909 동대구역복합 환승센터앞
        String start = URLEncoder.encode("128.6146397,35.8852186", "UTF-8"); //경대정문
        String goal = URLEncoder.encode("128.629909,35.877557", "UTF-8"); // 동대구역
        String option = URLEncoder.encode("trafast", "UTF-8"); // 옵션


        // Geocoding 개요에 나와있는 API URL 입력.
        //String apiURL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start={start}&goal={goal}&option={option}";	// JSON
        String apiURL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start=128.6146397,35.8852186&goal=128.629909,35.877557&option=trafast";	// JSON

        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-type", "application/json"); //여기 추가부분임

        // Geocoding 개요에 나와있는 요청 헤더 입력.
        con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
        con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

        // 요청 결과 확인. 정상 호출인 경우 200
        int responseCode = con.getResponseCode();

        BufferedReader rd;

        if (responseCode == 200) {
            rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String inputLine;

        StringBuffer sb = new StringBuffer();

        while((inputLine = rd.readLine()) != null) {
            sb.append(inputLine);
        }
        rd.close();
        con.disconnect();







        JSONObject jObjects = new JSONObject(sb.toString());
        int time = 0;

        JSONArray pre_trafast = jObjects.getJSONObject("route").getJSONArray("trafast");

        JSONObject trafast = pre_trafast.getJSONObject(0);
        JSONObject summary = trafast.getJSONObject("summary");
        time = summary.getInt("duration");
//        Map<Integer, Integer> buses = new HashMap<>();
//        buses.put(summary.getInt("distance"), summary.getInt("duration"));

//        for(int i=0; i< pre_trafast.length(); i++)
//        {
//            JSONObject obj = pre_trafast.getJSONObject(i);
//            System.out.println(obj.getJSONObject("summary"));
//        }


        System.out.println(time);
        return time;


//        return sb.toString(); // 일단임시
    }

}