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

public class GlobeAtHome {

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
            runTest = gotoDashboard();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To go to Dashboard");
                return 1;
            }
            runTest = dashboardDisplayed();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Display Dashboard");
                return 1;
            }
            runTest = gotoSidebar();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Go To SideBar");
                return 1;
            }
            runTest = gotoGetMoreData();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Go To Get More Data");
                return 1;
            }
            runTest = getMoreDisplayed();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Display Get More Data");
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
            device.executeShellCommand(getCommand(GlobalVariables.GlobeAtHome_Package));
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

                UiSelector homeElementId = new UiSelector().resourceId("ph.com.globe.globeathome:id/display_name");
                UiObject homeElement = device.findObject(homeElementId);

                UiSelector homeElementId2= new UiSelector().resourceId("ph.com.globe.globeathome:id/data_usage");
                UiObject homeElement2 = device.findObject(homeElementId2);

                UiSelector homeElementId3= new UiSelector().resourceId("ph.com.globe.globeathome:id/title_small");
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

    private boolean gotoDashboard() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.HalfMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Go to Dashboard");

                UiSelector firstSelector = new UiSelector().textContains("DASHBOARD");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().resourceId("ph.com.globe.globeathome:id/link_view_dashboard");
                UiObject secObject = device.findObject(secSelector);

                if(firstObject.exists())
                {
                    firstObject.click();
                    GTDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Dashboard Successfully Time : " + GTDT);
                    value = true;
                    break;
                }
                if(secObject.exists())
                {
                    secObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Clicked Dashboard Successfully Time : " + unUsableTime);
                    value = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Dashboard");
            return false;
        }
        return value;
    }

    private boolean dashboardDisplayed() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Dashboard Loading");

                UiSelector firstSelector = new UiSelector().textContains("PhP");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().resourceId("ph.com.globe.globeathome:id/load_balance");
                UiObject secObject = device.findObject(secSelector);
                if(firstObject.exists() || secObject.exists())
                {
                    DDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Dashboard Displayed Time: " + DDT);
                    value = true;
                    Thread.sleep(3000);
                    device.pressBack();
                    Thread.sleep(3000);
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Displaying Dashboard");
            return false;
        }
        return value;
    }

    private boolean gotoSidebar() {
        boolean value = false;
        try {
            Log.d(GlobalVariables.Tag_Name, "Trying to click Drawer");

            UiSelector selector = new UiSelector().resourceId("ph.com.globe.globeathome:id/drawer");
            UiObject object = device.findObject(selector);

            if (object.waitForExists(GlobalVariables.OneMin_Timeout)) {
                object.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Drawer Successfully Time: " + unUsableTime);
                value = true;
                sidebarDisplayed();
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Drawer");
            return false;
        }
        return value;
    }

    private boolean sidebarDisplayed() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Displaying Sidebar");

                UiSelector firstSelector = new UiSelector().resourceId("ph.com.globe.globeathome:id/nav_moredata");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().textContains("Get more data");
                UiObject secObject = device.findObject(secSelector);
                if (firstObject.exists() || secObject.exists()) {
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Sidebar Displayed Time: " + unUsableTime);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Sidebar");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Loading Sidebar");
            return value;
        }
        return value;
    }

    private boolean gotoGetMoreData() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Click Get More Data");

                UiSelector firstSelector = new UiSelector().resourceId("ph.com.globe.globeathome:id/nav_moredata");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().textContains("Get more data");
                UiObject secObject = device.findObject(secSelector);
                if (firstObject.exists()) {
                    firstObject.click();
                    GMDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Click First Get More Successfully Time: " + GMDT);
                    value = true;
                    break;
                }
                else if (secObject.exists()) {
                    secObject.click();
                    GMDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Click Second Get More Successfully Time: " + GMDT);
                    value = true;
                    break;
                }
                else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Get More");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Get more");
            return value;
        }
        return value;
    }
    private boolean getMoreDisplayed() {
        boolean value = false;
        try {

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Display Get more Page");

                UiSelector selector = new UiSelector().resourceId("ph.com.globe.globeathome:id/tv_headerTitle");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("ph.com.globe.globeathome:id/container");
                UiObject objectSec = device.findObject(selectorSec);

                UiSelector selectorThird = new UiSelector().resourceId("ph.com.globe.globeathome:id/btn_negative");
                UiObject objectThird = device.findObject(selectorThird);

                UiSelector selectorFourth = new UiSelector().className("android.widget.TextView");
                UiObject objectFourth = device.findObject(selectorFourth);
                if (object.exists() || objectSec.exists() || objectThird.exists() || objectFourth.exists()) {
                    if (objectThird.exists())
                    {
                        objectThird.click();
                    }
                    GMDDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Get More Page Displayed: " + GMDDT);
                    value = true;
                    Thread.sleep(3000);
                    device.pressBack();
                    Thread.sleep(3000);
                    gotoSidebar();
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error In Displaying Get More Page");
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

                UiSelector secSelector = new UiSelector().textContains("Help and Support");
                UiObject secObject = device.findObject(secSelector);
                if (firstObject.exists()) {
                    firstObject.click();
                    HAST = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Click First Help and Support Successfully Time: " + HAST);
                    value = true;
                    break;
                }
                else if (secObject.exists()) {
                    secObject.click();
                    HAST = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Click Second Help and Support Successfully Time: " + HAST);
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

                UiSelector selector = new UiSelector().resourceId("ph.com.globe.globeathome:id/hi_tv");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("ph.com.globe.globeathome:id/messenger_btn");
                UiObject objectSec = device.findObject(selectorSec);
                if (object.exists() || objectSec.exists()) {
                    HASDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Help And Support Displayed: " + HASDT);
                    value = true;
                    device.pressBack();
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
