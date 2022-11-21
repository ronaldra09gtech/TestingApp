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

public class Waze {

    private UiDevice device;
    Timestamp homeElementsAppearTime,searchPlaceTime,overviewTime,locationGOTime;

    public int testRun(UiDevice device) {
        boolean runTest = true;
        this.device = device;
        try {
            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Home element");
                return 1;
            }
            runTest = gotoMyWaze();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Go To My Waze");
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
            runTest = clickGO();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click GO");
                return 1;
            }
            runTest = calculatingRoute();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Calculate Route");
                return 1;
            }
            runTest = goNow();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Go Now");
                return 1;
            }
            runTest = menu();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Menu");
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
                UiSelector selectorResume = new UiSelector().textContains("Resume drive");
                UiObject objectResume = device.findObject(selectorResume);

                UiSelector selectorCancel = new UiSelector().textContains("Cancel");
                UiObject objectCancel = device.findObject(selectorCancel);

                UiSelector selectorRightMenu = new UiSelector().resourceId("com.waze:id/rightMenuButtonImage");
                UiObject objectRightMenu = device.findObject(selectorRightMenu);

                UiSelector selector = new UiSelector().textContains("Where to?");
                UiObject object = device.findObject(selector);

                if (objectResume.exists())
                {
                    objectCancel.click();
                }
                else if (objectRightMenu.exists())
                {
                    objectRightMenu.click();

                    UiSelector selectorStop = new UiSelector().textContains("Stop");
                    UiObject objectStop = device.findObject(selectorStop);
                    if (objectStop.exists())
                    {
                        objectStop.click();
                    }
                }
                else if (object.exists())
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

    public boolean gotoMyWaze() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("com.waze:id/leftMenuButtonText");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on My Waze Successfully Time:" + unUsableTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on clicking My Waze");
        }
        return value;
    }

    public boolean searchPlace() {
        boolean value = false;
        try{
            UiSelector selectorWhereTo = new UiSelector().textContains("Where to?");
            UiObject objectWhereTo = device.findObject(selectorWhereTo);

            if(objectWhereTo.waitForExists(GlobalVariables.OneMin_Timeout)){
                objectWhereTo.click();
                objectWhereTo.setText("The Globe Tower");
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
            UiSelector selector = new UiSelector().textContains("Lane Q, Taguig");
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

    public boolean clickGO(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("GO");
            UiObject object = device.findObject(selector);

            UiSelector PINSelector = new UiSelector().resourceId("com.waze:id/addressPreviewPlaceSiteTitle");
            UiObject PINObject = device.findObject(PINSelector);

            if(PINObject.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                locationGOTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click GO Successfully" + locationGOTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking GO");
        }
        return value;
    }

    public boolean calculatingRoute(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector calculateSeletor = new UiSelector().textContains("Calculating");
                UiObject calculateObject = device.findObject(calculateSeletor);

                UiSelector errorNetworkSeletor = new UiSelector().textContains("No network connection");
                UiObject errorNetworkObject = device.findObject(errorNetworkSeletor);
                if(calculateObject.exists() || errorNetworkObject.exists())
                {
                    Log.d(GlobalVariables.Tag_Name,"Calculating...");
                }
                else
                {
                    UiSelector notifSelector = new UiSelector().textContains("Drive Now");
                    UiObject notifObject = device.findObject(notifSelector);
                    if(notifObject.exists())
                    {
                        device.pressBack();
                    }
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Calculating Route Successfully" + unUsableTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Calculating Route");
        }
        return value;
    }

    public boolean goNow(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Overview");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Log.d(GlobalVariables.Tag_Name,"Clicked Overview Successfully");
                overviewTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Overview Successfully Loaded" + overviewTime);

                UiSelector selectorOverview = new UiSelector().textContains("Re-center");
                UiObject objectOverview = device.findObject(selectorOverview);
                Thread.sleep(5000);
                if (objectOverview.exists())
                {
                    Log.d(GlobalVariables.Tag_Name,"Back");
                    device.pressBack();
                }
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Go Now");
        }
        return value;
    }

    public boolean menu(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().resourceId("com.waze:id/rightMenuButtonImage");
                UiObject object = device.findObject(selector);

                if(object.exists())
                {
                    object.click();
                    Log.d(GlobalVariables.Tag_Name,"Clicked Menu Successfully: ");

                    UiSelector selectorStop = new UiSelector().textContains("Stop");
                    UiObject objectStop = device.findObject(selectorStop);
                    Thread.sleep(4000);

                    if(objectStop.exists())
                    {
                        objectStop.click();
                        Log.d(GlobalVariables.Tag_Name,"Clicked Stop Successfully: ");
                        value = true;
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Right Menu");
        }
        return value;
    }

    private boolean kpiCalculation() {
        Log.d(GlobalVariables.Tag_Name, "KPI Calculation");
        try {
            Thread.sleep(5000);
            double TTLH = (homeElementsAppearTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double TTLSL = (locationGOTime.getTime() - searchPlaceTime.getTime()) / 1000.0;
            double TTLO = (overviewTime.getTime() - locationGOTime.getTime()) / 1000.0;

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

