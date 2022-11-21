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

public class NewGlobeOne {

    private Timestamp HPT,HASDT,HAST,GMDDT,GMDT,DDT,GTDT;
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
            runTest = gotoAccount();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To go to Account Page");
                return 1;
            }
            runTest = accountDisplayed();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Display Account Page");
                return 1;
            }
            runTest = gotoShop();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Go To Shop");
                return 1;
            }
            runTest = shopDisplayed();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Display Shop");
                return 1;
            }
            runTest = gotoHelpAndSupport();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Go To Help And Support");
                return 1;
            }
            runTest = helpAndSupportDisplayed();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Display Help And Support");
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
            device.executeShellCommand(getCommand(GlobalVariables.NewGlobeOne_Package));
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

                UiSelector homeElementId = new UiSelector().resourceId("ph.com.globe.globeonesuperapp:id/iv_user");
                UiObject homeElement = device.findObject(homeElementId);

                UiSelector homeElementId2= new UiSelector().resourceId("ph.com.globe.globeonesuperapp:id/tv_name");
                UiObject homeElement2 = device.findObject(homeElementId2);

                UiSelector homeElementId3= new UiSelector().resourceId("ph.com.globe.globeonesuperapp:id/cl_header");
                UiObject homeElement3 = device.findObject(homeElementId3);

                if (homeElement.exists() || homeElement2.exists() || homeElement3.exists()) {
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

    private boolean gotoAccount() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.HalfMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Go to Account");

                UiSelector firstSelector = new UiSelector().resourceId("ph.com.globe.globeonesuperapp:id/tv_nickname");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().resourceId("ph.com.globe.globeathome:id/link_view_dashboard");
                UiObject secObject = device.findObject(secSelector);

                if(firstObject.exists())
                {
                    firstObject.click();
                    GTDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Account Successfully Time : " + GTDT);
                    value = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Account");
            return false;
        }
        return value;
    }

    private boolean accountDisplayed() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Account Loading");

                UiSelector firstSelector = new UiSelector().resourceId("ph.com.globe.globeonesuperapp:id/tv_brand_label");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().resourceId("ph.com.globe.globeonesuperapp:id/tv_balance_amount");
                UiObject secObject = device.findObject(secSelector);
                if(firstObject.exists() || secObject.exists())
                {
                    DDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Account Displayed Time: " + DDT);
                    value = true;
                    device.pressBack();
                    Thread.sleep(3000);
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Displaying Account");
            return false;
        }
        return value;
    }

    private boolean gotoShop() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Click Shop");

                UiSelector firstSelector = new UiSelector().resourceId("ph.com.globe.globeathome:id/nav_moredata");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().textContains("Shop");
                UiObject secObject = device.findObject(secSelector);
                if (secObject.exists()) {
                    secObject.click();
                    GMDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Click Shop Successfully Time: " + GMDT);
                    value = true;
                    break;
                }
                else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Shop");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Shop");
            return value;
        }
        return value;
    }
    private boolean shopDisplayed() {
        boolean value = false;
        try {

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Display Shop");

                UiSelector selector = new UiSelector().resourceId("ph.com.globe.globeonesuperapp:id/iv_background");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("ph.com.globe.globeonesuperapp:id/cl_brand_icons");
                UiObject objectSec = device.findObject(selectorSec);

                UiSelector selectorThird = new UiSelector().className("android.widget.EditText");
                UiObject objectThird = device.findObject(selectorThird);

                if (object.exists() || objectSec.exists() || objectThird.exists()) {
                    GMDDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Shop Page Displayed: " + GMDDT);
                    value = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error In Displaying Shop Page");
        }
        return value;
    }

    private boolean gotoHelpAndSupport() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Click Help And Support");

                UiSelector firstSelector = new UiSelector().resourceId("ph.com.globe.globeathome:id/nav_help_support");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().textContains("Help");
                UiObject secObject = device.findObject(secSelector);
                if (secObject.exists()) {
                    secObject.click();
                    HAST = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Click Help and Support Successfully Time: " + HAST);
                    value = true;
                    break;
                }
                else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Help and Support");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Help and Support");
            return value;
        }
        return value;
    }
    private boolean helpAndSupportDisplayed() {
        boolean value = false;
        try {

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Display Help And Support");

                UiSelector selector = new UiSelector().className("android.widget.TextView");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().className("android.widget.ImageView");
                UiObject objectSec = device.findObject(selectorSec);
                if (object.exists() || objectSec.exists()) {
                    HASDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Help And Support Displayed: " + HASDT);
                    value = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error In Displaying Help And Support");
        }
        return value;
    }

    private boolean kpiCalculation() {
        try {
            Thread.sleep(5000);
            double TTLH = (HPT.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double TTLD = (DDT.getTime() - GTDT.getTime()) / 1000.0;
            double TTLGMD = (GMDDT.getTime() - GMDT.getTime()) / 1000.0;
            double TTLHAS = (HASDT.getTime() - HAST.getTime()) / 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page = " + TTLH);
            Log.d(GlobalVariables.Tag_Name, "Time To Load Dashboard = " + TTLD);
            Log.d(GlobalVariables.Tag_Name, "Time To Load Add Data Page = " + TTLGMD);
            Log.d(GlobalVariables.Tag_Name, "Time To Load Support Page = " + TTLHAS);
            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}
