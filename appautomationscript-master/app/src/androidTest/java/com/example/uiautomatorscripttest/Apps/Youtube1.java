package com.example.uiautomatorscripttest.Apps;


import android.support.test.uiautomator.UiScrollable;
import android.util.Log;

import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;


import com.example.uiautomatorscripttest.ExampleInstrumentedTest;
import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import com.example.uiautomatorscripttest.Interfaces.setAttrib;
import com.example.uiautomatorscripttest.Utility.DataHolder;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.assertEquals;

public class Youtube1 {
    private UiDevice device;
    setAttrib setAttribY = new setAttrib("Youtube","com.google.android.youtube");
    Timestamp homePageTime,ST,EST,SRT,CVT,VPT;
    String videoQuality = "";

    public int testRun(UiDevice device) {
        this.device = device;
        boolean runTest = true;
        try {
            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to find Home Element");
                return 1;
            }
            runTest = gotoProfile();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Clear History");
                return 1;
            }
            runTest = displaySetting();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Setting");
                return 1;
            }
            runTest = clickSetting();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Setting");
                return 1;
            }
            runTest = displayHistoryPrivacy();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display History and Privacy");
                return 1;
            }
            runTest = clickHistoryPrivacy();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click History and Privacy");
                return 1;
            }
            runTest = displayClearWatch();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Clear Watch");
                return 1;
            }
            runTest = clickClearWatch();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Clear Watch");
                return 1;
            }
            runTest = displayClearWatchHistory();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Clear Watch History");
                return 1;
            }
            runTest = clickClearHistory();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to click Clear Watch History");
                return 1;
            }
            runTest = clickSearchButton();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to click Search Button");
                return 1;
            }
            runTest = enterSearchText();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to enter Search text Button");
                return 1;
            }
            runTest = searchResults();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Search Video");
                return 1;
            }
            runTest = clickOnResult();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Video");
                return 1;
            }
            runTest = videoDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Video");
                return 1;
            }
            runTest = bufferRead();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Read Buffer");
                return 1;
            }
            runTest = getMoreButton();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Fragment Container");
                return 1;
            }
            runTest = gotoMore();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click More Button");
                return 1;
            }
            runTest = getVideoQuality();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to get Video Quality");
                return 1;
            }
            runTest = kpiCalculation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Calculate KPI");
                return 1;
            }
            tearDown();

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
            Thread.sleep(3000);
            device.executeShellCommand(getCommand(setAttribY.getPackage()));
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in closing the app");
        }
    }

    public boolean homePage() {
        boolean homeElement = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().resourceId("com.google.android.youtube:id/results");
                UiObject object = device.findObject(selector);
                if (object.exists()) {
                    homePageTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Home Elements Appear Time: " + homePageTime);
                    homeElement = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Home Page Elements");
                    Thread.sleep(200);
                }
            }
            return homeElement;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return homeElement;
        }
    }

    public boolean gotoProfile() {
        boolean value = false;
        try{
            Thread.sleep(3000);
            UiSelector selector = new UiSelector().resourceId("com.google.android.youtube:id/mobile_topbar_avatar");
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)) {
                object.click();
                Timestamp profileTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Clicked On Profile Time:" + profileTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking Profile Button");
        }
        return value;
    }
    public boolean displaySetting(){
        boolean value = false;
        try{
            UiSelector selector1 = new UiSelector().textContains("Settings");
            UiObject object1 = device.findObject(selector1);
            if(object1.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp settingTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Setting Time:" + settingTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Display Setting");
        }
        return  value;
    }
    public boolean clickSetting() {
        boolean value = false;
        try{
            UiSelector selector2 = new UiSelector().textContains("Settings");
            UiObject object2 = device.findObject(selector2);
            if(object2.waitForExists(GlobalVariables.OneMin_Timeout)) {
                object2.click();
                Timestamp gotoSettingTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Click Setting Time:" + gotoSettingTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking Setting");
        }
        return value;
    }
    public boolean displayHistoryPrivacy(){
        boolean value = false;
        try{
            UiSelector selector3 = new UiSelector().textContains("History & privacy");
            UiObject object3 = device.findObject(selector3);
            if(object3.waitForExists(GlobalVariables.OneMin_Timeout)) {
                Timestamp historyTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "History & privacy Time:" + historyTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Display History and Privacy");
        }
        return  value;
    }
    public boolean clickHistoryPrivacy() {
        boolean value = false;
        try{
            UiSelector selector4 = new UiSelector().textContains("History & privacy");
            UiObject object4 = device.findObject(selector4);
            if(object4.waitForExists(GlobalVariables.OneMin_Timeout)) {
                object4.click();
                Timestamp gotoHistoryTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Click History & privacy Time:" + gotoHistoryTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking History and Privacy");
        }
        return value;
    }
    public boolean displayClearWatch(){
        boolean value = false;
        try{
            UiSelector selector5 = new UiSelector().textContains("Clear watch history");
            UiObject object5 = device.findObject(selector5);
            if(object5.waitForExists(GlobalVariables.OneMin_Timeout)) {
                Timestamp clearWatchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Clear watch history Time:" + clearWatchTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Display Clear watch history");
        }
        return  value;
    }
    public boolean clickClearWatch() {
        boolean value = false;
        try{
            UiSelector selector6 = new UiSelector().textContains("Clear watch history");
            UiObject object6 = device.findObject(selector6);
            if(object6.waitForExists(GlobalVariables.OneMin_Timeout)) {
                object6.click();
                Timestamp gotoClearWatchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Click Clear watch history Time:" + gotoClearWatchTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking Clear watch history");
        }
        return value;
    }
    public boolean displayClearWatchHistory(){
        boolean value = false;
        try{
            UiSelector selector7 = new UiSelector().textContains("CLEAR WATCH HISTORY");
            UiObject object7 = device.findObject(selector7);
            if(object7.waitForExists(GlobalVariables.OneMin_Timeout)) {
                Timestamp Clear = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "CLEAR WATCH HISTORY Time:" + Clear);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Display Clear watch history");
        }
        return  value;
    }
    public boolean clickClearHistory() {
        boolean value = false;
        try{
            UiSelector selector8 = new UiSelector().resourceId("android:id/button1");
            UiObject object8 = device.findObject(selector8);
            if(object8.waitForExists(GlobalVariables.OneMin_Timeout)) {
                object8.click();
                Timestamp clearTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Clear History Successfully:" + clearTime);
                value = true;
                Thread.sleep(2000);
                device.pressBack();
                Thread.sleep(2000);
                device.pressBack();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking history");
        }
        return value;
    }
    public boolean clickSearchButton() {
        boolean value = false;
        try{
            Thread.sleep(3000);
            UiSelector selector = new UiSelector().resourceId("com.google.android.youtube:id/menu_item_1");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                ST = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked On Search Time:" + ST);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking Search Button");
        }
        return value;
    }

    public boolean enterSearchText(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("com.google.android.youtube:id/search_edit_text");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                object.setText("7-3hoYhxuX8");
                device.pressEnter();
                EST = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Entered Search Text Time:" + EST);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error while entering search text");
        }
        return value;
    }

    public boolean searchResults(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().descriptionContains("Fishball | #AtinAngMundo - 2 minutes");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("com.google.android.youtube:id/player_view");
                UiObject objectSec = device.findObject(selectorSec);
                if(objectSec.exists()){
                    SRT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Search Results Appear Time:" + SRT);
                    value = true;
                    break;
                }
                if(object.exists()){
                    SRT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Search Results Appear Time:" + SRT);
                    value = true;
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in finding Video");
        }
        return  value;
    }

    public boolean clickOnResult(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().descriptionContains("Fishball | #AtinAngMundo - 2 minutes");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("com.google.android.youtube:id/player_view");
                UiObject objectSec = device.findObject(selectorSec);
                if (objectSec.exists()) {
                    objectSec.click();
                    CVT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Video successfully:" + CVT);
                    value = true;
                    break;
                }
                if (object.exists()) {
                    object.click();
                    CVT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Video successfully:" + CVT);
                    value = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Video");
        }
        return  value;
    }

    public boolean videoDisplayed() {
        boolean value = false;
        try {
            UiSelector selector = new UiSelector().descriptionContains("Expand description");
            UiObject object = device.findObject(selector);
            if (object.waitForExists(GlobalVariables.OneMin_Timeout)) {
                VPT = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Video Appeared successfully:" + VPT);
                value = true;
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in appearing Video");
        }
        return value;
    }

    private boolean bufferRead() {
        try {
            UiSelector loaderId = new UiSelector().resourceId("com.google.android.youtube:id/player_loading_view_thin");
            UiObject loader = device.findObject(loaderId);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                if (loader.exists()) {
                    Timestamp loaderYes = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Loader Appear:YES Time:" + loaderYes);
                } else {
                    Timestamp loaderNo = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Loader Appear:NO Time:" + loaderNo);
                }
            }
            return true;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Buffer Reading");
            return false;
        }
    }
    private boolean getMoreButton() {
        boolean value = false;
        try {
            UiSelector selector = new UiSelector().resourceId("com.google.android.youtube:id/player_fragment_container");
            UiObject object = device.findObject(selector);
            if (object.waitForExists(GlobalVariables.OneMin_Timeout)) {
                object.click();
                Timestamp moreButton = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Successfully clicked Fragment container:" + moreButton);
                value = true;
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in clicking Fragment container");
        }
        return value;
    }
    private boolean gotoMore() {
        boolean value = false;
        try {
            UiSelector selector = new UiSelector().resourceId("com.google.android.youtube:id/player_overflow_button");
            UiObject object = device.findObject(selector);
            if (object.waitForExists(GlobalVariables.OneMin_Timeout)) {
                object.click();
                Timestamp moreButton = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Successfully clicked more button:" + moreButton);
                value = true;
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in clicking more button");
        }
        return value;
    }

    private boolean getVideoQuality() {
        boolean value = false;
        try {
            UiSelector selector = new UiSelector().resourceId("com.google.android.youtube:id/list_item_text_secondary");
            UiObject object = device.findObject(selector);
            if (object.waitForExists(GlobalVariables.OneMin_Timeout)) {
                videoQuality = object.getText();
                Log.d(GlobalVariables.Tag_Name, "Quality Appeared successfully:" + videoQuality);
                value = true;
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in appearing Video Quality");
        }
        return value;
    }

    private boolean kpiCalculation() {
        try {
            double TTLH = (homePageTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double PST = (VPT.getTime() - CVT.getTime())/ 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page=" + TTLH);
            Log.d(GlobalVariables.Tag_Name, "Play Start Time=" + PST);
            Log.d(GlobalVariables.Tag_Name, "Video Quality=" + videoQuality);
            Log.d(GlobalVariables.Tag_Name, "Buffer Count=" + "Calculate in the back end");
            Log.d(GlobalVariables.Tag_Name, "Buffer Percentage=" + "Calculate in the back end");

            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}

