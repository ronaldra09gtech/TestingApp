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

public class Hangouts {

    private UiDevice device;
    Timestamp homeElementsAppearTime,clickSearchTime,enterSearchTime,messageDeliveredTime,messageSendTime,
            deliveredImageTime,clickSendImageTime,deliveredLocationTime ,clickCurrentLocationTime ;
    boolean sendLocation = false;


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
            runTest = clickPlus();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Plus button");
                return 1;
            }
            runTest = checkExistingMessage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Check Existing Message");
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
            runTest = galleryDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Gallery");
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
            runTest = clickSticker();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to click sticker Button");
                return 1;
            }
            runTest = sendSticker();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to send sticker");
                return 1;
            }
            runTest = deliveredSticker();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to delivered sticker");
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
            device.executeShellCommand(getCommand(GlobalVariables.Hangouts_Package));
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
                UiSelector selector = new UiSelector().resourceId("com.google.android.talk:id/zero_state_image_container");
                UiObject object = device.findObject(selector);

                UiSelector secSelector = new UiSelector().textContains("Hangouts");
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

    public boolean clickPlus() {
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().descriptionContains("New conversation");
                UiObject object = device.findObject(selector);

                UiSelector secSelector = new UiSelector().resourceId("com.google.android.talk:id/floating_action_button");
                UiObject secObject = device.findObject(secSelector);

                if(object.exists()){
                    object.click();
                    clickSearchTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on Search Successfully Time:" + clickSearchTime);
                    value = true;
                    searchContact();
                    break;
                }
                if(secObject.exists())
                {
                    object.click();
                    clickSearchTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked on Search Successfully Time:" + clickSearchTime);
                    value = true;
                    searchContact();
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on clicking Search Button");
        }
        return value;
    }

    public boolean searchContact(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().resourceId("com.google.android.talk:id/participant_text_view");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.setText("smdstb@gmail.co");
                enterSearchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Entered Contact Successfully Time:" + enterSearchTime);
                value = true;
                selectContact();
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
            Thread.sleep(5000);
            UiSelector selector = new UiSelector().descriptionContains("smdstb@gmail.com");
            UiObject object = device.findObject(selector);

            UiSelector sendSelector = new UiSelector().descriptionContains("Send");
            UiObject sendObject = device.findObject(sendSelector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                if (sendObject.exists())
                {
                    enterSearchTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Select Contact Successfully Time:" + enterSearchTime);
                    value = true;
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name,"Entered in else");
                    searchContact();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error In Selecting Contact");
        }
        return value;
    }

    public boolean checkExistingMessage(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector checkMessage = new UiSelector().textContains("Sending Hangouts");
                UiObject message = device.findObject(checkMessage);

                if(message.waitForExists(3000)){
                    UiSelector threeDot = new UiSelector().descriptionContains("More options");
                    UiObject threeDotObj = device.findObject(threeDot);
                    if (threeDotObj.waitForExists(3000))
                    {
                        threeDotObj.click();
                        Thread.sleep(3000);
                        UiSelector more = new UiSelector().textContains("Options");
                        UiObject moreOjb = device.findObject(more);
                        if (moreOjb.waitForExists(3000))
                        {
                            moreOjb.click();
                            Thread.sleep(3000);
                            UiSelector clearAll = new UiSelector().textContains("Delete");
                            UiObject clearAllObj = device.findObject(clearAll);
                            if (clearAllObj.waitForExists(3000)) {
                                clearAllObj.click();
                                Thread.sleep(3000);
                                UiSelector clear = new UiSelector().textContains("DELETE").resourceId("android:id/button1");
                                UiObject clearObj = device.findObject(clear);
                                if (clearObj.waitForExists(3000)) {
                                    clearObj.click();
                                    Timestamp enterSearchTime = new Timestamp(new Date().getTime());
                                    Log.d(GlobalVariables.Tag_Name, "Clear Existing Message Successfully Time:" + enterSearchTime);
                                    value = true;
                                    if (!sendLocation)
                                    {
                                        clickPlus();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                else
                {
                    Timestamp enterSearchTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"No Existing Message:" + enterSearchTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error In Selecting Contact");
        }
        return value;
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
                object.setText("Sending Hangouts Text Message");
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

            UiSelector selector = new UiSelector().descriptionContains("Send");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                messageSendTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Message send Successfully Time:" + messageSendTime);
                value = true;
                device.pressBack();
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
                UiSelector messageSent = new UiSelector().textContains("Now");
                UiObject objectSent = device.findObject(messageSent);

                if(objectSent.exists()){
                    messageDeliveredTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Message Sent successfully Time:" + messageDeliveredTime);
                    value = true;
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

    public boolean attach(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().descriptionContains("Photos");
                UiObject object = device.findObject(selector);

                UiSelector secSelector = new UiSelector().resourceId("com.google.android.talk:id/gallerypicker_indicator_icon");
                UiObject secObject = device.findObject(secSelector);

                if(object.exists()){
                    object.click();
                    Timestamp ClickAttachTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Attach Button Successfully Time:" + ClickAttachTime);
                    value = true;
                    break;
                }
                if(secObject.exists()){
                    secObject.click();
                    Timestamp ClickAttachTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Attach Button Successfully Time:" + ClickAttachTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Attach Button");
        }
        return  value;
    }
    public boolean galleryDisplayed(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector messageSent = new UiSelector().descriptionContains("Choose photo");
                UiObject objectSent = device.findObject(messageSent);

                UiSelector secSelector = new UiSelector().resourceId("com.google.android.talk:id/floating_system_photo_picking_button");
                UiObject secObject = device.findObject(secSelector);

                if(objectSent.exists()){
                    objectSent.click();
                    Timestamp galleryDisplayedTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Gallery Displayed successfully Time:" + galleryDisplayedTime);
                    value = true;
                    break;
                }
                if(secObject.exists()){
                    secObject.click();
                    Timestamp galleryDisplayedTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Gallery Displayed successfully Time:" + galleryDisplayedTime);
                    value = true;
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Gallery");
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

            UiSelector selector = new UiSelector().textContains("Downloads").resourceId("android:id/title");
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

            UiSelector selector = new UiSelector().descriptionContains("Send");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                clickSendImageTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Send Successfully Time:" + clickSendImageTime);
                value = true;
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
                UiSelector messageSent = new UiSelector().textContains("Now");
                UiObject objectSent = device.findObject(messageSent);
                if(objectSent.exists()){
                    deliveredImageTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Delivered Photo successfully Time:" + deliveredImageTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Delivering Photo");
        }
        return  value;
    }
    public boolean clickSticker(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().descriptionContains("Stickers");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("com.google.android.talk:id/sticker_picker_indicator_icon");
                UiObject objectSec= device.findObject(selectorSec);

                if(object.exists()){
                    object.click();
                    Timestamp stickerTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"sticker button clicked successfully Time:" + stickerTime);
                    value = true;
                    break;
                }
                else if(objectSec.exists()){
                    objectSec.click();
                    Timestamp stickerTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"sticker button clicked successfully Time:" + stickerTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking sticker button");
        }
        return  value;
    }

    public boolean sendSticker(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().descriptionContains("sticker");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().resourceId("com.google.android.talk:id/gallery_item_image");
                UiObject objectSec= device.findObject(selectorSec);

                if(objectSec.exists()){
                    objectSec.click();
                    clickCurrentLocationTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"sticker send successfully Time:" + clickCurrentLocationTime);
                    value = true;
                    break;
                }
                else if(object.exists()){
                    object.click();
                    clickCurrentLocationTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"sticker send successfully Time:" + clickCurrentLocationTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in sending sticker button");
        }
        return  value;
    }

    public boolean deliveredSticker(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector messageSent = new UiSelector().textContains("Now");
                UiObject objectSent = device.findObject(messageSent);
                if(objectSent.exists()){
                    deliveredLocationTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Delivered Photo successfully Time:" + deliveredLocationTime);
                    value = true;
                    sendLocation = true;
                    checkExistingMessage();
                    break;
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

