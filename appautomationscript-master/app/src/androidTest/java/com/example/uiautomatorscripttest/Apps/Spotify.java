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

public class Spotify {

    private UiDevice device;
    Timestamp homeElementsAppearTime,clickSearchTime,clickLibraryTime,libraryDisplayedTime,clickSearchedItemTime,songPlayTime
            ,clickPlaylistTime,playlistDisplayedTime,searchItemDisplayTime,inputTextTime,clickPlayTime;

    public int testRun(UiDevice device) {
        boolean runTest = true;
        this.device = device;
        try {
            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Home element");
                return 1;
            }
            runTest = checkAds();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Check Home Ads");
                return 1;
            }
//            runTest = gotoLibrary();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Go To Library");
//                return 1;
//            }
//            runTest = libraryDisplayed();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Displayed Library");
//                return 1;
//            }
//            runTest = clickSearchPlaylist();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Click Search in Playlist");
//                return 1;
//            }
//            runTest = clickPlayList();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Click Playlist");
//                return 1;
//            }
//            runTest = playlistDisplayed();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Display Playlist");
//                return 1;
//            }
            runTest = gotoSearch();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Go to Search");
                return 1;
            }
            runTest = clickSearchBox();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click SearchBox");
                return 1;
            }
            runTest = searchItemDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Search Item");
                return 1;
            }
            runTest = clickSearchedItem();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Item");
                return 1;
            }
            runTest = playlist();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Playlist");
                return 1;
            }
            runTest = playSong();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Play Song");
                return 1;
            }
            runTest = checkIfPlaying();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Play Song");
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

                UiSelector firElementSelector = new UiSelector().resourceId("com.spotify.music:id/free_tier_home_hubs_header");
                UiObject firElementObject = device.findObject(firElementSelector);

                UiSelector secElementSelector = new UiSelector().resourceId("com.spotify.music:id/section_heading1_title");
                UiObject secElementObject = device.findObject(secElementSelector);

                UiSelector thirdElementSelector = new UiSelector().textContains("DISMISS");
                UiObject thirdElementObject = device.findObject(thirdElementSelector);

                if (firElementObject.exists() || secElementObject.exists() || thirdElementObject.exists())
                {
                    homeElementsAppearTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Home Page Appear Time:" + homeElementsAppearTime);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Home Page Elements");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return false;
        }
        return value;
    }

    public boolean checkAds() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.Wait_Timeout) {

                UiSelector firElementSelector = new UiSelector().textContains("DISMISS");
                UiObject firElementObject = device.findObject(firElementSelector);

                UiSelector secElementSelector = new UiSelector().resourceId("com.spotify.music:id/dismiss_text");
                UiObject secElementObject = device.findObject(secElementSelector);

                if (firElementObject.exists())
                {
                    firElementObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Home Ads Detected: " + unUsableTime);
                    value = true;
                    break;
                }
                else if (secElementObject.exists())
                {
                    secElementObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Home Ads Detected: " + unUsableTime);
                    value = true;
                    break;
                }
                else if(stopWatch.getTime() >= 10000){
                    Log.d(GlobalVariables.Tag_Name, "No Ads");
                    value = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return false;
        }
        return value;
    }

    public boolean gotoLibrary() {
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector firSelector = new UiSelector().textContains("Library");
                UiObject firObject = device.findObject(firSelector);

                UiSelector secSelector = new UiSelector().resourceId("com.spotify.music:id/your_library_tab");
                UiObject secObject = device.findObject(secSelector);

                if(firObject.exists()){
                    firObject.click();
                    clickLibraryTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on Your Library Successfully Time:" + clickLibraryTime);
                    value = true;
                    break;
                }
                else if(secObject.exists()){
                    secObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on Your Library Successfully Time:" + unUsableTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on clicking Your Library");
        }
        return value;
    }

    public boolean libraryDisplayed() {
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector firSelector = new UiSelector().resourceId("com.spotify.music:id/row_root");
                UiObject firObject = device.findObject(firSelector);

                UiSelector secSelector = new UiSelector().resourceId("com.spotify.music:id/icon_create");
                UiObject secObject = device.findObject(secSelector);

                if(firObject.exists() || secObject.exists()){
                    libraryDisplayedTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Library Displayed Successfully Time:" + libraryDisplayedTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Displaying Library");
        }
        return value;
    }

    public boolean clickSearchPlaylist() {
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                Log.d(GlobalVariables.Tag_Name,"Trying to Click Search in Playlist");
                UiSelector secSelector = new UiSelector().descriptionContains("Search Your Library");
                UiObject secObject = device.findObject(secSelector);

                UiSelector searchFieldSelector = new UiSelector().className("android.widget.EditText");
                UiObject searchFieldObject = device.findObject(searchFieldSelector);

                 if(secObject.exists()){
                    secObject.click();
                    searchFieldObject.setText("Test");
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Search Successfully Time:" + unUsableTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Clicking Search");
        }
        return value;
    }

    public boolean clickPlayList(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().resourceId("com.spotify.music:id/row_root");
                UiObject object = device.findObject(selector);

                UiSelector secSelector = new UiSelector().descriptionContains("playlist");
                UiObject secObject = device.findObject(secSelector);

                if(object.exists()){
                    object.click();
                    clickPlaylistTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Playlist Successfully" + clickPlaylistTime);
                    value = true;
                    break;
                }
                else if(secObject.exists()){
                    secObject.click();
                    clickPlaylistTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Playlist Successfully" + clickPlaylistTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Playlist");
        }
        return value;
    }

    public boolean playlistDisplayed(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().resourceId("com.spotify.music:id/action_row_container");
                UiObject object = device.findObject(selector);

                UiSelector secSelector = new UiSelector().resourceId("com.spotify.music:id/cta_button");
                UiObject secObject = device.findObject(secSelector);

                if(object.exists() || secObject.exists()){
                    playlistDisplayedTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Playlist Displayed Successfully" + playlistDisplayedTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Playlist");
        }
        return value;
    }

    public boolean gotoSearch() {
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector firSelector = new UiSelector().resourceId("com.spotify.music:id/search_tab");
                UiObject firObject = device.findObject(firSelector);

                UiSelector secSelector = new UiSelector().textContains("Search");
                UiObject secObject = device.findObject(secSelector);

                if(firObject.exists()){
                    firObject.click();
                    clickSearchTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Search Successfully Time:" + clickSearchTime);
                    value = true;
                    break;
                }
                else if(secObject.exists()){
                    secObject.click();
                    clickSearchTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Search Successfully Time:" + clickSearchTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Clicking Search");
        }
        return value;
    }

    public boolean clickSearchBox(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().resourceId("com.spotify.music:id/find_search_field");
                UiObject object = device.findObject(selector);

                UiSelector inputSelector = new UiSelector().className("android.widget.EditText");
                UiObject inputObject = device.findObject(inputSelector);

                if(object.exists()){
                    object.click();
                    inputObject.setText("Chill");
                    inputTextTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Input Text Successfully" + inputTextTime);
                    value = true;
                    device.pressBack();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in input Text");
        }
        return value;
    }

    public boolean searchItemDisplayed(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().textContains("Ben&Ben");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("com.spotify.music:id/row_view");
                UiObject objectSec = device.findObject(selectorSec);

                if(objectSec.exists())
                {
                    searchItemDisplayTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Item Displayed Successfully" + searchItemDisplayTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Item");
        }
        return value;
    }

    public boolean clickSearchedItem(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selectorSec = new UiSelector().resourceId("com.spotify.music:id/row_view");
                UiObject objectSec = device.findObject(selectorSec);

                if(objectSec.exists())
                {
                    objectSec.click();
                    clickSearchedItemTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Item Successfully" + clickSearchedItemTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Item");
        }
        return value;
    }

    public boolean playlist(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selectorFirst = new UiSelector().resourceId("com.spotify.music:id/row_root");
                UiObject objectFirst = device.findObject(selectorFirst);

                UiSelector selectorSec = new UiSelector().resourceId("com.spotify.music:id/action_row_container");
                UiObject objectSec = device.findObject(selectorSec);

                if(objectSec.exists() || objectFirst.exists())
                {
                    playlistDisplayedTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Playlist Displayed Successfully" + playlistDisplayedTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Item");
        }
        return value;
    }

    public boolean playSong(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selectorSec = new UiSelector().resourceId("com.spotify.music:id/button_play_and_pause");
                UiObject objectSec = device.findObject(selectorSec);

                if(objectSec.exists())
                {
                    objectSec.click();
                    device.pressBack();
                    clickPlayTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Song Successfully" + clickPlayTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Item");
        }
        return value;
    }

    public boolean checkIfPlaying(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selectorSec = new UiSelector().descriptionContains("Pause");
                UiObject objectSec = device.findObject(selectorSec);

                if(objectSec.exists()){
                    songPlayTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Song Playing Successfully: " + songPlayTime);
                    value = true;
                    Thread.sleep(5000);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Playing Song");
        }
        return value;
    }

    private boolean kpiCalculation() {
        Log.d(GlobalVariables.Tag_Name, "KPI Calculation");
        try {
            Thread.sleep(5000);
            double TTLH = (homeElementsAppearTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double TTSP = (searchItemDisplayTime.getTime() - inputTextTime.getTime()) / 1000.0;
            double TTLP = (playlistDisplayedTime.getTime() - clickSearchedItemTime.getTime()) / 1000.0;
            double PST = (songPlayTime.getTime() - clickPlayTime.getTime()) / 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page = " + TTLH);
            Log.d(GlobalVariables.Tag_Name, "time to Search Playlist = " + TTSP);
            Log.d(GlobalVariables.Tag_Name, "Time to Load Playlist = " + TTLP);
            Log.d(GlobalVariables.Tag_Name, "Play Start Time = " + PST);
            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}

