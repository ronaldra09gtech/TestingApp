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

public class GoogleMap {

    private UiDevice device;
    Timestamp homeElementsAppearTime,searchPlaceTime,overviewTime,locationLoadTime,overviewDisplayedTime;

    public int testRun(UiDevice device) {
        boolean runTest = true;
        this.device = device;
        try {
            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Home element");
                return 1;
            }
            runTest = searchPlace();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Options");
                return 1;
            }
            runTest = clickSearchedPlace();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Search Place");
                return 1;
            }
            runTest = locationLoaded();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Load Location");
                return 1;
            }
            runTest = overView();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Overview");
                return 1;
            }
            runTest = overviewDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Overview");
                return 1;
            }
//            runTest = clickStart();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Click Start");
//                return 1;
//            }
//            runTest = startDisplayed();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Display Start");
//                return 1;
//            }
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

                Log.d(GlobalVariables.Tag_Name, "Finding Home Element ");
                UiSelector selectorResume = new UiSelector().resourceId("com.google.android.apps.maps:id/below_search_omnibox_container");
                UiObject objectResume = device.findObject(selectorResume);

                UiSelector selectorRightMenu = new UiSelector().resourceId("com.google.android.apps.maps:id/sheet_header");
                UiObject objectRightMenu = device.findObject(selectorRightMenu);

                if (objectResume.exists()|| objectRightMenu.exists())
                {
                    homeElementsAppearTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Home Elements Appear Time:" + homeElementsAppearTime);
                    value = true;

                    Thread.sleep(3000);
                    if (objectRightMenu.exists())
                    {
                        device.pressBack();
                    }
                    break;
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name, "Loading...");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return false;
        }
    }

    public boolean searchPlace() {
        boolean value = false;
        try{
            UiSelector selectorWhereTo = new UiSelector().className("android.widget.EditText");
            UiObject objectWhereTo = device.findObject(selectorWhereTo);

            UiSelector selectorText = new UiSelector().resourceId("com.google.android.apps.maps:id/search_omnibox_edit_text");
            UiObject objectText = device.findObject(selectorText);

            if(objectWhereTo.waitForExists(GlobalVariables.OneMin_Timeout)){
                objectWhereTo.click();
                objectText.setText("The Globe Tower");
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Searched Place Displayed Successfully Time:" + unUsableTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on clicking Where to");
        }
        return value;
    }

    public boolean clickSearchedPlace() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Taguig");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                searchPlaceTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Searched Place Successfully Time:" + searchPlaceTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Clicking Searched Place");
        }
        return value;
    }

    public boolean locationLoaded(){
        boolean value = false;
        try{
            UiSelector PINSelector = new UiSelector().resourceId("com.google.android.apps.maps:id/business_place_card");
            UiObject PINObject = device.findObject(PINSelector);

            if(PINObject.waitForExists(GlobalVariables.OneMin_Timeout)){
                locationLoadTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Location Loaded Successfully" + locationLoadTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking GO");
        }
        return value;
    }

    public boolean overView(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Directions");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Log.d(GlobalVariables.Tag_Name,"Clicked Directions Successfully");
                overviewTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Directions Successfully Clicked: " + overviewTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Directions");
        }
        return value;
    }

    public boolean overviewDisplayed(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("com.google.android.apps.maps:id/header_container");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                overviewDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Directions Successfully Loaded: " + overviewDisplayedTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Loading Overview");
        }
        return value;
    }

    public boolean clickStart(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().textContains("Start");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("com.google.android.apps.maps:id/start_button");
                UiObject objectSec = device.findObject(selectorSec);

                if(object.exists())
                {
                    object.click();
                    Log.d(GlobalVariables.Tag_Name,"Clicked Start Successfully: ");
                    value = true;
                    break;
                }
                else if(objectSec.exists())
                {
                    objectSec.click();
                    Log.d(GlobalVariables.Tag_Name,"Clicked Start Successfully: ");
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Start");
        }
        return value;
    }

    public boolean startDisplayed(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().resourceId("com.google.android.apps.maps:id/navigation_time_remaining_label");
                UiObject object = device.findObject(selector);

                if(object.exists())
                {
                    Log.d(GlobalVariables.Tag_Name,"Start Displayed Successfully: ");
                    value = true;
                    device.pressBack();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Displaying Start");
        }
        return value;
    }

    private boolean kpiCalculation() {
        Log.d(GlobalVariables.Tag_Name, "KPI Calculation");
        try {
            Thread.sleep(5000);
            double TTLH = (homeElementsAppearTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double TTLSL = (locationLoadTime.getTime() - searchPlaceTime.getTime()) / 1000.0;
            double TTLO = (overviewDisplayedTime.getTime() - overviewTime.getTime()) / 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page = " + TTLH);
            Log.d(GlobalVariables.Tag_Name, "Time to Load Search Location = " + TTLSL);
            Log.d(GlobalVariables.Tag_Name, "Time to Load Overview = " + TTLO);
            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}

