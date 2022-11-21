package com.example.uiautomatorscripttest.Apps;


import android.graphics.Point;
import android.graphics.Rect;
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

public class MicrosoftTeams {

    private UiDevice device;
    Timestamp homeElementsAppearTime,clickSearchTime,enterSearchTime,messageDeliveredTime,messageSendTime,
            deliveredImageTime,clickSendImageTime,deliveredLocationTime ,clickCurrentLocationTime ;
    boolean sendLocation = false;
    boolean sendImage = false;


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
            runTest = checkExistingConvo();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to check existing convo");
                return 1;
            }
            runTest = searchContact();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Check Search Contact");
                return 1;
            }
            runTest = selectContact();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Check Select Contact");
                return 1;
            }
            runTest = clickMessageIcon();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Message Icon");
                return 1;
            }
            runTest = startMessage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Start Message");
                return 1;
            }
            runTest = enterMessageText();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Enter Message");
                return 1;
            }
            runTest = sendMessage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Send Message");
                return 1;
            }
            runTest = checkMessageSent();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Check Message");
                return 1;
            }
            runTest = attach();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Attach");
                return 1;
            }
            runTest = clickMedia();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Media");
                return 1;
            }
            runTest = openLibrary();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Open Gallery");
                return 1;
            }
            runTest = gotoGallery();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Gallery");
                return 1;
            }
            runTest = gotoDownloads();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go Downloads");
                return 1;
            }
            runTest = clickDownloads();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to click Downloads");
                return 1;
            }
            runTest = searchFoler();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to search Folder");
                return 1;
            }
            runTest = Image5mbDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Image5mb");
                return 1;
            }
            runTest = clickImage5mbDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Image5mb");
                return 1;
            }
            runTest = sendImage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Send Image");
                return 1;
            }
            runTest = deliveredPhotoDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Delivered Photo");
                return 1;
            }
            runTest = clickLocation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to click sticker Button");
                return 1;
            }
            runTest = sendLocation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to send location");
                return 1;
            }
            runTest = deliveredLocation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to delivered location");
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
            device.executeShellCommand(getCommand(GlobalVariables.MicrosoftTeams_Package));
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
                Log.d(GlobalVariables.Tag_Name, "Loading Home Screen");
                UiSelector selector = new UiSelector().resourceId("com.microsoft.teams:id/chat_item_container");
                UiObject object = device.findObject(selector);

                UiSelector secSelector = new UiSelector().resourceId("com.microsoft.teams:id/action_bar_search_action");
                UiObject secObject = device.findObject(secSelector);
                if (object.exists() || secObject.exists()) {
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

    public boolean checkExistingConvo(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
            Log.d(GlobalVariables.Tag_Name, "Checking..");
            UiSelector selectorSec = new UiSelector().textContains("Globe Aquamark");
            UiObject objectSec = device.findObject(selectorSec);

                UiSelector deleteSelector = new UiSelector().textContains("Delete");
                UiObject deleteObject = device.findObject(deleteSelector);

                UiSelector okSelector = new UiSelector().resourceId("android:id/button1");
                UiObject okObject = device.findObject(okSelector);

                if(objectSec.exists()){
                    objectSec.dragTo(objectSec,1);

                    Thread.sleep(2000);
                    if (deleteObject.exists())
                    {
                        deleteObject.click();

                        Thread.sleep(2000);
                        if (okObject.exists())
                        {
                            okObject.click();
                            Timestamp unUsableTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name,"Long Press Successfully Time:" + unUsableTime);
                            Thread.sleep(5000);
                            if (stopWatch.getTime() > 50000)
                            {
                                value = true;
                                break;
                            }
                        }
                    }
                }
                else
                {
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in long pressing");
        }
        return value;
    }

    public boolean searchContact(){
        boolean value = false;
        try{
            Log.d(GlobalVariables.Tag_Name, "Enter Contact");
            UiSelector selector = new UiSelector().resourceId("com.microsoft.teams:id/action_bar_search_action");
            UiObject object = device.findObject(selector);

            UiSelector selectorSec = new UiSelector().className("android.widget.EditText");
            UiObject objectSec = device.findObject(selectorSec);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                objectSec.setText("Globe Aquamark");
                enterSearchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Entered Contact Successfully Time:" + enterSearchTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Entering Contact text");
        }
        return value;
    }

    public boolean selectContact(){
        boolean value = false;
        try{
            Log.d(GlobalVariables.Tag_Name, "Selecting Contact");
            UiSelector selector = new UiSelector().descriptionContains("Globe Aquamark");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                enterSearchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Select Contact Successfully Time:" + enterSearchTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error In Selecting Contact");
        }
        return value;
    }

    public boolean clickMessageIcon(){
        boolean value = false;
        try{
            Log.d(GlobalVariables.Tag_Name, "Clicking Message Icon");
            UiSelector selector = new UiSelector().descriptionContains("Open private chat");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp contactSelectedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked icon successfully:" + contactSelectedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Contact Selected");
        }
        return  value;
    }

    public boolean startMessage(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().className("android.widget.EditText");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp contactSelectedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Contact Selected Appeared Time:" + contactSelectedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Contact Selected");
        }
        return  value;
    }

    public boolean enterMessageText(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().className("android.widget.EditText");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                object.setText("Sending Microsoft Teams Text Message");
                Timestamp enterTextTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Enter Text Message Successfully Time:" + enterTextTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Entering Text Message");
        }
        return  value;
    }

    public boolean sendMessage(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().resourceId("com.microsoft.teams:id/message_area_send_btn");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                if (sendImage == false)
                {
                    messageSendTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Message send Successfully Time:" + messageSendTime);
                    value = true;
                }
                else
                {
                    clickSendImageTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Send Successfully Time:" + clickSendImageTime);
                    value = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Sending Message");
        }
        return  value;
    }

    public boolean checkMessageSent(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector messageSent = new UiSelector().descriptionContains("Sent").resourceId("com.microsoft.teams:id/chat_message_sending_indicator");
                UiObject objectSent = device.findObject(messageSent);

                UiSelector messageLoading = new UiSelector().textContains("Loading");
                UiObject objectLoading = device.findObject(messageLoading);

                if(objectSent.exists() && objectLoading.exists() == false){
                    messageDeliveredTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Message Sent successfully Time:" + messageDeliveredTime);
                    value = true;
                    if (sendImage == false)
                    {
                        holdMessage();
                    }
                    break;
                }
            }
            return  value;

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Delivering Message");
        }
        return  value;
    }

    public boolean holdMessage(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector messageSent = new UiSelector().descriptionContains("Sending Microsoft Teams Text Message");
                UiObject objectSent = device.findObject(messageSent);

                UiSelector deleteIconSelector = new UiSelector().textContains("Delete");
                UiObject deleteIconObject = device.findObject(deleteIconSelector);

                UiSelector DELETESelector = new UiSelector().textContains("DELETE").resourceId("android:id/button1");
                UiObject DELETEObject = device.findObject(DELETESelector);

                objectSent.dragTo(objectSent,1);

                if(deleteIconObject.exists()){
                    deleteIconObject.click();
                    Thread.sleep(2000);

                    if (DELETEObject.exists())
                    {
                        DELETEObject.click();
                        Timestamp unUsableTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name,"Delete successfully Time:" + unUsableTime);
                        value = true;
                        break;
                    }
                }
            }
            return  value;

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Delivering Message");
        }
        return  value;
    }

    public boolean attach(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().resourceId("com.microsoft.teams:id/message_area_plus_btn");
                UiObject object = device.findObject(selector);

                UiSelector mediaSelector = new UiSelector().textContains("Media");
                UiObject mediaObject = device.findObject(mediaSelector);

                if(object.exists()){
                    object.click();
                    Timestamp ClickAttachTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Attach Button Successfully Time:" + ClickAttachTime);
                    value = true;

                    if (mediaObject.exists())
                    {
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Attach Button");
        }
        return  value;
    }
    public boolean clickMedia(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector messageSent = new UiSelector().textContains("Media");
                UiObject objectSent = device.findObject(messageSent);

                if(objectSent.exists()){
                    objectSent.click();
                    Timestamp galleryDisplayedTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Media successfully Time:" + galleryDisplayedTime);
                    value = true;
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Media");
        }
        return  value;
    }

    public boolean openLibrary(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().descriptionContains("library");
                UiObject object = device.findObject(selector);

                if(object.exists()){
                    object.click();
                    Timestamp galleryDisplayedTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Library successfully Time:" + galleryDisplayedTime);
                    value = true;
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Library");
        }
        return  value;
    }

    public boolean gotoGallery(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().descriptionContains("Gallery");
                UiObject object = device.findObject(selector);

                if(object.exists()){
                    object.click();
                    Timestamp galleryDisplayedTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Gallery successfully Time:" + galleryDisplayedTime);
                    value = true;
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Gallery");
        }
        return  value;
    }

    public boolean gotoDownloads(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("Show roots");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp ClickFolderTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Roots Successfully Time:" + ClickFolderTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Roots");
        }
        return  value;
    }

    public boolean clickDownloads(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Downloads").resourceId("android:id/title").index(0);
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp ClickFolderTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Downloads Successfully Time:" + ClickFolderTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Downloads");
        }
        return  value;
    }

    public boolean searchFoler(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("Search");
            UiObject object = device.findObject(selector);

            UiSelector textFieldSelector = new UiSelector().className("android.widget.EditText");
            UiObject textFieldObject = device.findObject(textFieldSelector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                textFieldObject.setText("image5mb");
                device.pressEnter();
                Timestamp ClickFolderTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Search Successfully Time:" + ClickFolderTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Folder");
        }
        return  value;
    }
    public boolean Image5mbDisplayed(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("image5mb").className("android.widget.TextView");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Thread.sleep(2000);
                object.click();
                Timestamp ClickFolderTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Image5mb Displayed Successfully Time:" + ClickFolderTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Folder");
        }
        return  value;
    }
    public boolean clickImage5mbDisplayed(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().resourceId("com.google.android.documentsui:id/icon_thumb");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp ClickFolderTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Image Successfully Time:" + ClickFolderTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Image");
        }
        return  value;
    }
    public boolean sendImage(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Done");
            UiObject object = device.findObject(selector);

            UiSelector selectorSec = new UiSelector().resourceId("com.microsoft.teams:id/gpuImageView");
            UiObject objectSec = device.findObject(selectorSec);

            if(objectSec.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                value = true;
                sendImage = true;
                sendMessage();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Image");
        }
        return  value;
    }
    public boolean deliveredPhotoDisplayed(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector messageSent = new UiSelector().descriptionContains("Sent").resourceId("com.microsoft.teams:id/chat_message_sending_indicator");
                UiObject objectSent = device.findObject(messageSent);
                if(objectSent.exists()){
                    deliveredImageTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Delivered Photo successfully Time:" + deliveredImageTime);
                    value = true;
                    attach();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Delivering Photo");
        }
        return  value;
    }
    public boolean clickLocation(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().textContains("Location");
                UiObject object = device.findObject(selector);

                if(object.exists()){
                    object.click();
                    Timestamp stickerTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Location button clicked successfully Time:" + stickerTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Location button");
        }
        return  value;
    }

    public boolean sendLocation(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().resourceId("com.microsoft.teams:id/share_nearby_place_location");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().textContains("Send your current location");
                UiObject objectSec= device.findObject(selectorSec);
                if(object.exists()){
                    objectSec.click();
                    clickCurrentLocationTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Location send successfully Time:" + clickCurrentLocationTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in sending Location button");
        }
        return  value;
    }

    public boolean deliveredLocation(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().resourceId("com.microsoft.teams:id/info");
                UiObject object = device.findObject(selector);

                UiSelector messageSent = new UiSelector().descriptionContains("Sent").resourceId("com.microsoft.teams:id/chat_message_sending_indicator");
                UiObject objectSent = device.findObject(messageSent);
                if(object.exists()){
                    if (objectSent.exists())
                    {
                        deliveredLocationTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name,"Delivered Location successfully Time:" + deliveredLocationTime);
                        value = true;
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Delivering Photo");
        }
        return  value;
    }

    private boolean kpiCalculation() {
        try {
            Thread.sleep(10000);
            double TTLH = (homeElementsAppearTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double MST = (messageDeliveredTime.getTime() - messageSendTime.getTime()) / 1000.0;
            double IST = (deliveredImageTime.getTime() - clickSendImageTime.getTime()) / 1000.0;
            double LST = (deliveredLocationTime.getTime() - clickCurrentLocationTime.getTime()) / 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time to Load Home Page = " + TTLH);
            Log.d(GlobalVariables.Tag_Name, "Time to Send Message = " + MST);
            Log.d(GlobalVariables.Tag_Name, "Time to Send 5Mb File = " + IST);
            Log.d(GlobalVariables.Tag_Name, "Time to Send Location = " + LST);
            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}

