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

public class Lazada {

    private UiDevice device;
    long counter = 0;
    Timestamp homeElementsAppearTime,clickSearchTime,enterSearchTime,searchResultsAppearTime,clickItemTime,viewItemTime,clickFeedTime,feedResultTime,followedResultTime,clickFollowedTime,
            checkAddsTime,displayFeedProfileTime,clickMydayTime,viewMydayTime,clickLazadaLiveTime,displayLiveTime,clickLiveTime,displayLiveViwerTime,clickHomeTime;

    public int testRun(UiDevice device) {
        boolean runTest = true;
        this.device = device;
        try {
            Log.d(GlobalVariables.Tag_Name, "Application Launched");

            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Home element");
                return 1;
            }
            runTest = checkAddsOnHome();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to check adds on home");
                return 1;
            }
            runTest = clickSearchButton();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Search bar");
                return 1;
            }
            runTest = enterSearchText();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Enter text in Search bar");
                return 1;
            }
            runTest = searchResults();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Search result");
                return 1;
            }
            runTest = clickOnItem();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click on Item");
                return 1;
            }
            runTest = viewItem();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to View Item");
                return 1;
            }
            runTest = returnToHome();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to return to Home");
                return 1;
            }
            runTest = gotoFeed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Feed Tab");
                return 1;
            }
            runTest = viewFeedItem();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to View Feed Item");
                return 1;
            }
            runTest = gotoFollowed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to followed tab");
                return 1;
            }
            runTest = viewFollowedItem();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to View followed Item");
                return 1;
            }
            runTest = gotoMyDay();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Myday");
                return 1;
            }
            runTest = viewMyday();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to View Myday");
                return 1;
            }
            runTest = gotoAccount();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go Account");
                return 1;
            }
            runTest = viewAccount();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to view Account");
                return 1;
            }
            runTest = gotoLazadaLive();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Lazada Live");
                return 1;
            }
            runTest = viewLive();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to View Live");
                return 1;
            }
            runTest = clickLive();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Live");
                return 1;
            }
            runTest = viewLiveViewer();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to View live viewers");
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
            device.executeShellCommand(getCommand(GlobalVariables.Lazada_Package));
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in closing the app");
        }
    }
    public boolean checkAddsOnHome() {
        long addCounter = 20000;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                Log.d(GlobalVariables.Tag_Name, "internal Counter " + stopWatch.getTime());
                Log.d(GlobalVariables.Tag_Name, "global Counter " + counter);
                //get adds element in home page
                UiSelector addsSelector = new UiSelector().resourceId("com.lazada.android:id/close_button");
                UiObject addsObject = device.findObject(addsSelector);

                UiSelector adds2Selector = new UiSelector().textContains("O1CN01wDnGi51lWoUAxpKMR_!!6000000004827-2-tps-32-33");
                UiObject adds2Object = device.findObject(adds2Selector);
                if(addsObject.exists()){
                    //remove adds in home page
                    counter = stopWatch.getTime();
                    checkAddsTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "First Home adds detected");
                    addsObject.click();
                    checkAddsOnHome();
                    break;
                }
                else if (adds2Object.exists())
                {
                    //remove adds in home page
                    counter = stopWatch.getTime();
                    checkAddsTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Second Home adds detected");
                    adds2Object.click();
                    checkAddsOnHome();
                    break;
                }
                else if(stopWatch.getTime() >= (addCounter-counter))
                {
                    Log.d(GlobalVariables.Tag_Name, String.valueOf((addCounter-counter)));
                    checkAddsTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "no Home adds detected");
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return false;
        }
    }

    public boolean homePage() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().resourceId("com.lazada.android:id/laz_homepage_scan_icon");
                UiObject object = device.findObject(selector);

                UiSelector selectorBanner = new UiSelector().resourceId("com.lazada.android:id/campaign_entry_banner");
                UiObject objectBanner = device.findObject(selectorBanner);
                if (object.exists() || objectBanner.exists()) {
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

    public boolean clickSearchButton() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("com.lazada.android:id/laz_homepage_search_view_container");
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

            UiSelector selector = new UiSelector().resourceId("com.lazada.android:id/search_input_box");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                object.setText("Globe sim");
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
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().textContains("Metro Manila");
                UiObject object = device.findObject(selector);

                UiScrollable scroll = new UiScrollable(new UiSelector().resourceId("com.lazada.android:id/libsf_srp_header_list_recycler"));
                scroll.scrollForward();

                if(object.exists()){
                    object.click();
                    clickItemTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on item successfully Time:" + clickItemTime);
                    value = true;
                    break;
                }
                else
                {
                    scroll.setAsVerticalList().scrollTextIntoView("Metro Manila");
                    object.click();
                    clickItemTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on item successfully Time:" + clickItemTime);
                    value = true;
                    break;
                }
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
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Trying to View Item");
                UiSelector selectorOne = new UiSelector().className("android.widget.TextView");
                UiObject objectOne = device.findObject(selectorOne);
                if (objectOne.exists())
                {
                    viewItemTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"View Item Appeared Time:" + viewItemTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Item");
        }
        return  value;
    }

    public boolean returnToHome(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Trying to go back in Home Page");
                UiSelector selectorBanner = new UiSelector().resourceId("com.lazada.android:id/laz_homepage_photo_search_icon");
                UiObject objectBanner = device.findObject(selectorBanner);
                if(objectBanner.exists() == false)
                {
                    device.pressBack();
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name,"Home Page");
                    value = true;
                    break;
                }
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

            UiSelector selector = new UiSelector().textContains("Feed").className("android.widget.TextView").index(1);
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

            UiSelector selector = new UiSelector().resourceId("com.lazada.android:id/detail_content");
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

    public boolean gotoFollowed(){
        boolean value = false;
        try{

            Thread.sleep(2000);
            UiSelector selector = new UiSelector().resourceId("com.lazada.android:id/tab_text").textContains("Followed");
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                clickFollowedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on followed successfully Time:" + clickFollowedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on followed");
        }
        return  value;
    }
    public boolean viewFollowedItem(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().resourceId("com.lazada.android:id/index_1");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                followedResultTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Followed Item Appeared Time:" + followedResultTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View followed");
        }
        return  value;
    }

    public boolean gotoMyDay(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().resourceId("com.lazada.android:id/play_icon");
                UiObject object = device.findObject(selector);

                UiScrollable scroll = new UiScrollable(new UiSelector().className("androidx.viewpager.widget.ViewPager"));
                if(object.exists()){
                    object.click();
                    clickMydayTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on Myday successfully Time:" + clickMydayTime);
                    value = true;
                    break;
                }
                else
                {
                    scroll.scrollIntoView(object);
                    object.click();
                    clickMydayTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on Myday successfully Time:" + clickMydayTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Myday");
        }
        return  value;
    }

    public boolean viewMyday(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {UiSelector selector = new UiSelector().resourceId("com.lazada.android:id/video_card_action_bar");
                UiObject object = device.findObject(selector);

                UiSelector selectorTumbs = new UiSelector().resourceId("com.lazada.android:id/up_parent");
                UiObject objectTumbs = device.findObject(selectorTumbs);

                if(object.exists()){
                    viewMydayTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Myday Appeared Time:" + viewMydayTime);
                    value = true;
                    Thread.sleep(5000);
                    device.pressBack();
                    break;
                }
                if (objectTumbs.exists())
                {
                    viewMydayTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Myday2 Appeared Time:" + viewMydayTime);
                    value = true;
                    Thread.sleep(5000);
                    device.pressBack();
                    break;
                }
            }
            return  value;

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Myday");
            return  false;
        }
    }

    public boolean gotoAccount(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().resourceId("com.lazada.android:id/title").textContains("Account");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp gotoAccountTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on Account Successfully Time:" + gotoAccountTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Account");
        }
        return  value;
    }
    public boolean viewAccount(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().textContains("LazLive");
                UiObject object = device.findObject(selector);

                if(object.exists()){
                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    double width = x / 100.00;
                    double height = y / 100.00;
                    int w = (int) (width * 50.00);
                    int h = (int) (height * 50.00);
                    device.drag(w,h,w,h - 250, 10);
                    Timestamp viewAccountTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Account Appeared Time:" + viewAccountTime);
                    value = true;
                    break;
                }
                else
                {
                    UiScrollable scroll = new UiScrollable(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
                    scroll.scrollTextIntoView("LazLive");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Account");
        }
        return  value;
    }
    public boolean gotoLazadaLive(){
        boolean value = false;
        try{
            Thread.sleep(2000);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Trying to click LazLive");

                UiSelector selector = new UiSelector().textContains("LazLive");
                UiObject object = device.findObject(selector);

                if(object.exists()){
                    object.click();
                    clickLazadaLiveTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on Lazada Live successfully Time:" + clickLazadaLiveTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Lazada Live");
        }
        return  value;
    }

    public boolean viewLive(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().resourceId("videoplayer0");
                UiObject object = device.findObject(selector);

                UiSelector selectorLive = new UiSelector().className("android.widget.FrameLayout").index(3);
                UiObject objectLive = device.findObject(selectorLive);

                if(object.exists()|| objectLive.exists()){
                    displayLiveTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Lazada Live Appeared Time:" + displayLiveTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Lazada Live");
        }
        return  value;
    }

    public boolean clickLive(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selectorLive = new UiSelector().className("android.widget.FrameLayout").index(3);
                UiObject objectLive = device.findObject(selectorLive);

                UiSelector selector = new UiSelector().resourceId("videoplayer0");
                UiObject object = device.findObject(selector);

                if(object.exists()){
                    object.click();
                    clickLiveTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on Live successfully Time:" + clickLiveTime);
                    value = true;
                    break;
                }
                else if(objectLive.exists())
                {
                    objectLive.click();
                    clickLiveTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on Live successfully Time:" + clickLiveTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Live");
        }
        return  value;
    }

    public boolean viewLiveViewer(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().className("android.view.View").descriptionContains(" views");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("com.lazada.android:id/weex_render_view");
                UiObject objectSec = device.findObject(selectorSec);

                if(object.exists()){
                    displayLiveViwerTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"live Appeared Time:" + displayLiveViwerTime);
                    value = true;
                    break;
                }
                else if(objectSec.exists()){
                    Log.d(GlobalVariables.Tag_Name,"Else");
                    displayLiveViwerTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"live Appeared Time:" + displayLiveViwerTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View live");
        }
        return  value;
    }

    private boolean kpiCalculation() {
        try {
            Thread.sleep(10000);
            double TTLH = (homeElementsAppearTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double SRT = (searchResultsAppearTime.getTime() - enterSearchTime.getTime()) / 1000.0;
            double TTLSI = (viewItemTime.getTime() - clickItemTime.getTime()) / 1000.0;
            double TTLM = (viewMydayTime.getTime() - clickMydayTime.getTime())/ 1000.0;
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

