package com.haka.stepup.controllers;

import com.haka.stepup.db.DeviceData;
import com.haka.stepup.db.DeviceDataDAO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FitBitController {

    @Autowired
    private DeviceDataDAO deviceDataDAO;

    @Autowired
    OAuth2RestTemplate fitbitOAuthRestTemplate;

    @RequestMapping("fitBit/getStepsFromDb")
    public int getStepsFromDb(DateTime from, DateTime to){
        return 0;
    }

    @RequestMapping("fitBit/setDataToDatabase")
    public int setDataToDatabase(){

        try {
            Map result = fitbitOAuthRestTemplate.getForObject("https://api.fitbit.com/1/user/-/activities/steps/date/2018-04-29/1w.json", Map.class);
            ArrayList<Map> allDays = (ArrayList) result.get("activities-steps");
            int totalStepCount = 0;
            for (Map day: allDays){
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
                DateTime dt = formatter.parseDateTime(day.get("dateTime").toString());
                deviceDataDAO.insertNewData(dt, Integer.parseInt(day.get("value").toString()), 1L);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return deviceDataDAO.getAllData().size();
    }

//    @RequestMapping("/lifetime-activity")
//    public LifetimeActivity lifetimeActivity() {
//        LifetimeActivity lifetimeActivity = new LifetimeActivity();
//
//        try {
//            Map result = fitbitOAuthRestTemplate.getForObject(fitbitActivitiesUri, Map.class);
//            ArrayList<Map> allDays = (ArrayList) result.get("activities-steps");
//            int totalStepCount = 0;
//            for (Map day: allDays){
//                Object value = day.get("value");
//                if (value != null) {
//                    totalStepCount  += Integer.parseInt(value.toString());
//                }
//            }
//            lifetimeActivity.setSteps(totalStepCount);
//        }
//        catch(Exception e) {
//            System.out.println(e);
//            lifetimeActivity = new LifetimeActivity();
//        }
//
//        return lifetimeActivity;
//    }


}
