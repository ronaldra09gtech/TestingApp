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

public class Telegram {

    private UiDevice device;
    Timestamp homeElementsAppearTime,clickSearchTime,enterSearchTime,messageDeliveredTime,messageSendTime,
            deliveredImageTime,clickSendImageTime,deliveredLocationTime ,clickCurrentLocationTime ;
    boolean deletePhoto = false;
    boolean deleteLocation = false;

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
            runTest = clickSearchButton();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Search bar");
                return 1;
            }
            runTest = searchContact();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Search Contact");
                return 1;
            }
            runTest = selectContact();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Select Contact");
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
            runTest = longclickMessage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Long Click Message");
                return 1;
            }
            runTest = displayDeleteButton();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display button");
                return 1;
            }
            runTest = deleteMessage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to click Delete Button");
                return 1;
            }
            runTest = alertDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to View Alert");
                return 1;
            }
            runTest = delete();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Delete for Everyone");
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
            runTest = clickGallery();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Gallery");
                return 1;
            }
            runTest = folderDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Folder");
                return 1;
            }
            runTest = clickFolder();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Folder");
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
            runTest = photoDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Photo");
                return 1;
            }
            runTest = clickPhoto();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Photo");
                return 1;
            }
            runTest = sendPhotoDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Send Button");
                return 1;
            }
            runTest = clickSend();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Send Button");
                return 1;
            }
            runTest = deliveredPhotoDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Delivered Photo");
                return 1;
            }
            runTest = longClickPhoto();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Long Click Photo");
                return 1;
            }
            runTest = locationDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Location Button");
                return 1;
            }
            runTest = clickLocation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Location Button");
                return 1;
            }
            runTest = sendCurrentLocationDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Current Location");
                return 1;
            }
            runTest = clickCurrentLocation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Current Location");
                return 1;
            }
            runTest = deliveredLocationDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Delivered Current Location");
                return 1;
            }
            runTest = longClickLocation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Long Click Current Location");
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
            device.executeShellCommand(getCommand(GlobalVariables.Telegram_Package));
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
                UiSelector selector = new UiSelector().descriptionContains("New Message");
                UiObject object = device.findObject(selector);
                if (object.exists()) {
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

    public boolean clickSearchButton() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().descriptionContains("Search");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Thread.sleep(5000);
                clickSearchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on Search Successfully Time:" + clickSearchTime);
                value = true;
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

            UiSelector selector = new UiSelector().textContains("Search");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                object.setText("Globe Aquam");
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
            UiSelector selector = new UiSelector().textContains("Globe Aquamark");

//            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/contact_row_container").index(0);
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

    public boolean checkExistingMessage(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector checkMessage = new UiSelector().descriptionContains("Sent");
                UiObject message = device.findObject(checkMessage);

                if(message.exists() || deleteLocation){
                    Thread.sleep(3000);
                    UiSelector threeDot = new UiSelector().descriptionContains("More options");
                    UiObject threeDotObj = device.findObject(threeDot);
                    if (threeDotObj.waitForExists(3000))
                    {
                        threeDotObj.click();
                        Thread.sleep(3000);
                        UiSelector more = new UiSelector().textContains("Clear history");
                        UiObject moreOjb = device.findObject(more);
                        if (moreOjb.waitForExists(3000))
                        {
                            moreOjb.click();
                            Thread.sleep(3000);
                            UiSelector clearAll = new UiSelector().className("android.widget.CheckBox");
                            UiObject clearAllObj = device.findObject(clearAll);
                            if (clearAllObj.waitForExists(3000)) {
                                clearAllObj.click();
                                Thread.sleep(3000);
                                UiSelector clear = new UiSelector().className("android.view.View").index(0).clickable(true).focusable(true);
                                UiObject clearObj = device.findObject(clear);
                                if (clearObj.waitForExists(3000)) {
                                    clearObj.click();
                                    Timestamp enterSearchTime = new Timestamp(new Date().getTime());
                                    Log.d(GlobalVariables.Tag_Name, "Clear Existing Message Successfully Time:" + enterSearchTime);
                                    value = true;
                                    Thread.sleep(5000);
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
                object.setText("Sending Telegram Text Message");
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
                UiSelector messageSent = new UiSelector().descriptionContains("Sent");
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

    public boolean longclickMessage(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().descriptionContains("Sending Telegram Text Message");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.dragTo(object,1);
                Timestamp longClickTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Message Long Click Successfully Time:" + longClickTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Long Click Message");
        }
        return  value;
    }
    public boolean displayDeleteButton(){
        boolean value = false;
        try{
            UiSelector messageSent = new UiSelector().descriptionContains("Delete");
            UiObject objectSent = device.findObject(messageSent);

            if(objectSent.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp deleteButtonDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Delete button Displayed successfully Time:" + deleteButtonDisplayedTime);
                value = true;
                if(deletePhoto)
                {
                    deleteMessage();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Delete Button");
        }
        return  value;
    }
    public boolean deleteMessage(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("Delete");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp longClickTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Button Successfully Time:" + longClickTime);
                value = true;
                if(deletePhoto)
                {
                    alertDisplayed();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Button");
        }
        return  value;
    }
    public boolean alertDisplayed(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().className("android.widget.CheckBox");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp deleteButtonDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Alert Displayed successfully Time:" + deleteButtonDisplayedTime);
                value = true;
                if(deletePhoto)
                {
                    delete();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Alert");
        }
        return  value;
    }
    public boolean delete(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("DELETE").index(1).clickable(true);
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp longClickTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Delete Button Successfully Time:" + longClickTime);
                value = true;
            }
            if(deletePhoto && !deleteLocation)
            {
                attach();
            }
            else
            {
                checkExistingMessage();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Button");
        }
        return  value;
    }
    public boolean attach(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("Attach media");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp ClickAttachTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Attach Button Successfully Time:" + ClickAttachTime);
                value = true;
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
            UiSelector messageSent = new UiSelector().textContains("Gallery");
            UiObject objectSent = device.findObject(messageSent);

            if(objectSent.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp galleryDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Gallery Displayed successfully Time:" + galleryDisplayedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Gallery");
        }
        return  value;
    }
    public boolean clickGallery(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Gallery");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp ClickGalleryTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Gallery Button Successfully Time:" + ClickGalleryTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Gallery Button");
        }
        return  value;
    }
    public boolean folderDisplayed(){
        boolean value = false;
        try{
            UiSelector messageSent = new UiSelector().textContains("Gallery");
            UiObject objectSent = device.findObject(messageSent);

            if(objectSent.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp folderDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Folder Displayed successfully Time:" + folderDisplayedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Folder");
        }
        return  value;
    }
    public boolean clickFolder(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Gallery");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp ClickFolderTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Folder Button Successfully Time:" + ClickFolderTime);
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

            UiSelector selector = new UiSelector().textContains("Image5mb");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
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

            UiSelector selector = new UiSelector().textContains("Image5mb");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp ClickFolderTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Image5mb Successfully Time:" + ClickFolderTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Folder");
        }
        return  value;
    }
    public boolean photoDisplayed(){
        boolean value = false;
        try{
            UiSelector messageSent = new UiSelector().textContains("Photo");
            UiObject objectSent = device.findObject(messageSent);

            if(objectSent.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp photoDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Photo Displayed successfully Time:" + photoDisplayedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Folder");
        }
        return  value;
    }
    public boolean clickPhoto(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Photo");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp clickPhotoTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Photo Successfully Time:" + clickPhotoTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Photo");
        }
        return  value;
    }
    public boolean sendPhotoDisplayed(){
        boolean value = false;
        try{
            UiSelector messageSent = new UiSelector().descriptionContains("Send");
            UiObject objectSent = device.findObject(messageSent);

            if(objectSent.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp sendPhotoDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Send Photo Displayed successfully Time:" + sendPhotoDisplayedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Send Photo");
        }
        return  value;
    }
    public boolean clickSend(){
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
            Log.d(GlobalVariables.Tag_Name,"Error in Click Send");
        }
        return  value;
    }
    public boolean deliveredPhotoDisplayed(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector messageSent = new UiSelector().descriptionContains("Sent");
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
    public boolean longClickPhoto(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("Photo");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.dragTo(object,1);
                Timestamp longClickPhotoTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Long Click Photo Successfully Time:" + longClickPhotoTime);
                value = true;
                deletePhoto = true;
                displayDeleteButton();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Long Click Photo");
        }
        return  value;
    }
    public boolean locationDisplayed(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Location");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp locationDisplayed = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Location Displayed successfully Time:" + locationDisplayed);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Location");
        }
        return  value;
    }
    public boolean clickLocation(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Location");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp clickLocationTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Location Successfully Time:" + clickLocationTime);
                value = true;
                deletePhoto = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Click Location");
        }
        return  value;
    }
    public boolean sendCurrentLocationDisplayed(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().textContains("Accurate");
                UiObject object = device.findObject(selector);

                Log.d(GlobalVariables.Tag_Name,"Loop");
                if(object.exists()){
                    Log.d(GlobalVariables.Tag_Name,"object");
                    Timestamp locationDisplayed = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Current Location Displayed successfully Time:" + locationDisplayed);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Current Location");
        }
        return  value;
    }
    public boolean clickCurrentLocation(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Send My Current Location");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                clickCurrentLocationTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Current Location Clicked successfully Time:" + clickCurrentLocationTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Current Location");
        }
        return  value;
    }
    public boolean deliveredLocationDisplayed(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector messageSent = new UiSelector().descriptionContains("Sent");
                UiObject objectSent = device.findObject(messageSent);

                if(objectSent.exists()){
                    deliveredLocationTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Delivered Location successfully Time:" + deliveredLocationTime);
                    value = true;
                    Thread.sleep(4000);
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Delivering Location");
        }
        return  value;
    }
    public boolean longClickLocation(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().descriptionContains("Location");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.dragTo(object,1);
                Timestamp clickCurrentLocationTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Long Clicked Location successfully Time:" + clickCurrentLocationTime);
                value = true;
                deleteLocation = true;
                displayDeleteButton();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in long Clicking Current Location");
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

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page = " + TTLH);
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

