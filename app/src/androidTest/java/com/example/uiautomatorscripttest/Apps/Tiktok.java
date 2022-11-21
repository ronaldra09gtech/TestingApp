package com.example.uiautomatorscripttest.Apps;

import android.content.Context;
import android.util.Log;

import android.graphics.Rect;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;


import androidx.constraintlayout.solver.GoalRow;

import com.example.uiautomatorscripttest.ExampleInstrumentedTest;
import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import com.example.uiautomatorscripttest.Utility.DataHolder;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Tiktok {
    private Timestamp lt, heat, wvt, vat, hbt, hvat, st, est, sat, pvt, vst, ht, bbt, fbt, tct;
    private UiDevice device;
    public int testRun(UiDevice device) {
        boolean runTest = true;
        try {
            this.device = device;

            runTest = homeElement();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Home Page Element");
                return 1;
            }

            runTest = searchAppear();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Search Button");
                return 1;
            }

            runTest = enterSearch();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Enter Search Text");
                return 1;
            }

            runTest = searchResults();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Search Results");
                return 1;
            }

            Thread.sleep(5000);

            runTest = hashTag();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find HashTag Button");
                return 1;
            }

            runTest = hashtagAppear();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find HashTag Results");
                return 1;
            }

            Thread.sleep(5000);

            runTest = watchVideos();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Videos Button");
                return 1;
            }

            runTest = videoAppear();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Videos");
                return 1;
            }

            runTest = backButton();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Back Button");
                return 1;
            }

            runTest = homeButton();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Home Button");
                return 1;
            }

            runTest = following();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Following Button");
                return 1;
            }

            runTest = trendingCreator();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Trending Creators");
                return 1;
            }

            tearDown();

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Test Run");
        }
        return 0;
    }

    private String getCommand(String appPackage) {
        return "am force-stop " + appPackage;
    }

    public void tearDown() {
        try {
            Thread.sleep(GlobalVariables.Timeout);
            device.executeShellCommand(getCommand(GlobalVariables.Tiktok_Package));
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in closing the app");
        }
    }

    private boolean homeElement() {
        boolean homeElementFound = false;

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector homeElementId =
                        new UiSelector().textContains("For You");
                UiObject homeElement = device.findObject(homeElementId);
                if (homeElement.exists()) {
                    heat = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Home Elements Appear Time:" + heat);
                    homeElementFound = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Home Page Elements");
                    Thread.sleep(200);
                }
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in finding Home Element");
            return homeElementFound;
        }
        return homeElementFound;
    }

    private boolean searchAppear() {
        try {
            boolean searchButtonFound = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(GlobalVariables.Tag_Name, "Trying to find search button with Attempt no." + (i + 1));
                    UiSelector searchId = new UiSelector().textContains("Discover");
                    UiObject searchAppear = device.findObject(searchId);

                    if (searchAppear.waitForExists(GlobalVariables.Wait_Timeout)) {
                        searchAppear.click();
                        st = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Search Button Appear Time" + st);
                        searchButtonFound = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in search Appear");
                }
            }
            return searchButtonFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding search Button Method");
            return false;
        }
    }

    private boolean enterSearch() {
        try {
            boolean enterSearchtext = false;
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                try {
                    Log.d(GlobalVariables.Tag_Name, "Trying to enter Search with Attempt no.");
                    UiSelector enterSearchField = new UiSelector().resourceId("com.ss.android.ugc.trill:id/eq7");
                    UiObject enterSearch_text = device.findObject(enterSearchField);

                    UiSelector enterText = new UiSelector().className("android.widget.EditText");
                    UiObject objectEnterText = device.findObject(enterText);

                    UiSelector clickSearch = new UiSelector().className("android.widget.TextSwitcher");
                    UiObject clickObjectSearch = device.findObject(clickSearch);

                    UiSelector searchSelector = new UiSelector().textContains("Search");
                    UiObject searchObject = device.findObject(searchSelector);

                    if (enterSearch_text.exists()) {
                        enterSearch_text.click();
                        Log.d(GlobalVariables.Tag_Name, "Entered one");
                        objectEnterText.setText("Football");
                        Thread.sleep(2000);
                        searchObject.click();
                        est = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name,"Enter Search Time" + est);
                        enterSearchtext = true;
                        break;
                    }
                    else if (clickObjectSearch.exists())
                    {
                        clickObjectSearch.click();
                        Log.d(GlobalVariables.Tag_Name, "Entered two");
                        objectEnterText.setText("Football");
                        Thread.sleep(2000);
                        searchObject.click();
                        est = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name,"Enter Search Time" + est);
                        enterSearchtext = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Enter Search Text Failed"+ " "+ e.getMessage());
                }
            }
            return enterSearchtext;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in entering Search Text");
            return false;
        }
    }

    private boolean searchResults() {
        boolean value = false;
        try {
            UiSelector searchResultsID =
                    new UiSelector().resourceId("com.ss.android.ugc.trill:id/bmb");
            UiObject searchResults = device.findObject(searchResultsID);

            UiSelector searchResultsID1 =
                    new UiSelector().textContains("K");
            UiObject searchResults1 = device.findObject(searchResultsID1);

            UiSelector searchResultsID2 =
                    new UiSelector().textContains("M");
            UiObject searchResults2 = device.findObject(searchResultsID2);

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                if (searchResults.exists() || searchResults1.exists() || searchResults2.exists()) {
                    sat = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Search #Tag Result Time" + sat);
                    value = true;
                    break;
                } else {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Loading Search Results");
                    Thread.sleep(200);
                }
            }
            return value;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in getting Search Results");
            return value;
        }
    }

    private boolean watchVideos() {
        try {
            boolean watchVideoFound = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find Watch Videos button with Attempt no." + (i + 1));
                    UiSelector watchVideoId = new UiSelector().textContains("VIDEOS");
                    UiObject watchVideo = device.findObject(watchVideoId);

                    if (watchVideo.waitForExists(GlobalVariables.Wait_Timeout)) {
                        watchVideo.click();
                        wvt = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Clicked on Watched Videos Time:" + wvt);
                        watchVideoFound = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Watch Videos");
                }
            }
            return watchVideoFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Watch Videos Method");
            return false;
        }
    }

    private boolean videoAppear() {
        boolean videoAppearFound = false;

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector videosId =
                        new UiSelector().resourceId("com.ss.android.ugc.trill:id/bmb");
                UiObject videoAppear = device.findObject(videosId);

                UiSelector videosID1 =
                        new UiSelector().textContains("K");
                UiObject videoAppear1 = device.findObject(videosID1);

                UiSelector videosID2 =
                        new UiSelector().textContains("M");
                UiObject videoAppear2 = device.findObject(videosID2);

                if (videoAppear.exists() || videoAppear1.exists() || videoAppear2.exists()) {
                    vat = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Videos Appear Time:" + vat);
                    videoAppearFound = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading for Videos");
                    Thread.sleep(200);
                }
            }
            return videoAppearFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in finding Videos");
            return false;
        }
    }

    private boolean hashTag() {
        try {
            boolean hashtagButtonFound = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find Hashtag button with Attempt no." + (i + 1));
                    UiSelector hashtagButtonId = new UiSelector().textContains("HASHTAGS");
                    UiObject hashtagButton = device.findObject(hashtagButtonId);

                    if (hashtagButton.waitForExists(GlobalVariables.Wait_Timeout)) {
                        hashtagButton.click();
                        hbt = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Clicked on Hashtag Button Time:" + hbt);
                        hashtagButtonFound = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Hashtag Button");
                }
            }
            return hashtagButtonFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Hashtag Button Method");
            return false;
        }
    }

    private boolean hashtagAppear() {
        boolean hashtagAppearFound = false;

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector hashtagViewsId =
                        new UiSelector().textContains("views");
                UiObject hashViews = device.findObject(hashtagViewsId);
                if (hashViews.exists()) {
                    hvat = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Hashtags Views Appear Time:" + hvat);
                    hashtagAppearFound = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading HashTags");
                    Thread.sleep(200);
                }
            }
            return hashtagAppearFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in finding HashTag Results");
            return false;
        }
    }

    private boolean backButton() {
        try {
            boolean backButtonFound = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(GlobalVariables.Tag_Name,"Trying to click on Back button with Attempt no." + (i + 1));
                        device.pressBack();
                        Thread.sleep(2000);
                        device.pressBack();
                        Thread.sleep(2000);
                        device.pressBack();
                        bbt = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Clicked on Back Button:" + bbt);
                        backButtonFound = true;
                        break;
                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Click Back Button Failed");
                }
            }
            return backButtonFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Back Button Method");
            return false;
        }
    }

    private boolean homeButton() {
        try {
            boolean homeButtonFound = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to click on Home button with Attempt no." + (i + 1));
                    UiSelector homeID = new UiSelector().textContains("Home");
                    UiObject home = device.findObject(homeID);
                    if (home.waitForExists(GlobalVariables.Wait_Timeout)) {
                        home.click();
                        ht = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click Home Button Time" + ht);
                        homeButtonFound = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Home Button");
                }
            }
            return homeButtonFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Home Button Method");
            return false;
        }
    }

    private boolean following() {
        try {
            boolean followingButtonFound = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(GlobalVariables.Tag_Name, "Trying to click on Following button with Attempt no." + (i + 1));
                    UiSelector followingId = new UiSelector().textContains("Following");
                    UiObject following = device.findObject(followingId);
                    if (following.waitForExists(GlobalVariables.Wait_Timeout)) {
                        following.click();
                        fbt = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Clicked on Following Button Time:" + fbt);
                        followingButtonFound = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Following Button");
                }
            }
            return followingButtonFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Following Button Method");
            return false;
        }
    }

    private boolean trendingCreator() {
        boolean value = false;
        try {
            UiSelector trendingCreatorID = new UiSelector().resourceId("com.ss.android.ugc.trill:id/e0n");
            UiObject trendingCreator = device.findObject(trendingCreatorID);
            UiSelector trendingCreatorID1 = new UiSelector().text("Follow");
            UiObject trendingCreator1 = device.findObject(trendingCreatorID1);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                if (trendingCreator.exists() || trendingCreator1.exists()) {
                    tct = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Trending Creators Appear Time:" + tct);
                    value = true;
                    break;
                } else {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Loading Trending Creators");
                    Thread.sleep(200);
                }
            }
            return value;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in getting Trending Creators");
            return value;
        }
    }

}
