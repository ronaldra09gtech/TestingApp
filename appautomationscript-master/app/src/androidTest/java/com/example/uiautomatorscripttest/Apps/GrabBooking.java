package com.example.uiautomatorscripttest.Apps;


import android.graphics.Point;
import android.graphics.Rect;
import android.os.health.TimerStat;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.util.Log;

import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;


import com.example.uiautomatorscripttest.ExampleInstrumentedTest;
import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import com.example.uiautomatorscripttest.Utility.DataHolder;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.assertEquals;

public class GrabBooking {

    private UiDevice device;
    Timestamp homeElementsAppearTime,foodPageClickTime,foodPageLoadTime,martPageClickTime,martPageLoadTime,carPageClickTime,carPageLoadTime,faireLoadTime,placeLoadTime,clickConfirmTime
            ,searchPlaceTime;

    public int testRun(UiDevice device) {
        boolean runTest = true;
        this.device = device;
        try {
            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Home element");
                return 1;
            }
            runTest = gotoCar();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Car");
                return 1;
            }
            runTest = carPageLoaded();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Load Car Page");
                return 1;
            }
            runTest = clickWhereTo();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Where To");
                return 1;
            }
            runTest = clickPlace();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Place");
                return 1;
            }
            runTest = placeLoaded();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Load Place");
                return 1;
            }
            runTest = confirm();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Confirm");
                return 1;
            }
            runTest = fareLoaded();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Load Fare");
                return 1;
            }
            runTest = kpiCalculation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Calculate KPI's");
                return 1;
            }

            tearDown();
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name, "Error in Running App");
        }
        return 0;

    }

    private String getCommand(String appPackage) {
        return "am force-stop " + appPackage;
    }

    public void tearDown() {
        try {
            device.executeShellCommand(getCommand(GlobalVariables.Waze_Package));
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in closing the app");
        }
    }
    public boolean homePage() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector firstElementSelector = new UiSelector().textContains("Points");
                UiObject firstElementObject = device.findObject(firstElementSelector);

                UiSelector secElementSelector = new UiSelector().resourceId("com.grabtaxi.passenger:id/title");
                UiObject secElementObject = device.findObject(secElementSelector);

                UiSelector thirdElementSelector = new UiSelector().resourceId("com.grabtaxi.passenger:id/tile_icon");
                UiObject thirdElementObject = device.findObject(thirdElementSelector);

                if (firstElementObject.exists()||secElementObject.exists() || thirdElementObject.exists())
                {
                    homeElementsAppearTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Home Elements Appear Time:" + homeElementsAppearTime);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Home Page Elements");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return false;
        }
    }

    public boolean gotoCar(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Car").resourceId("com.grabtaxi.passenger:id/tile_text");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                carPageClickTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Car Page Successfully Time:" + carPageClickTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Clicking Car Page");
        }
        return value;
    }

    public boolean carPageLoaded(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Trying to load car");
                UiSelector firstElementSelector = new UiSelector().descriptionContains("Google Map");
                UiObject firstElementObject = device.findObject(firstElementSelector);

                UiSelector secondElementSelector = new UiSelector().resourceId("com.grabtaxi.passenger:id/tv_drop_off_selection");
                UiObject secondElementObject = device.findObject(secondElementSelector);

                UiSelector acceptElementSelector = new UiSelector().textContains("I Accept");
                UiObject acceptElementObject = device.findObject(acceptElementSelector);

                UiSelector dismissElementSelector = new UiSelector().textContains("Dismiss");
                UiObject dismissElementObject = device.findObject(dismissElementSelector);

                UiSelector laterElementSelector = new UiSelector().textContains("Maybe Later");
                UiObject laterElementObject = device.findObject(laterElementSelector);

                UiSelector bannerElementSelector = new UiSelector().resourceId("com.grabtaxi.passenger:id/ivBanner");
                UiObject bannerElementObject = device.findObject(bannerElementSelector);

                Log.d(GlobalVariables.Tag_Name,"Trying.....");
                if (bannerElementObject.exists())
                {
                    Log.d(GlobalVariables.Tag_Name,"here");
                    device.pressBack();
                }
                else if (acceptElementObject.exists())
                {
                    acceptElementObject.click();
                }
                else if (dismissElementObject.exists())
                {
                    dismissElementObject.click();
                }
                else if (laterElementObject.exists())
                {
                    laterElementObject.click();
                }
                else if(firstElementObject.exists() || secondElementObject.exists())
                {
                    Log.d(GlobalVariables.Tag_Name,"Trying.....");
                    carPageLoadTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Car Page Displayed Successfully Time:" + carPageLoadTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Displaying Car");
        }
        return value;
    }

    public boolean clickWhereTo(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector firstElementSelector = new UiSelector().textContains("Where to?");
                UiObject firstElementObject = device.findObject(firstElementSelector);

                Log.d(GlobalVariables.Tag_Name,"Trying to Click Where to");
                if(firstElementObject.exists()){
                    firstElementObject.click();

                    UiSelector secElementSelector = new UiSelector().descriptionContains("Select location from map");
                    UiObject secElementObject = device.findObject(secElementSelector);

                    UiSelector thirdElementSelector = new UiSelector().resourceId("com.grabtaxi.passenger:id/parent_search_location");
                    UiObject thirdElementObject = device.findObject(thirdElementSelector);

                    UiSelector fourthElementSelector = new UiSelector().resourceId("com.grabtaxi.passenger:id/cv_saved_places");
                    UiObject fourthElementObject = device.findObject(fourthElementSelector);


                    if (secElementObject.exists() || thirdElementObject.exists() || fourthElementObject.exists())
                    {
                        firstElementObject.setText("The Globe Tower");
                        Timestamp unUsableTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name,"Enter Text Successfully Time:" + unUsableTime);
                        value = true;
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Displaying Car");
        }
        return value;
    }

    public boolean clickPlace(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector firstElementSelector = new UiSelector().textContains("The Globe Tower").resourceId("com.grabtaxi.passenger:id/poi_item_title");
                UiObject firstElementObject = device.findObject(firstElementSelector);

                if(firstElementObject.exists())
                {
                    firstElementObject.click();
                    searchPlaceTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Place Successfully Time:" + searchPlaceTime);
                    value = true;

                    if (firstElementObject.exists() == false)
                    {
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Displaying Car");
        }
        return value;
    }

    public boolean placeLoaded(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector firstSelector = new UiSelector().resourceId("com.grabtaxi.passenger:id/node_pick_up_pin");
                UiObject firstElementObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().textContains("Confirm");
                UiObject secElementObject = device.findObject(secSelector);
                if(firstElementObject.exists() || secElementObject.exists())
                {
                    placeLoadTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Place Loaded Successfully" + placeLoadTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Loading Place");
        }
        return value;
    }

    public boolean confirm(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector firstSelector = new UiSelector().textContains("Confirm");
                UiObject firstElementObject = device.findObject(firstSelector);

                Thread.sleep(2000);
                if(firstElementObject.exists())
                {
                    firstElementObject.click();
                    clickConfirmTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Confirm Successfully" + clickConfirmTime);
                    value = true;
                    if (firstElementObject.exists() == false)
                    {
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Loading Place");
        }
        return value;
    }

    public boolean fareLoaded(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector firstSelector = new UiSelector().resourceId("com.grabtaxi.passenger:id/serviceTypeItemView");
                UiObject firstElementObject = device.findObject(firstSelector);

                if(firstElementObject.exists())
                {
                    faireLoadTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Fare Loaded Successfully" + faireLoadTime);
                    value = true;
                    device.pressBack();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Loading Place");
        }
        return value;
    }

    private boolean kpiCalculation() {
        try {
            Thread.sleep(10000);
            double TTLH = (homeElementsAppearTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double TTLBP = (carPageLoadTime.getTime() - carPageClickTime.getTime()) / 1000.0;
            double TTLBF = (faireLoadTime.getTime() - clickConfirmTime.getTime()) / 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page = " + TTLH);
            Log.d(GlobalVariables.Tag_Name, "Time to Load Booking Page = " + TTLBP);
            Log.d(GlobalVariables.Tag_Name, "Time to Load Booking Fare = " + TTLBF);
            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}

