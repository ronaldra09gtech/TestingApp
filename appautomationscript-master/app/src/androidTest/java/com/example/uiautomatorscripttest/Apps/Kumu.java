package com.example.uiautomatorscripttest.Apps;

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

public class Kumu {

    private Timestamp HPT,EST,SRT,VLT,CST,LBT,LVT,CLVT,VST;
    private UiDevice device;
    float loadingTime;
    public int testRun(UiDevice device) {
        boolean runTest = true;
        try {
            this.device = device;

            runTest = homeElement();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Home Page Element");
                return 1;
            }
            runTest = checkAds();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Check Ads");
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
            runTest = searchResult();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Load Search Result");
                return 1;
            }
            runTest = clickStreamers();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Stream Tab");
                return 1;
            }
            runTest = streamerResult();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Load Stream");
                return 1;
            }
            runTest = gotoKlips();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Klips Button");
                return 1;
            }
            runTest = klipsLoaded();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Load Klips Page");
                return 1;
            }
            runTest = clickLiveVideo();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Live Videos");
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
            device.executeShellCommand(getCommand(GlobalVariables.Kumu_Package));
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

                UiSelector homeElementId = new UiSelector().resourceId("im.kumu.ph:id/surface_container");
                UiObject homeElement = device.findObject(homeElementId);

                UiSelector homeElementId2= new UiSelector().resourceId("im.kumu.ph:id/img_shadow");
                UiObject homeElement2 = device.findObject(homeElementId2);

                UiSelector homeElementId3= new UiSelector().resourceId("im.kumu.ph:id/view_bottom_end");
                UiObject homeElement3 = device.findObject(homeElementId3);

                UiSelector adsSelector2 = new UiSelector().resourceId("im.kumu.ph:id/img_close");
                UiObject adsObject2 = device.findObject(adsSelector2);

                UiSelector adsSelector3 = new UiSelector().resourceId("im.kumu.ph:id/interstitial_image");
                UiObject adsObject3 = device.findObject(adsSelector3);

                UiSelector adsSelector4 = new UiSelector().resourceId("im.kumu.ph:id/video");
                UiObject adsObject4 = device.findObject(adsSelector4);

                if (homeElement.exists() || homeElement2.exists() || homeElement3.exists() || adsObject2.exists() || adsObject3.exists() || adsObject4.exists()) {
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

    private boolean checkAds() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.HalfMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Checking for ads");

                UiSelector adsSelector2 = new UiSelector().resourceId("im.kumu.ph:id/img_close");
                UiObject adsObject2 = device.findObject(adsSelector2);

                UiSelector adsSelector3 = new UiSelector().resourceId("im.kumu.ph:id/interstitial_image");
                UiObject adsObject3 = device.findObject(adsSelector3);

                UiSelector adsSelector4 = new UiSelector().resourceId("im.kumu.ph:id/video");
                UiObject adsObject4 = device.findObject(adsSelector4);

                UiSelector buttonSelector = new UiSelector().className("android.widget.ImageView");
                UiObject buttonObject = device.findObject(buttonSelector);

                if(adsObject2.exists() || adsObject4.exists())
                {
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Ads Appear time: " + unUsableTime);
                    device.pressBack();
                    value = true;
                    break;
                }
                if(adsObject3.exists())
                {
                    buttonObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Ads Appear time: " + unUsableTime);
                    value = true;
                    break;
                }
                if (stopWatch.getTime() >= 10000)
                {
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "No Ads: " + unUsableTime);
                    value = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Ads");
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

                UiSelector searchHome = new UiSelector().resourceId("im.kumu.ph:id/img_search");
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
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Entering Text");

                UiSelector selector = new UiSelector().className("android.widget.EditText");
                UiObject object = device.findObject(selector);

                UiSelector searchSelector = new UiSelector().textContains("Search");
                UiObject searchObject = device.findObject(searchSelector);

                UiSelector progressSelector = new UiSelector().resourceId("im.kumu.ph:id/progressBar");
                UiObject progressObject = device.findObject(progressSelector);

                if (object.exists()) {
                    if (!progressObject.exists())
                    {
                        object.click();
                        object.setText("#games");
                        Thread.sleep(3000);
                        searchObject.click();
                    }
                    if (progressObject.exists())
                    {
                        EST = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name,"Enter Search Time: " + EST);
                        value = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in entering Search Text");
            return false;
        }
        return value;
    }

    private boolean searchResult() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Finding Search Result");

                UiSelector firstSelector = new UiSelector().resourceId("im.kumu.ph:id/user_tags");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().resourceId("im.kumu.ph:id/user_name");
                UiObject secObject = device.findObject(secSelector);
                if (firstObject.exists() || secObject.exists()) {
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
    private boolean clickStreamers() {
        boolean value = false;
        try {

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Clicking Streamers Button");

                UiSelector selector = new UiSelector().resourceId("im.kumu.ph:id/tvMomentName").textContains("Streamers");
                UiObject object = device.findObject(selector);
                if (object.exists()) {
                    object.click();
                    CST = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Videos Page: " + CST);
                    value = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error Clicking Videos Page");
        }
        return value;
    }

    private boolean streamerResult() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Check if page is loaded ");

                UiSelector selector = new UiSelector().className("android.view.ViewGroup");
                UiObject object = device.findObject(selector);

                UiSelector secSelector = new UiSelector().resourceId("im.kumu.ph:id/user_tags");
                UiObject secObject = device.findObject(secSelector);

                if (object.exists() || secObject.exists()) {
                    VLT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Streamer Page Loading Time: " + VLT);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name,"Loading Streamer Page");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Loading Streamer Page");
            return value;
        }
    }

    private boolean gotoKlips() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Click Klips Button ");

                UiSelector selector = new UiSelector().textContains("Klips").resourceId("im.kumu.ph:id/tvMomentName");
                UiObject object = device.findObject(selector);

                if (object.exists()) {
                    object.click();
                    LVT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Klips Successfully: " + LVT);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name,"Loading in Clicking Klips");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Klips");
        }
        return value;
    }

    private boolean klipsLoaded() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Load Klips Page");

                UiSelector selector = new UiSelector().resourceId("im.kumu.ph:id/view_live");
                UiObject object = device.findObject(selector);

                UiSelector secSelector = new UiSelector().resourceId("android.widget.ImageView");
                UiObject secObject = device.findObject(secSelector);
                if (object.exists() || secObject.exists()) {
                    LBT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Klips Loaded Successfully: " + LBT);
                    value = true;
                    Thread.sleep(4000);
                    device.pressBack();
                    Thread.sleep(4000);
                    device.pressBack();
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name,"Loading in Load Klips");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Loading Klips");
            return value;
        }
    }

    private boolean clickLiveVideo() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Thread.sleep(2000);
                Log.d(GlobalVariables.Tag_Name, "Trying to Click Live Video ");

                UiSelector firstElementSelector = new UiSelector().resourceId("im.kumu.ph:id/tv_count");
                UiObject firstElementObject = device.findObject(firstElementSelector);

                UiSelector secondElementSelector= new UiSelector().resourceId("im.kumu.ph:id/img_shadow");
                UiObject secondElementObject = device.findObject(secondElementSelector);

                if (firstElementObject.exists()) {
                    firstElementObject.click();
                    CLVT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Video 1st element Successfully: " + CLVT);
                    value = true;
                    startPlaying();
                    break;
                }else if (secondElementObject.exists())
                {
                    secondElementObject.click();
                    CLVT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Video 2st element Successfully: " + CLVT);
                    value = true;
                    startPlaying();
                    break;
                }
                else {
                    Log.d(GlobalVariables.Tag_Name,"Loading in Clicking Live Video");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Live Video");
            return value;
        }
    }

    private boolean startPlaying() {
        boolean liveButtonFound = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Finding view element");

                UiSelector viewsElementSelector = new UiSelector().resourceId("im.kumu.ph:id/tv_num");
                UiObject viewsElementObject = device.findObject(viewsElementSelector);

                UiSelector chatElementSelector = new UiSelector().resourceId("im.kumu.ph:id/recycler_chat");
                UiObject chatElementObject = device.findObject(chatElementSelector);

                UiSelector layoutSelector = new UiSelector().resourceId("im.kumu.ph:id/title_layout");
                UiObject layoutObject = device.findObject(layoutSelector);

                if (viewsElementObject.exists() || chatElementObject.exists() || layoutObject.exists()) {
                    VST = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Video Playing Time:" + VST);
                    liveButtonFound = true;
                    break;
                }
                else if (stopWatch.getTime() >= 20000)
                {
                    clickLiveVideo();
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Playing video");
            return false;
        }
        return liveButtonFound;
    }

    private boolean bufferRead() {
        try {
            Log.d(GlobalVariables.Tag_Name, "Checking if Buffering");

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector secondLoaderId = new UiSelector().resourceId("im.kumu.ph:id/llayout_select");
                UiObject secondLoaderIdObject = device.findObject(secondLoaderId);

                switch (secondLoaderIdObject.exists() ? 1 : 2)
                {
                    case 1:
                        if (secondLoaderIdObject.exists())
                        {
                            Timestamp loaderYes = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name, "Loader Appear:YES Time: "  + loaderYes);
                            Log.d(GlobalVariables.Tag_Name, "Calculation"  + stopWatch.getTime());
                            loadingTime = stopWatch.getTime();
                            device.pressBack();
                            secBufferRead();
                        }
                        break;
                    case 2:
                        Timestamp loaderNo = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Loader Appear:NO Time: " + loaderNo);
                        break;
                    default:
                        Log.d(GlobalVariables.Tag_Name, "Loading...");
                }
            }
            return true;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Buffer Reading");
            return false;
        }
    }

    private boolean secBufferRead() {
        try {
            Log.d(GlobalVariables.Tag_Name, "Buffering second check");
            Log.d(GlobalVariables.Tag_Name, "Calculation"  + (GlobalVariables.OneMin_Timeout - loadingTime));

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= (GlobalVariables.OneMin_Timeout - loadingTime)) {
                UiSelector viewersSelector = new UiSelector().resourceId("im.kumu.ph:id/llayout_avatar");
                UiObject viewersObject = device.findObject(viewersSelector);

                UiSelector checkConnectionSelector = new UiSelector().resourceId("im.kumu.ph:id/tv_spotlight_desc");
                UiObject checkConnectionObject = device.findObject(checkConnectionSelector);

                UiSelector checkConnectionSelector2 = new UiSelector().resourceId("im.kumu.ph:id/recyclerview");
                UiObject checkConnectionObject2 = device.findObject(checkConnectionSelector2);
                if (viewersObject.exists())
                {
                    viewersObject.click();
                    if (checkConnectionObject.exists())
                    {
                        Timestamp loading = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Loader Appear:NO Time: "  + loading);
                        device.pressBack();
                    }
                    else
                    {
                        Timestamp loading = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Loader Appear:YES Time: "  + loading);
                    }
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
            Thread.sleep(5000);
            double TTLH = (HPT.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double SRTi = (SRT.getTime() - EST.getTime()) / 1000.0;
            double TTLV = (VLT.getTime() - CST.getTime()) / 1000.0;
            double TTLL = (LBT.getTime() - LVT.getTime()) / 1000.0;
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
