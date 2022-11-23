package com.example.uiautomatorscripttest.Apps;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.example.uiautomatorscripttest.ExampleInstrumentedTest;
import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import com.example.uiautomatorscripttest.Utility.DataHolder;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Timestamp;
import java.util.Date;

public class Facebook {

    private Timestamp HPT,EST,SRT,VLT,CST,LBT,LVT,CLVT,VST;
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
            runTest = suggestGotoFree();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Suggestion");
                return 1;
            }
            runTest = checkIfFreeMode();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Check Free Mode");
                return 1;
            }
            runTest = checkIfLoaded();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Check If Home is Loaded");
                return 1;
            }
            runTest = gotoSearch();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Go To Search");
                return 1;
            }
            runTest = enterSearch();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Enter Search Text");
                return 1;
            }
            runTest = SResultAllTab();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Check Search Result");
                return 1;
            }
            runTest = clickVideos();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Videos Tab");
                return 1;
            }
            runTest = videosResult();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Check Video Result");
                return 1;
            }
            runTest = gotoFilter();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Go To Filter Button");
                return 1;
            }
            runTest = clickLive();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Live Button");
                return 1;
            }
//            runTest = clickShowResult();
//            if (!runTest) {
//                DataHolder.getInstance().setFailureReason("Unable To Click Show Result Button");
//                return 1;
//            }
            runTest = liveVideoAppear();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Display Live Videos");
                return 1;
            }
            runTest = clickLiveVideo();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Live Videos");
                return 1;
            }
            runTest = checkIfPlaying();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Check if Playing");
                return 1;
            }
            runTest = bufferRead();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Check Buffer");
                return 1;
            }
            runTest = kpiCalculation();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Check KPI's");
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
            device.executeShellCommand(getCommand(GlobalVariables.Facebook_Package));
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
                Log.d(GlobalVariables.Tag_Name, "Finding Home Element");

                UiSelector homeElementId = new UiSelector().descriptionContains("Stories");
                UiObject homeElement = device.findObject(homeElementId);

                UiSelector homeElementId2= new UiSelector().descriptionContains("Photo");
                UiObject homeElement2 = device.findObject(homeElementId2);
                if (homeElement.exists() || homeElement2.exists()) {
                    HPT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Home Elements Appear Time:" + HPT);
                    homeElementFound = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Home Page Elements");
                    Thread.sleep(200);
                }
            }
            return homeElementFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in finding Home Element");
            return false;
        }
    }

    private boolean suggestGotoFree() {
        boolean value;
        try {
            Thread.sleep(5000);
            Log.d(GlobalVariables.Tag_Name, "Finding Suggestion");

            UiSelector xButton = new UiSelector().descriptionContains("Close");
            UiObject objectXbutton = device.findObject(xButton);

            if (objectXbutton.waitForExists(3000)) {
                objectXbutton.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Suggestion Appear Time:" + unUsableTime);
            } else {
                Log.d(GlobalVariables.Tag_Name, "No Suggestion");
            }
            value = true;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Suggestion");
            return false;
        }
        return value;
    }

    private boolean checkIfFreeMode() {
        try
        {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= 5000) {
                Log.d(GlobalVariables.Tag_Name, "Checking if Free Mode");

                UiSelector selector = new UiSelector().textContains("See Photos");
                UiObject object = device.findObject(selector);

                UiSelector selector1 = new UiSelector().textContains("Go to Data Mode");
                UiObject object1 = device.findObject(selector1);
                if (object.exists()) {
                    object.click();
                    Timestamp freeModeTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "On a Free Mode: " + freeModeTime);
                    checkUSEButton();
                }
                else if(object1.exists())
                {
                    object1.click();
                    Timestamp freeModeTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "On a Free Mode: " + freeModeTime);
                    checkUSEButton();
                }
                else {
                    Timestamp freeModeTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Not On a Free Mode: " + freeModeTime);
                }
            }
            return true;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in finding Home Element");
            return false;
        }
    }

    private boolean checkUSEButton() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Checking Use Button");

                UiSelector selector = new UiSelector().className("android.widget.ProgressBar");
                UiObject object = device.findObject(selector);
                if (object.exists())
                {
                    Log.d(GlobalVariables.Tag_Name, "Loading");
                }
                else
                {
                    UiSelector FBLogo = new UiSelector().descriptionContains("facebook logo");
                    UiObject objectFBLogo = device.findObject(FBLogo);

                    if(objectFBLogo.waitForExists(GlobalVariables.Timeout))
                    {
                        Log.d(GlobalVariables.Tag_Name, "Using Data Already");
                    }
                    else
                    {
                        Timestamp USEButtonTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "USE Button Appeared Time : " + USEButtonTime);
                        clickUSEButton();
                    }
                    value = true;
                    break;
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in finding USE Button");
            return false;
        }
    }

    private boolean clickUSEButton() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Clicking USE Button");

                UiSelector selector = new UiSelector().descriptionContains("USE");
                UiObject object = device.findObject(selector);

                UiSelector selector2 = new UiSelector().className("android.view.ViewGroup").index(1);
                UiObject object2 = device.findObject(selector2);
                if (object.exists())
                {
                    object.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "USE Button Clicked Time : " + unUsableTime);
                    value = true;
                    break;
                }else if(object2.exists())
                {
                    object2.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "USE Button Clicked Time : " + unUsableTime);
                    value = true;
                    break;
                }
                else {
                    Log.d(GlobalVariables.Tag_Name, "Loading in Clicking USE Button");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking USE Button");
            return false;
        }
    }

    private boolean checkIfLoaded() {
        boolean value = false;
        try {
            Log.d(GlobalVariables.Tag_Name,"Checking if Home is Loaded");
            UiSelector selector = new UiSelector().descriptionContains("Photo");
            UiObject object = device.findObject(selector);

            if (object.waitForExists(GlobalVariables.OneMin_Timeout)) {
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Clicked on Live Button Time:" + unUsableTime);
                value = true;
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Loading Home");
            return false;
        }
        return value;
    }

    private boolean gotoSearch() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Clicking Search Button");

                UiSelector searchHome = new UiSelector().descriptionContains("Search");
                UiObject searchHomeObject = device.findObject(searchHome);
                if(searchHomeObject.exists())
                {
                    searchHomeObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Search Button Clicked Successfully time: " + unUsableTime);
                    value = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding search Button");
            return false;
        }
        return value;
    }

    private boolean enterSearch() {
        boolean value = false;
        try {
            Log.d(GlobalVariables.Tag_Name, "Entering Text");

            UiSelector selector = new UiSelector().textContains("Search");
            UiObject object = device.findObject(selector);

            if (object.waitForExists(GlobalVariables.OneMin_Timeout)) {
                object.click();
                object.setText("videos live");
                device.pressEnter();
                EST = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Enter Search Time: " + EST);
                value = true;
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in entering Search Text");
            return false;
        }
        return value;
    }

    private boolean SResultAllTab() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Finding Search Result");

                UiSelector seeAllSelector = new UiSelector().descriptionContains("See All");
                UiObject seeAllObject = device.findObject(seeAllSelector);

                UiSelector secSelector = new UiSelector().className("android.view.ViewGroup");
                UiObject secObject = device.findObject(secSelector);
                if (seeAllObject.exists() || secObject.exists()) {
                    SRT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Search Result Time: " + SRT);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Search Results");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in getting Search Results");
            return value;
        }
        return value;
    }

    private boolean clickVideos() {
        boolean value = false;
        try {

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Clicking Videos Button");

                UiSelector seeAllSelector = new UiSelector().descriptionContains("Videos search results");
                UiObject seeAllObject = device.findObject(seeAllSelector);

                UiSelector selector = new UiSelector().descriptionContains("FILTERS");
                UiObject object = device.findObject(selector);

                if (seeAllObject.exists()) {
                    seeAllObject.click();
                    if (object.exists())
                    {
                        CST = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Clicked Videos Page: " + CST);
                        value = true;
                        break;
                    }
                } else {
                    UiScrollable scroll = new UiScrollable(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
                    scroll.setAsHorizontalList().scrollDescriptionIntoView("Videos search results");
                    Log.d(GlobalVariables.Tag_Name, "Loading Videos Page");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error Clicking Videos Page");
            return value;
        }
        return value;
    }

    private boolean videosResult() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Check if page is loaded ");

                UiSelector searchResultsID = new UiSelector().descriptionContains("Watching");
                UiObject searchResults = device.findObject(searchResultsID);

                UiSelector searchResultsID1 = new UiSelector().descriptionContains("Views");
                UiObject searchResults1 = device.findObject(searchResultsID1);

                UiSelector searchResultsID2 = new UiSelector().descriptionContains("SORT BY");
                UiObject searchResults2 = device.findObject(searchResultsID2);

                if (searchResults.exists() || searchResults1.exists() || searchResults2.exists()) {
                    VLT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Videos Page Loading Time: " + VLT);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name,"Loading Videos Page");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Loading Videos Page");
            return value;
        }
    }

    private boolean gotoFilter() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Click Filter Button ");

                UiSelector selector = new UiSelector().descriptionContains("FILTERS");
                UiObject object = device.findObject(selector);

                if (object.exists()) {
                    object.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Filter Successfully: " + unUsableTime);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name,"Loading in Clicking Filter");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Filter");
            return value;
        }
    }

    private boolean clickLive() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Click Live Button ");

                UiSelector selector = new UiSelector().className("android.view.ViewGroup").index(2);
                UiObject object = device.findObject(selector);
                if (object.exists()) {
                    object.click();
                    LBT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Filter Successfully: " + LBT);
                    value = true;
                    device.pressBack();
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name,"Loading in Clicking Filter");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Filter");
            return value;
        }
    }

    private boolean clickShowResult() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Click Show Results Button ");

                UiSelector selector = new UiSelector().descriptionContains("SHOW RESULTS");
                UiObject object = device.findObject(selector);
                if (object.exists()) {
                    object.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Show Results Successfully: " + unUsableTime);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name,"Loading in Clicking Show Results");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Show Results");
            return value;
        }
    }

    private boolean gotoliveButton() {
        boolean liveButtonFound = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Finding Live button");

                UiSelector liveButtonId = new UiSelector().descriptionContains("LIVE").clickable(true);
                UiObject liveButton = device.findObject(liveButtonId);

                if (liveButton.waitForExists(GlobalVariables.Wait_Timeout)) {
                    liveButton.click();
                    LBT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Live Button Time:" + LBT);
                    liveButtonFound = true;
                    break;
                } else {
                    UiScrollable scroll = new UiScrollable(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
                    scroll.setAsHorizontalList().scrollDescriptionIntoView("LIVE");
                    liveButton.click();
                    LBT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Live Button Time:" + LBT);
                    liveButtonFound = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Live Button Method");
            return false;
        }
        return liveButtonFound;
    }

    private boolean liveVideoAppear() {
        boolean videoAppearFound = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Finding Live Videos");

                UiSelector videosId1 = new UiSelector().descriptionContains("watching");
                UiObject videoAppear1 = device.findObject(videosId1);

                if (videoAppear1.exists()) {
                    LVT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Live Videos Appear Time:" + LVT);
                    videoAppearFound = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading for Live Video");
                    Thread.sleep(200);
                }
            }
            return videoAppearFound;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "No Live Video Available");
            return false;
        }
    }

    private boolean clickLiveVideo() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Clicking Live Video");

                UiSelector selector = new UiSelector().descriptionContains("Watching");
                UiObject object = device.findObject(selector);

                if (object.exists()) {
                    object.click();
                    CLVT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Live Video: " + CLVT);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name,"Loading Clicking Live Video");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Live Video");
            return false;
        }
        return value;
    }

    private boolean checkIfPlaying() {

        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Checking if Playing");

                UiSelector videoStartedId = new UiSelector().className("android.widget.ProgressBar");
                UiObject videoStarted = device.findObject(videoStartedId);

                UiSelector videoPaused = new UiSelector().descriptionContains("Play current video");
                UiObject objectPaused = device.findObject(videoPaused);

                if (videoStarted.exists()) {
                    Log.d(GlobalVariables.Tag_Name, "Video is Loading");
                    Thread.sleep(200);
                } else {
                    if(objectPaused.exists())
                    {
                        objectPaused.click();
                    }
                    else
                    {
                        VST = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Video Start Playing Time: " + VST);

                        Log.d(GlobalVariables.Tag_Name, "Double Check");
                        Thread.sleep(3000);
                        int x = device.getDisplayWidth();
                        int y = device.getDisplayHeight();
                        double width = x / 100.00;
                        double height = y / 100.00;
                        int w = (int) (width * 50.00);
                        int h = (int) (height * 15.00);
                        device.click(w, h);
                        Thread.sleep(3000);
                        UiSelector checkPause = new UiSelector().descriptionContains("Play current video");
                        UiObject objectCheck = device.findObject(checkPause);

                        if (objectCheck.exists())
                        {
                            objectCheck.click();
                        }
                        else
                        {
                            value = true;
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in getting Video Started");
            return false;
        }
        return value;
    }

    private boolean bufferRead() {
        try {
            Log.d(GlobalVariables.Tag_Name, "Checking if Buffering");

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector loaderId = new UiSelector().textContains("Couldn't play video");
                UiObject loader = device.findObject(loaderId);
                if (loader.exists()) {
                    Timestamp loaderYes = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Loader Appear:YES Time: "  + loaderYes);
                } else {
                    Timestamp loaderNo = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Loader Appear:NO Time: " + loaderNo);
                    Thread.sleep(200);
                }
            }
            return true;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Buffer Reading");
            return false;
        }
    }

    private boolean kpiCalculation() {
        try {
            Thread.sleep(10000);
            double TTLH = (HPT.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double SRTi = (SRT.getTime() - EST.getTime()) / 1000.0;
            double TTLV = (VLT.getTime() - CST.getTime()) / 1000.0;
            double TTLL = (LVT.getTime() - LBT.getTime()) / 1000.0;
            double VPST = (VST.getTime() - CLVT.getTime()) / 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page = " + TTLH);
            Log.d(GlobalVariables.Tag_Name, "Search Result Time = " + SRTi);
            Log.d(GlobalVariables.Tag_Name, "Time To Load Video Page = " + TTLV);
            Log.d(GlobalVariables.Tag_Name, "Time To Load Live Video Page = " + TTLL);
            Log.d(GlobalVariables.Tag_Name, "Play Start Time = " + VPST);
            Log.d(GlobalVariables.Tag_Name, "Buffer Percentage = Back-end Calculation");
            Log.d(GlobalVariables.Tag_Name, "Buffer Count = Back-end Calculation");
            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}
