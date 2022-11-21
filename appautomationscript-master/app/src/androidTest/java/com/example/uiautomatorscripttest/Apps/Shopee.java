package com.example.uiautomatorscripttest.Apps;


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

public class Shopee {

    private UiDevice device;
    Timestamp homeElementsAppearTime,clickSearchTime,enterSearchTime,searchResultsAppearTime,clickItemTime,viewItemTime,clickFeedTime,feedResultTime,
            clickFeedProfileTime,displayFeedProfileTime,clickMydayTime,viewMydayTime,clickShopeeLiveTime,displayLiveTime,clickLiveTime,displayLiveViwerTime;

    public int testRun(UiDevice device) {
        this.device = device;
        boolean runTest = true;
        try {
            Log.d(GlobalVariables.Tag_Name, "Application Launched");

            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to find Home Element");
                return 1;
            }
            runTest = checkAddsOnHome();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to find Home Element");
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
                DataHolder.getInstance().setFailureReason("Unable to Search result");
                return 1;
            }
            runTest = clickOnItem();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click item");
                return 1;
            }
            runTest = viewItem();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display item");
                return 1;
            }
            runTest = gotoFeed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go feed");
                return 1;
            }
            runTest = viewFeedItem();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to view feed");
                return 1;
            }
//            runTest = gotoFeedProfile();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to go feed profile");
//                return 1;
//            }
//            runTest = displayFeedProfile();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to display profile");
//                return 1;
//            }
//            runTest = gotoMyDay();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to go myday");
//                return 1;
//            }
//            runTest = viewMyday();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to view myday");
//                return 1;
//            }
            runTest = gotoShopeeLive();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go shopee live");
                return 1;
            }
            runTest = displayLive();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display live");
                return 1;
            }
            runTest = clickLive();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to click live");
                return 1;
            }
            runTest = displayLiveViewer();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display live");
                return 1;
            }
            runTest = kpiCalculation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Calculate KPI");
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
            device.executeShellCommand(getCommand(GlobalVariables.Shopee_Package));
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
                UiSelector selector = new UiSelector().className("android.widget.ImageView");
                UiObject object = device.findObject(selector);
                if (object.exists()) {
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
            return value;
        }
    }
    public boolean checkAddsOnHome() {
        boolean value = false;
        try {
            //get adds element in home page
            UiSelector addsSelector = new UiSelector().className("android.view.ViewGroup").index(2).clickable(true).focusable(true);
            UiObject addsObject = device.findObject(addsSelector);
            if(addsObject.waitForExists(20000)){
                //remove adds in home page
                Log.d(GlobalVariables.Tag_Name, "Home adds detected");
                Thread.sleep(200);
                addsObject.click();
                value = true;
            } else {
                Log.d(GlobalVariables.Tag_Name, "no Home adds detected");
                value = true;
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
        }
        return value;
    }

    public boolean clickSearchButton() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("com.shopee.ph:id/text_search");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                clickSearchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked On Search Successfully Time:" + clickSearchTime);
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

            UiSelector selector = new UiSelector().className("android.widget.EditText");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                object.setText("Globe Lte");
                device.pressEnter();
                enterSearchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Entered Search Text Successfully Time:" + enterSearchTime);
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
            UiSelector selector = new UiSelector().textContains("sold");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                searchResultsAppearTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Search Results Appear Time:" + searchResultsAppearTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in finding search results");
        }
        return  value;
    }

    public boolean clickOnItem(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("sold");
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                clickItemTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on item successfully Time:" + clickItemTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on item");
        }
        return  value;
    }

    public boolean viewItem(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("sold");
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                viewItemTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"View Item Appeared Time:" + viewItemTime);
                value = true;
                Thread.sleep(3000);
                device.pressBack();
                Thread.sleep(3000);
                device.pressBack();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Item");
        }
        return  value;
    }


    public boolean gotoFeed(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Feed");
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                clickFeedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on Feed successfully Time:" + clickFeedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Feed");
        }
        return  value;
    }

    public boolean viewFeedItem(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().className("android.widget.ImageView").index(2);
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                feedResultTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Feed Item Appeared Time:" + feedResultTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Feed");
        }
        return  value;
    }

//    public boolean gotoFeedProfile(){
//        boolean value = false;
//        try{
//            Thread.sleep(2000);
//            UiSelector selector = new UiSelector().textContains("shopeeph").index(1).className("android.widget.TextView");
//            UiObject object = device.findObject(selector);
//            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
//                object.click();
//                clickFeedProfileTime = new Timestamp(new Date().getTime());
//                Log.d(GlobalVariables.Tag_Name,"Clicked on FeedProfile successfully Time:" + clickFeedProfileTime);
//                value = true;
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            Log.d(GlobalVariables.Tag_Name,"Error in clicking on FeedProfile");
//        }
//        return  value;
//    }
//
//    public boolean displayFeedProfile(){
//        boolean value = false;
//        try{
//            UiSelector selector = new UiSelector().className("android.view.ViewGroup");
//            UiObject object = device.findObject(selector);
//            UiScrollable scroll = new UiScrollable(new UiSelector().className("androidx.viewpager.widget.ViewPager").scrollable(true));
//                if (object.waitForExists(GlobalVariables.OneMin_Timeout)) {
//                    displayFeedProfileTime = new Timestamp(new Date().getTime());
//                    Log.d(GlobalVariables.Tag_Name, "Feed profile Appeared Time:" + displayFeedProfileTime);
//                    scroll.scrollBackward();
//                    value = true;
//                }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            Log.d(GlobalVariables.Tag_Name,"Error in View Feed profile");
//        }
//        return  value;
//    }
//
//    public boolean gotoMyDay(){
//        boolean value = false;
//        try{
//
//            UiSelector selector = new UiSelector().className("android.view.ViewGroup").index(0).clickable(true).
//                    enabled(true).checkable(false).checked(false).focusable(true);
//            UiObject object = device.findObject(selector);
//            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
//                object.click();
//                clickMydayTime = new Timestamp(new Date().getTime());
//                Log.d(GlobalVariables.Tag_Name,"Clicked on Myday successfully Time:" + clickMydayTime);
//                value = true;
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Myday");
//        }
//        return  value;
//    }
//
//    public boolean viewMyday(){
//        boolean value = false;
//        try{
//            UiSelector selector = new UiSelector().resourceId("com.shopee.ph:id/function_btn_layout");
//            UiObject object = device.findObject(selector);
//            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
//                viewMydayTime = new Timestamp(new Date().getTime());
//                Log.d(GlobalVariables.Tag_Name,"Myday Appeared Time:" + viewMydayTime);
//                value = true;
//                Thread.sleep(3000);
//                device.pressBack();
//                Thread.sleep(3000);
//                device.pressBack();
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            Log.d(GlobalVariables.Tag_Name,"Error in View Myday");
//        }
//        return  value;
//    }

    public boolean gotoShopeeLive(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Shopee Live");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                clickShopeeLiveTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on Shopee Live successfully Time:" + clickShopeeLiveTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Shopee Live");
        }
        return  value;
    }

    public boolean displayLive(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("LIVE").className("android.widget.TextView").index(2);
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                displayLiveTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Shopee Live Appeared Time:" + displayLiveTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Shopee Live");
        }
        return  value;
    }

    public boolean clickLive(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().className("android.widget.TextView").textContains("LIVE").index(2);
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                clickLiveTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on Live successfully Time:" + clickLiveTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Live");
        }
        return  value;
    }

    public boolean displayLiveViewer(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().resourceId("com.shopee.ph:id/tv_live_title");
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                displayLiveViwerTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Viewer Appeared Time:" + displayLiveViwerTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Viewer");
        }
        return  value;
    }


    private boolean kpiCalculation() {
        try {
            Thread.sleep(10000);
            double TTLH = (homeElementsAppearTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double SRT = (searchResultsAppearTime.getTime() - enterSearchTime.getTime()) / 1000.0;
            double TTLSI = (viewItemTime.getTime() - clickItemTime.getTime()) / 1000.0;
//            double TTLM = (viewMydayTime.getTime() - clickMydayTime.getTime())/ 1000.0;
            double TTLM = (feedResultTime.getTime() - clickFeedTime.getTime())/ 1000.0;
            double TTLL = (displayLiveViwerTime.getTime() - clickLiveTime.getTime())/ 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page=" + TTLH);
            Log.d(GlobalVariables.Tag_Name, "Search Result Time=" + SRT);
            Log.d(GlobalVariables.Tag_Name, "Time To Load Selected Item=" + TTLSI);
            Log.d(GlobalVariables.Tag_Name, "Time To Load Myday=" + TTLM);
            Log.d(GlobalVariables.Tag_Name, "Time To Load Live Video Page=" + TTLL);


            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}

