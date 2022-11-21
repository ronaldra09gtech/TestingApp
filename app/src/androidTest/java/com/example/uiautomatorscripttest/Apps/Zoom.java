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


public class Zoom {
    private Timestamp lt, st, vt;
    private UiDevice device;
    public int testRun(
            UiDevice device) {
        boolean runTest = true;
        try {
            this.device = device;

            runTest = ContinueClickSplash();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Continue Button");
                return 1;
            }

            runTest = Homepage();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Home Page Element");
                return 1;
            }

            Thread.sleep(5000);
            runTest = JoinMeeting();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Join Meeting Button");
                return 1;
            }

            runTest = EnterMeetingId();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Enter Meeting ID");
                return 1;
            }

            runTest = SeacrhGo();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Go Button");
                return 1;
            }

            runTest = startMeeting();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Start Meeting Button");
                return 1;
            }

            Thread.sleep(2000);
            runTest = EnterPassword();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Enter Meeting Password");
                return 1;
            }

            runTest = clickOk();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find OK Button");
                return 1;
            }

            runTest = LoaderAppear();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Join Meeting");
                return 1;
            }

            runTest = EndMeeting();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find End Meeting Button");
                return 1;
            }

            runTest = ConfirmEndMeeting();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Confirm End Meeting Button");
                return 1;
            }

            tearDown();

            return 0;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Test Run");
        }
        return 0;
    }

    private boolean Homepage() {
        try {
            boolean Homepage = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find continue button with Attempt no." + (i + 1));

                    //  UiCollection searchSelector = new UiCollection(new
                    // UiSelector().resourceId("us.zoom.videomeetings:id/rooted_warning_dialog_continue_btn"));
                    UiSelector searchId = new UiSelector().resourceId("us.zoom.videomeetings:id/btnStart");
                    UiObject searchAppear = device.findObject(searchId);
                    if (searchAppear.waitForExists(GlobalVariables.Wait_Timeout)) {

                        st = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, GlobalVariables.Search_Appear_Time + st);

                        Homepage = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Homepage Button Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return Homepage;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Homepage Button Method");
            return false;
        }
    }

    private boolean JoinMeeting() {
        try {
            boolean joinmeeting = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find JoinMeeting button with Attempt no." + (i + 1));

                    //  UiCollection searchSelector = new UiCollection(new
                    // UiSelector().resourceId("us.zoom.videomeetings:id/rooted_warning_dialog_continue_btn"));
                    UiSelector joinmeet = new UiSelector().resourceId("us.zoom.videomeetings:id/btnJoin");
                    UiObject joinButton = device.findObject(joinmeet);
                    if (joinButton.waitForExists(GlobalVariables.Wait_Timeout) && joinButton.isEnabled()) {
                        joinButton.click();
                        Log.d(GlobalVariables.Tag_Name,"Clicked on join meeting "+new Timestamp(new Date().getTime()));
                        joinmeeting = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "JoinMeeting Button Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return joinmeeting;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding JoinMeeting Button Method");
            return false;
        }
    }

    private boolean ContinueClickSplash() {
        try {
            boolean clickContinue = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find continue button with Attempt no." + (i + 1));

                    //  UiCollection searchSelector = new UiCollection(new
                    // UiSelector().resourceId("us.zoom.videomeetings:id/rooted_warning_dialog_continue_btn"));
                    UiSelector searchId =
                            new UiSelector()
                                    .resourceId("us.zoom.videomeetings:id/rooted_warning_dialog_continue_btn");
                    UiObject searchAppear = device.findObject(searchId);
                    if (searchAppear.waitForExists(GlobalVariables.Wait_Timeout)) {
                        searchAppear.click();
                        Log.d(GlobalVariables.Tag_Name,"Clicked on warning dialog "+new Timestamp(new Date().getTime()));
                        clickContinue = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "continue Button Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return clickContinue;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding continue Button Method");
            return false;
        }
    }

    private boolean EnterMeetingId() {
        try {
            boolean enterMeeting = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find enterMeeting text with Attempt no." + (i + 1));

                    //  UiCollection searchSelector = new UiCollection(new
                    // UiSelector().resourceId("us.zoom.videomeetings:id/rooted_warning_dialog_continue_btn"));
                    UiSelector meetingId =
                            new UiSelector().resourceId("us.zoom.videomeetings:id/edtConfNumber");
                    UiObject enterMeetingId = device.findObject(meetingId);
                    if (enterMeetingId.waitForExists(GlobalVariables.Wait_Timeout)) {
                        enterMeetingId.click();
                        Log.d(GlobalVariables.Tag_Name,"Clicked on enter meeting Id"+new Timestamp(new Date().getTime()));
                        enterMeetingId.setText("508 5886 682");
                        enterMeeting = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "enterMeeting text Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return enterMeeting;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding enterMeeting Method");
            return false;
        }
    }

    private boolean SeacrhGo() {
        try {
            boolean searchGo = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to remove the keyboard  with Attempt no." + (i + 1));

                    device.pressBack();
                    device.removeWatcher("GO");
                    searchGo = true;
                    break;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "keyboard Remove Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return searchGo;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding keyboard Remove Method");
            return false;
        }
    }

    private boolean startMeeting() {
        try {
            boolean enterMeeting = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find StartMeeting button with Attempt no." + (i + 1));

                    //  UiCollection searchSelector = new UiCollection(new
                    // UiSelector().resourceId("us.zoom.videomeetings:id/rooted_warning_dialog_continue_btn"));
                    UiSelector meetingId = new UiSelector().resourceId("us.zoom.videomeetings:id/btnJoin");
                    UiObject enterMeetingId = device.findObject(meetingId);
                    if (enterMeetingId.waitForExists(GlobalVariables.Wait_Timeout)
                            && enterMeetingId.isClickable()) {
                        enterMeetingId.click();
                        //                        vt = new Timestamp(new Date().getTime());
                        //                        Log.d(GlobalVariables.Tag_Name,
                        // GlobalVariables.JoinMeetingClickTime + vt);
                        Log.d(GlobalVariables.Tag_Name,"Clicked on start meeting "+new Timestamp(new Date().getTime()));
                        enterMeeting = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "StartMeeting  Button Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return enterMeeting;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding StartMeeting  Method");
            return false;
        }
    }

    private boolean EnterPassword() {
        try {
            boolean enterPassword = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name, "Trying to enter password text with Attempt no." + (i + 1));
                    UiSelector meetingId =
                            new UiSelector().resourceId("us.zoom.videomeetings:id/edtPassword");
                    UiObject enterMeetingId = device.findObject(meetingId);
                    enterMeetingId.click();
                    if (enterMeetingId.waitForExists(GlobalVariables.Wait_Timeout)) {
                        enterMeetingId.click();
                        enterMeetingId.setText("hhL4X7");
                        Log.d(GlobalVariables.Tag_Name,"entered password  "+new Timestamp(new Date().getTime()));
                        enterPassword = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "enter password  Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return enterPassword;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding enterPassword Method");
            return false;
        }
    }

    private boolean clickOk() {
        try {
            boolean clickOk = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find Click ok button with Attempt no." + (i + 1));
                    UiSelector meetingId = new UiSelector().resourceId("us.zoom.videomeetings:id/button1");
                    UiObject enterMeetingId = device.findObject(meetingId);
                    if (enterMeetingId.waitForExists(GlobalVariables.Wait_Timeout)
                            && enterMeetingId.isClickable()) {
                        enterMeetingId.click();
                        vt = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, GlobalVariables.JoinMeetingClickTime + vt);
                        clickOk = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Click ok  Button Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return clickOk;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding clickOk  Method");
            return false;
        }
    }

    private boolean LoaderAppear() {
        try {

            UiSelector loaderId = new UiSelector().resourceId("us.zoom.videomeetings:id/txtConnecting");
            UiObject loader = device.findObject(loaderId);

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            int time = 0;
            int c = GlobalVariables.Delay_Time;

            while (stopWatch.getTime() <= GlobalVariables.Video_Run_Time) {
                if (loader.exists()) {
                    Timestamp loaderYes = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, GlobalVariables.Loading_Yes + loaderYes);
                } else {
                    Timestamp loaderNo = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, GlobalVariables.Loading_No + loaderNo);
                }

            }
            return true;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Buffer Reading");
            return false;
        }
    }

    private boolean EndMeeting() {
        try {
            boolean endMeet = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find EndMeeting button with Attempt no." + (i + 1));

                    //  UiCollection searchSelector = new UiCollection(new
                    // UiSelector().resourceId("us.zoom.videomeetings:id/rooted_warning_dialog_continue_btn"));
                    UiSelector meetingId = new UiSelector().resourceId("us.zoom.videomeetings:id/btnLeave");
                    UiObject enterMeetingId = device.findObject(meetingId);
                    if (enterMeetingId.waitForExists(GlobalVariables.Wait_Timeout)
                            && enterMeetingId.isClickable()) {
                        enterMeetingId.click();
                        Log.d(GlobalVariables.Tag_Name,"Clicked on end meeting  "+new Timestamp(new Date().getTime()));
                        endMeet = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "End Meeting Button Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return endMeet;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding End Meeting  Method");
            return false;
        }
    }

    private boolean ConfirmEndMeeting() {
        try {
            boolean confirmEnd = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find ConfirmEndMeeting button with Attempt no." + (i + 1));

                    //  UiCollection searchSelector = new UiCollection(new
                    // UiSelector().resourceId("us.zoom.videomeetings:id/rooted_warning_dialog_continue_btn"));
                    UiSelector meetingId =
                            new UiSelector().resourceId("us.zoom.videomeetings:id/btnLeaveMeeting");
                    UiObject enterMeetingId = device.findObject(meetingId);
                    if (enterMeetingId.waitForExists(GlobalVariables.Wait_Timeout)
                            && enterMeetingId.isClickable()) {
                        enterMeetingId.click();
                        Log.d(GlobalVariables.Tag_Name,"Clicked on confirm end meeting  "+new Timestamp(new Date().getTime()));
                        confirmEnd = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "ConfirmEndMeeting  Button Failed" + new Timestamp(new Date().getTime()));
                }
            }
            return confirmEnd;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding ConfirmEndMeeting  Method");
            return false;
        }
    }

    private String getCommand(String appPackage) {
        return "am force-stop " + appPackage;
    }

    public void tearDown() {
        try {
            Thread.sleep(GlobalVariables.LAUNCH_TIMEOUT);
            device.executeShellCommand(getCommand(GlobalVariables.ZoomPackage));
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in closing the app");
        }
    }
}

