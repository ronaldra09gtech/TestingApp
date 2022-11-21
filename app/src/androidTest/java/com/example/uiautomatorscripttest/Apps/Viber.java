package com.example.uiautomatorscripttest.Apps;

import android.content.Context;
import android.util.Log;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;


import com.example.uiautomatorscripttest.ExampleInstrumentedTest;
import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import com.example.uiautomatorscripttest.Utility.DataHolder;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Timestamp;
import java.util.Date;

public class Viber  {

    private Timestamp lt, heat, st, est, sendMessageTime, messageSentTime, sendFileTime, fileSentTime, sendLocationTime, locationSentTime;
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

            runTest = selectContact();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Select Contact");
                return 1;
            }

            Thread.sleep(5000);

            runTest = chatsMoreOptionsButton();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find More Options Button");
                return 1;
            }

            runTest = clearChatContent();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Clear Chat Content");
                return 1;
            }

            runTest = confirmClearChatContent();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Confirm Clear Chat Content");
                return 1;
            }

            runTest = clickBackButton();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Back Button");
                return 1;
            }

            runTest = enterTextMessage();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Enter Text Message");
                return 1;
            }

            runTest = clickOnSend();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Send Button");
                return 1;
            }

            runTest = messageStatus();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Message Status");
                return 1;
            }

            Thread.sleep(5000);

            runTest = selectMessageToDelete();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Select Message");
                return 1;
            }

            runTest = clickOnDelete();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Delete Button");
                return 1;
            }

            runTest = clickOnConfirmDelete();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Confirm Delete Button");
                return 1;
            }

            Thread.sleep(5000);

            runTest = moreActionButton();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find More Action Button");
                return 1;
            }

            runTest = clickOnSendFile();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Send File Button");
                return 1;
            }

            runTest = filesSearchButtonAppear();
            if (!runTest) {
                runTest = selectDownload();
                if (!runTest) {
                    DataHolder.getInstance().setFailureReason("Unable To Select Download Folder");
                    return 1;
                }

                runTest = selectFolder();
                if (!runTest) {
                    DataHolder.getInstance().setFailureReason("Unable To Select ViberFiles Folder");
                    return 1;
                }

            }
            else {
                runTest = clickFilesSearchButton();
                if (!runTest) {
                    DataHolder.getInstance().setFailureReason("Unable To Find Files Search Button");
                    return 1;
                }

                runTest = enterFilesSearch();
                if (!runTest) {
                    DataHolder.getInstance().setFailureReason("Unable To Enter Files Search Text");
                    return 1;
                }
                runTest = gotoFolder();
                if (!runTest) {
                    DataHolder.getInstance().setFailureReason("Unable To Go To Folder");
                    return 1;
                }
            }

            Thread.sleep(5000);

            runTest = selectFile();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Select File");
                return 1;
            }

            runTest = fileMessageStatus();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find File Message Status");
                return 1;
            }

            Thread.sleep(5000);

            runTest = selectMessageToDelete();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Select Message");
                return 1;
            }

            runTest = clickOnDelete();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Delete Button");
                return 1;
            }

            runTest = clickOnConfirmDelete();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Confirm Delete Button");
                return 1;
            }

            Thread.sleep(5000);

            runTest = clickOnSendLocation();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Send Location Button");
                return 1;
            }

            Thread.sleep(5000);

            runTest = clickOnSendLoc();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Send Location Button");
                return 1;
            }

            runTest = locationMessageStatus();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Location Message Status");
                return 1;
            }

            runTest = chatsMoreOptionsButton();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find More Options Button");
                return 1;
            }

            runTest = clearChatContent();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Clear Chat Content");
                return 1;
            }

            runTest = confirmClearChatContent();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Confirm Clear Chat Content");
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
            device.executeShellCommand(getCommand(GlobalVariables.Viber_Package));
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in closing the app");
        }
    }

    private boolean homeElement() {
        boolean value = false;

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector homeElementId =
                        new UiSelector().resourceId("com.viber.voip:id/date");
                UiObject homeElement = device.findObject(homeElementId);
                if (homeElement.exists()) {
                    heat = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Home Elements Appear Time:" + heat);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Home Page Elements");
                    Thread.sleep(200);
                }
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in finding Home Element");
            return value;
        }
        return value;
    }

    private boolean searchAppear() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name, "Trying to find search button with Attempt no." + (i + 1));

                    UiSelector searchId = new UiSelector().resourceId("com.viber.voip:id/menu_search");
                    UiObject searchAppear = device.findObject(searchId);

                    if (searchAppear.waitForExists(GlobalVariables.Wait_Timeout) && searchAppear.isClickable()) {
                        searchAppear.click();
                        st = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Search Button Appear Time: " + st);
                        value = true;
                        break;
                    }
                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in search Appear");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding search Button Method");
            return false;
        }
    }

    private boolean enterSearch() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(GlobalVariables.Tag_Name, "Trying to enter Search with Attempt no." + (i + 1));
                    UiSelector enterSearchField =
                            new UiSelector()
                                    .resourceId("com.viber.voip:id/search_src_text");
                    UiObject enterSearch_text = device.findObject(enterSearchField);

                    if (enterSearch_text.waitForExists(GlobalVariables.Wait_Timeout)) {
                        enterSearch_text.click();
                        enterSearch_text.setText(" Aquamark");
                        device.pressEnter();
                        est = new Timestamp(new Date().getTime());
                        Log.d(
                                GlobalVariables.Tag_Name,
                                "Enter Search Time: " + est);
                        value = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Enter Search Text Failed");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in entering Search Text");
            return false;
        }
    }

    private boolean selectContact() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                Log.d(GlobalVariables.Tag_Name, "Trying to select contact with Attempt no.:" + (i + 1));
                try {
                    UiSelector selector = new UiSelector().textContains("Globe Aquamark");
                    UiObject object = device.findObject(selector);

                    if (object.waitForExists(GlobalVariables.Wait_Timeout)) {
                        object.click();
                        Log.d(GlobalVariables.Tag_Name, "Select Contact Time:" + new Timestamp(new Date().getTime()));
                        value = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Select Contact Failed");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Selecting Contact");
            return false;
        }
    }

    private boolean enterTextMessage() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(GlobalVariables.Tag_Name, "Trying to enter Search with Attempt no." + (i + 1));
                    UiSelector selector =
                            new UiSelector()
                                    .resourceId("com.viber.voip:id/send_text");
                    UiObject object = device.findObject(selector);

                    if (object.waitForExists(GlobalVariables.Wait_Timeout)) {
                        object.click();
                        object.setText("Sending Viber Text Message");
                        est = new Timestamp(new Date().getTime());
                        Log.d(
                                GlobalVariables.Tag_Name,
                                "Entered Text Message Time:" + est);
                        value = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Enter Text Message Failed");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in entering Text Message");
            return false;
        }
    }

    private boolean clickOnSend() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                Log.d(GlobalVariables.Tag_Name, "Trying to send message with Attempt no.:" + (i + 1));
                try {

                    UiSelector selector =
                            new UiSelector().resourceId("com.viber.voip:id/btn_send");
                    UiObject object = device.findObject(selector);

                    if (object.waitForExists(5000)) {
                        object.click();
                        sendMessageTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click On Send Message Button Time:" + sendMessageTime);
                        value = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Click on Send Button Failed");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Send Button");
            return false;
        }
    }

    private boolean messageStatus() {
        boolean value = false;
        try {
            UiSelector selector =
                    new UiSelector().resourceId("com.viber.voip:id/statusView");
            UiObject object = device.findObject(selector);

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                if (object.exists()) {
                    messageSentTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Text Message Sent Time:" + messageSentTime);
                    value = true;
                    break;
                } else {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Loading Message Status");
                }
            }
            return value;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in getting Text Message Status");
            return value;
        }
    }

    private boolean selectMessageToDelete() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                Log.d(GlobalVariables.Tag_Name, "Trying to select message with Attempt no.:" + (i + 1));
                try {

                    UiSelector selector = new UiSelector().resourceId("com.viber.voip:id/statusView");
                    UiObject object = device.findObject(selector);

                    if (object.waitForExists(GlobalVariables.Wait_Timeout)) {
                        object.longClick();
                        Log.d(GlobalVariables.Tag_Name, "Select Message Time:" + new Timestamp(new Date().getTime()));
                        value = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Select Message Failed");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Selecting Message");
            return false;
        }
    }

    private boolean clickOnDelete() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Delete message with Attempt no.:" + (i + 1));
                try {

                    UiSelector selector =
                            new UiSelector().text("Delete");
                    UiObject object = device.findObject(selector);

                    if (object.waitForExists(5000)) {
                        object.click();
                        Timestamp vt = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click On Delete Message Button Time:" + vt);
                        value = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Click on Delete Button Failed");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Delete Button");
            return false;
        }
    }

    private boolean clickOnConfirmDelete() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Confirm Delete message with Attempt no.:" + (i + 1));
                try {

                    UiSelector selector =
                            new UiSelector().textContains("DELETE FOR EVERYONE");
                    UiObject object = device.findObject(selector);

                    UiSelector selector2 =
                            new UiSelector().textContains("DELETE FOR MYSELF");
                    UiObject object2 = device.findObject(selector2);

                    if (object.waitForExists(5000)) {
                        object.click();
                        Timestamp vt = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click On Confirm Delete Message Button Time:" + vt);
                        value = true;
                        break;
                    } else if (object2.waitForExists(5000)) {
                        object2.click();
                        Timestamp vt = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click On Confirm Delete Message Button Time:" + vt);
                        value = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Click on Confirm Delete Button Failed");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Confirm Delete Button");
            return false;
        }
    }

    private boolean moreActionButton() {
        boolean value = false;
        try {
            Log.d(GlobalVariables.Tag_Name, "Trying to click on More Action Button");

            UiSelector elementID = new UiSelector()
                    .resourceId("com.viber.voip:id/options_menu_open_extra_section");
            UiObject element = device.findObject(elementID);
            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked on More Action Button Time:" + new Timestamp(new Date().getTime()));
                value = true;
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Click on More Action Button Failed");
        }
        return value;
    }

    private boolean clickOnSendFile() {
        boolean value = false;
        try {

            Log.d(GlobalVariables.Tag_Name, "Trying to click on Send File Button");

            UiSelector elementID = new UiSelector()
                    .resourceId("com.viber.voip:id/extra_options_menu_send_file");
            UiObject element = device.findObject(elementID);
            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked on Send File Time:" + new Timestamp(new Date().getTime()));
                value = true;
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Click on Send File Failed");
        }
        return value;
    }

    private boolean selectDownload() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                Log.d(GlobalVariables.Tag_Name, "Trying to Select Download with Attempt no.:" + (i + 1));
                try {

                    UiSelector selector =
                            new UiSelector().textContains("Download");
                    UiObject object = device.findObject(selector);

                    if (object.waitForExists(5000)) {
                        object.click();
                        Timestamp vt = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Select Download Folder Time:" + vt);
                        value = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Select Download Folder Failed");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Download Folder");
            return false;
        }
    }

    private boolean selectFolder() {
        boolean value = false;
        try {

            Log.d(GlobalVariables.Tag_Name, "Trying to Select Folder Button");

            UiSelector elementID = new UiSelector().textContains("image5mb");
            UiObject element = device.findObject(elementID);

            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Selected Folder Time:" + new Timestamp(new Date().getTime()));
                Thread.sleep(2000);
                value = true;
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Select Folder Failed");
        }
        return value;
    }

    private boolean selectFile() {
        boolean value = false;
        try {

            Log.d(GlobalVariables.Tag_Name, "Trying to Select File Button");

            UiSelector elementID = new UiSelector().resourceId("com.google.android.documentsui:id/icon_thumb");
            UiObject element = device.findObject(elementID);

            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                sendFileTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name, "Selected File Time:" + sendFileTime);
                value = true;
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Select File Failed");
        }
        return value;
    }

    private boolean fileMessageStatus() {
        boolean value = false;
        try {
            UiSelector selector =
                    new UiSelector().resourceId("com.viber.voip:id/forwardRootView");
            UiObject object = device.findObject(selector);

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                if (object.exists()) {
                    fileSentTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "File Message Sent Time:" + fileSentTime);
                    value = true;
                    break;
                } else {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Loading Message Status");
                }
            }
            return value;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in getting File Message Status");
            return value;
        }
    }

    private boolean clickOnSendLocation() {
        boolean value = false;
        try {

            Log.d(GlobalVariables.Tag_Name, "Trying to click On Send Location Button");

            UiSelector elementID = new UiSelector()
                    .resourceId("com.viber.voip:id/extra_options_menu_send_location");
            UiObject element = device.findObject(elementID);

            UiSelector elementID1 = new UiSelector()
                    .resourceId("com.viber.voip:id/options_menu_open_extra_section");
            UiObject element1 = device.findObject(elementID1);

            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked on Send Location Time:" + new Timestamp(new Date().getTime()));
                value = true;
            } else {
                element1.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked on More Action Button Time:" + new Timestamp(new Date().getTime()));
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Click on Send Location Failed");
        }
        return value;
    }

    private boolean clickOnSendLoc() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                Log.d(GlobalVariables.Tag_Name, "Trying to send Location with Attempt no.:" + (i + 1));
                try {
                    UiObject object = new UiObject(new UiSelector().className("android.widget.ListView").className("android.widget.FrameLayout").instance(1).className("android.widget.FrameLayout").instance(5).className("android.widget.TextView"));

                    UiSelector firstSelector = new UiSelector().textContains("Send").clickable(true);
                    UiObject firstObject = device.findObject(firstSelector);

                    UiSelector thirdSelector = new UiSelector().descriptionContains("Send").className("android.widget.TextView");
                    UiObject thirdObject = device.findObject(thirdSelector);

                    UiSelector addressSe = new UiSelector().resourceId("com.viber.voip:id/txPlaceAddress");
                    UiObject addressOb = device.findObject(addressSe);

                    UiSelector pinnedAddSelector = new UiSelector().resourceId("com.viber.voip:id/mapLocationPin");
                    UiObject pinnedAddObject = device.findObject(pinnedAddSelector);

                    UiSelector secSelector = new UiSelector().resourceId("com.viber.voip:id/btnShareLocation");
                    UiObject secObject = device.findObject(secSelector);
                    if (addressOb.waitForExists(5000)) {
                        Log.d(GlobalVariables.Tag_Name, "Entered on first if");
                        if (firstObject.exists())
                        {
                            firstObject.click();
                            sendLocationTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name, "Click On 1 Send Location Button Time:" + sendLocationTime);
                            value = true;
                            break;
                        }
                        if (secObject.exists())
                        {
                            secObject.click();
                            sendLocationTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name, "Click On 2 Send Location Button Time:" + sendLocationTime);
                            value = true;
                            break;
                        }
                        if (thirdObject.exists())
                        {
                            thirdObject.click();
                            sendLocationTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name, "Click On 3 Send Location Button Time:" + sendLocationTime);
                            value = true;
                            break;
                        }
                    }
                    else if (pinnedAddObject.waitForExists(5000)) {
                        Log.d(GlobalVariables.Tag_Name, "Entered on second if");
                        if (firstObject.exists())
                        {
                            firstObject.click();
                            sendLocationTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name, "Click On 1 Send Location Button Time:" + sendLocationTime);
                            value = true;
                            break;
                        }
                        if (secObject.exists())
                        {
                            secObject.click();
                            sendLocationTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name, "Click On 2 Send Location Button Time:" + sendLocationTime);
                            value = true;
                            break;
                        }
                        if (thirdObject.exists())
                        {
                            thirdObject.click();
                            sendLocationTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name, "Click On 3 Send Location Button Time:" + sendLocationTime);
                            value = true;
                            break;
                        }
                    }
                    else
                    if (object.waitForExists(5000)) {
                        Log.d(GlobalVariables.Tag_Name, "Entered on else");
                        object.click();
                        sendLocationTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click On Send Location Button Time:" + sendLocationTime);
                        value = true;
                        break;
                    }

                } catch (Exception e) {
                    Log.d(GlobalVariables.Tag_Name, "Click on Send Location Button Failed");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Send Location Button");
            return false;
        }
    }

    private boolean locationMessageStatus() {
        boolean value = false;
        try {
            UiSelector selector =
                    new UiSelector().resourceId("com.viber.voip:id/forwardRootView");
            UiObject object = device.findObject(selector);

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                if (object.exists()) {
                    locationSentTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Location Message Sent Time:" + locationSentTime);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name,
                            "Loading Location Message Status");
                }
            }
            return value;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in getting Location Message Status");
            return value;
        }
    }

    private boolean chatsMoreOptionsButton() {
        boolean value = false;
        try {
            Log.d(GlobalVariables.Tag_Name, "Trying to click on Chats More Options Button");

            UiSelector elementID = new UiSelector()
                    .textContains(" Aquamark");
            UiObject element = device.findObject(elementID);

            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked on Chats More Options Button Time:" + new Timestamp(new Date().getTime()));
                value = true;
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Click on Chats More Options Button Failed");
        }
        return value;
    }

    private boolean clearChatContent() {
        boolean value = false;
        try {
            Log.d(GlobalVariables.Tag_Name, "Trying to click on Clear Chat Content Button");

            UiSelector elementID = new UiSelector()
                    .textContains("Clear Chat Content");
            UiObject element = device.findObject(elementID);

            UiScrollable scrollableID = new UiScrollable(new UiSelector().packageName("com.viber.voip"));
            scrollableID.scrollTextIntoView("Clear Chat Content");

            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked on Clear Chat Content Button Time:" + new Timestamp(new Date().getTime()));
                value = true;
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Click on Clear Chat Content Button Failed");
        }
        return value;
    }

    private boolean confirmClearChatContent() {
        boolean value = false;
        try {
            Log.d(GlobalVariables.Tag_Name, "Trying to click on Confirm Clear Chat Content Button");

            UiSelector elementID = new UiSelector()
                    .resourceId("android:id/button1");
            UiObject element = device.findObject(elementID);
            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked on Confirm Clear Chat Content Button Time:" + new Timestamp(new Date().getTime()));
                value = true;
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Click on Confirm Clear Chat Content Button Failed");
        }
        return value;
    }

    private boolean filesSearchButtonAppear() {
        boolean value = false;
        try {
            UiSelector elementId = new UiSelector().descriptionContains("Search");
            UiObject element = device.findObject(elementId);

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.HalfMin_Timeout) {
                if (element.exists()) {
                    Log.d(GlobalVariables.Tag_Name, "Files Search Button Appear Time:" + new Timestamp(new Date().getTime()));
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Files Search Button Loading");
                }
            }
        } catch (Exception e) {
            Log.d(
                    GlobalVariables.Tag_Name,
                    "Error in Files Search Button");
        }
        return value;
    }

    private boolean clickFilesSearchButton() {
        boolean value = false;
        try {
            UiSelector elementID = new UiSelector().descriptionContains("Search");
            UiObject element = device.findObject(elementID);

            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked On Files Search Button Time:" + new Timestamp(new Date().getTime()));
                value = true;
            }
        } catch (Exception e) {
            Log.d(
                    GlobalVariables.Tag_Name,
                    "Click On Search Button Failed");
        }
        return value;
    }

    private boolean enterFilesSearch() {
        boolean value = false;
        try {
            UiCollection element =
                    new UiCollection(new UiSelector().text("Search…"));

            if (element.waitForExists(GlobalVariables.Wait_Timeout)) {
                element.click();
                element.setText("image5mb");
                device.pressSearch();
                device.pressEnter();
                Log.d(GlobalVariables.Tag_Name, "Entered Files Search Text Time:" + new Timestamp(new Date().getTime()));
                value = true;
            }
        } catch (Exception e) {
            Log.d(
                    GlobalVariables.Tag_Name,
                    "Enter Files Search Button Failed");
        }
        return value;
    }

    private boolean gotoFolder() {
        boolean value = false;
        try {
            Thread.sleep(2000);
            UiSelector elementID = new UiSelector().textContains("image5mb").resourceId("android:id/title");
            UiObject element = device.findObject(elementID);

            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked On Folder Time:" + new Timestamp(new Date().getTime()));
                value = true;
            }
        } catch (Exception e) {
            Log.d(
                    GlobalVariables.Tag_Name,
                    "Click On Folder Failed");
        }
        return value;
    }

    private boolean clickBackButton() {
        boolean value = false;
        try {
            Log.d(GlobalVariables.Tag_Name, "Trying to click on Back Button");

            UiSelector elementID = new UiSelector()
                    .descriptionContains("Navigate up");
            UiObject element = device.findObject(elementID);
            if (element.waitForExists(GlobalVariables.OneMin_Timeout)) {
                element.click();
                Log.d(GlobalVariables.Tag_Name, "Clicked on Back Button Time:" + new Timestamp(new Date().getTime()));
                value = true;
            }

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Click on Back Button Failed");
        }
        return value;
    }

}
