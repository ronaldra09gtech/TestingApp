package com.example.uiautomatorscripttest.Apps;

import android.graphics.Rect;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;
import android.view.KeyEvent;


import androidx.constraintlayout.solver.GoalRow;

import com.example.uiautomatorscripttest.ExampleInstrumentedTest;
import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import com.example.uiautomatorscripttest.Utility.DataHolder;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class GlobeOne {

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
            runTest = enterPIN();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Enter PIN");
                return 1;
            }
            runTest = dashboardDisplayed();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Display Dashboard");
                return 1;
            }
            runTest = addMoreData();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Go To Add More Data");
                return 1;
            }
            runTest = addMoreDataDisplayed();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Display Add More Data");
                return 1;
            }
            runTest = gotoSidebar();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Go To SideBar");
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
            device.executeShellCommand(getCommand(GlobalVariables.GlobeOne_Package));
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

                UiSelector homeElementId = new UiSelector().descriptionContains("Pin");
                UiObject homeElement = device.findObject(homeElementId);

                if (homeElement.exists()) {
                    HPT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Home Elements Appear Time:" + HPT);
                    homeElementFound = true;
                    Thread.sleep(5000);
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

    public boolean enterPIN() {
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Entering the PIN ");
                UiSelector firstSelector = new UiSelector().className("android.widget.TextView");
                UiObject firstObject = device.findObject(firstSelector);

                if (firstObject.exists()) {
                    device.pressKeyCode(KeyEvent.KEYCODE_1);
                    Thread.sleep(2000);
                }
                else {
                    GTDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Entered All PIN successfully: " + GTDT);
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return false;
        }
    }

    private boolean dashboardDisplayed() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Dashboard Loading");

                UiSelector firstSelector = new UiSelector().textContains("Load Balance");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().descriptionContains("Total balance");
                UiObject secObject = device.findObject(secSelector);

                UiSelector adsSelector = new UiSelector().descriptionContains("btn-close");
                UiObject adsObject = device.findObject(adsSelector);

                if(firstObject.exists() || secObject.exists() || adsObject.exists())
                {
                    if (adsObject.exists())
                    {
                        device.pressBack();
                    }
                    DDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Dashboard Displayed Time: " + DDT);
                    value = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Displaying Dashboard");
            return false;
        }
        return value;
    }

    private boolean addMoreData() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            Thread.sleep(3000);
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to click Add More Data");

                UiSelector selector = new UiSelector().textContains("Add More Data");
                UiObject object = device.findObject(selector);

                UiScrollable scroll = new UiScrollable(new UiSelector().className("android.widget.ScrollView"));

                if (object.exists()) {
                    object.click();
                    GMDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Add More Data Successfully Time: " + GMDT);
                    value = true;
                    break;
                }
                else
                {
                    scroll.scrollTextIntoView("Add More Data");
                    scroll.click();
                    if (!object.exists())
                    {
                        GMDT = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name,"Clicked Add More Data Successfully Time: " + GMDT);
                        value = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clicking Add More Data");
            return false;
        }
        return value;
    }

    private boolean addMoreDataDisplayed() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Displaying Add More Data");

                UiSelector firstSelector = new UiSelector().textContains("Balance");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().descriptionContains("offer description");
                UiObject secObject = device.findObject(secSelector);

                if (firstObject.exists() || secObject.exists()) {
                    GMDDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Add More Data Displayed Time: " + GMDDT);
                    value = true;
                    Thread.sleep(3000);
                    device.pressBack();
                    Thread.sleep(3000);
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Add More Data");
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Loading Add More Data");
            return value;
        }
        return value;
    }

    private boolean gotoSidebar() {
        boolean value = false;
        try {
            Log.d(GlobalVariables.Tag_Name, "Trying to click Drawer");

            UiSelector selector = new UiSelector().descriptionContains("Menu Icon4");
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

                UiSelector firstSelector = new UiSelector().descriptionContains("Go To Page Loan Load");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().descriptionContains("Go To Page");
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

    private boolean gotoHelpAndSupport() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Click Help And Support");

                UiSelector firstSelector = new UiSelector().descriptionContains("Go To Page Help & Support");
                UiObject firstObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().descriptionContains("Help & Support");
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

                UiSelector selector = new UiSelector().className("android.widget.EditText");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().descriptionContains("Main account name");
                UiObject objectSec = device.findObject(selectorSec);
                if (object.exists() || objectSec.exists()) {
                    HASDT = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Help And Support Displayed: " + HASDT);
                    value = true;
                    Thread.sleep(3000);
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
