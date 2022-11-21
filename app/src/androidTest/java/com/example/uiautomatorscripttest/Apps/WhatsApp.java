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

public class WhatsApp {

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
            runTest = clickStartChatButton();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Start Chat");
                return 1;
            }
            runTest = displayContact();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Contact element");
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
//            runTest = checkPopUp();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Check POP Up");
//                return 1;
//            }
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
            runTest = deleteForEveryone();
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
            runTest = imageDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Image");
                return 1;
            }
            runTest = clickFolder();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Folder");
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
            runTest = clickMoreOptions();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Long Click More Options");
                return 1;
            }
            runTest = moreOptionsDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display More Options");
                return 1;
            }
            runTest = clickMore();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click More");
                return 1;
            }
            runTest = clearChatDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Clear Chat");
                return 1;
            }
            runTest = clickClearChat();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Clear Chat");
                return 1;
            }
            runTest = clearDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Clear");
                return 1;
            }
            runTest = clickClear();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Clear");
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
            device.executeShellCommand(getCommand(GlobalVariables.Lazada_Package));
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
                UiSelector selector = new UiSelector().className("android.widget.RelativeLayout");
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
    public boolean clickStartChatButton() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/fab");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp clickSearchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on Start Chat Successfully Time:" + clickSearchTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on clicking Start Chat Button");
        }
        return value;
    }
    public boolean displayContact() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("New group");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp clickSearchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Displayed Contact Successfully Time:" + clickSearchTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Displaying Contact");
        }
        return value;
    }

    public boolean clickSearchButton() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/menuitem_search");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Log.d(GlobalVariables.Tag_Name,"enter click button");
                object.click();
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

            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/search_src_text");
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
                UiSelector checkMessage = new UiSelector().resourceId("com.whatsapp:id/conversation_text_row");
                UiObject message = device.findObject(checkMessage);

                UiSelector checkDeletedMessage = new UiSelector().resourceId("com.whatsapp:id/message_text");
                UiObject deletedMessage = device.findObject(checkDeletedMessage);

                if(message.exists() || deletedMessage.exists()){
                    Thread.sleep(3000);
                    UiSelector threeDot = new UiSelector().descriptionContains("More options");
                    UiObject threeDotObj = device.findObject(threeDot);
                    if (threeDotObj.waitForExists(3000))
                    {
                        threeDotObj.click();
                        Thread.sleep(3000);
                        UiSelector more = new UiSelector().textContains("More");
                        UiObject moreOjb = device.findObject(more);
                        if (moreOjb.waitForExists(3000))
                        {
                            moreOjb.click();
                            Thread.sleep(3000);
                            UiSelector clearChat = new UiSelector().textContains("Clear chat");
                            UiObject clearChatObj = device.findObject(clearChat);
                            if(clearChatObj.waitForExists(3000))
                            {
                                clearChatObj.click();
                                Thread.sleep(3000);
                                UiSelector clear = new UiSelector().resourceId("android:id/button1");
                                UiObject clearObj = device.findObject(clear);
                                if(clearObj.waitForExists(3000)){
                                    clearObj.click();
                                    Timestamp enterSearchTime = new Timestamp(new Date().getTime());
                                    Log.d(GlobalVariables.Tag_Name,"Clear Existing Message Successfully Time:" + enterSearchTime);
                                    value = true;
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
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().textContains("message");
                UiObject object = device.findObject(selector);

                UiSelector selectorClass = new UiSelector().className("android.widget.EditText");
                UiObject objectClass = device.findObject(selectorClass);

                if(object.exists() || objectClass.exists()){
                    Timestamp contactSelectedTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Contact Selected Appeared Time:" + contactSelectedTime);
                    value = true;
                    checkPopUp();
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View Contact Selected");
        }
        return  value;
    }

    public boolean checkPopUp(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                Log.d(GlobalVariables.Tag_Name, "Checking Pop Up");
                Thread.sleep(5000);
                UiSelector selectorPOP = new UiSelector().descriptionContains("Learn more");
                UiObject objectPOP = device.findObject(selectorPOP);

                UiSelector selectorOK = new UiSelector().textContains("OK").resourceId("android:id/button2");
                UiObject objectOK = device.findObject(selectorOK);
                if (objectPOP.exists()) {
                    Log.d(GlobalVariables.Tag_Name, "Entered Learn More");
                    Log.d(GlobalVariables.Tag_Name, String.valueOf(objectOK.exists()));
                    objectOK.click();
                    Log.d(GlobalVariables.Tag_Name, "Remove POP Successfully:");
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name, "No Pop Up:");
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Removing POP");
        }
        return  value;
    }

    public boolean enterMessageText(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selectorClass = new UiSelector().className("android.widget.EditText");
                UiObject objectClass = device.findObject(selectorClass);

                if(objectClass.exists())
                {
                    checkPopUp();
                    Log.d(GlobalVariables.Tag_Name,"Entered in If");
                    objectClass.click();
                    checkPopUp();
                    Thread.sleep(2000);
                    objectClass.setText("Sending Messenger Text Message");
                    Timestamp enterTextTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Enter Text Message Successfully Time:" + enterTextTime);
                    value = true;
                    break;
                }
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

            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/send");
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

                UiSelector messageDelivered = new UiSelector().descriptionContains("Delivered");
                UiObject objectDelivered = device.findObject(messageDelivered);

                Log.d(GlobalVariables.Tag_Name,"Start Here");
                if(objectSent.exists()){
                    Log.d(GlobalVariables.Tag_Name,"Entered Sent");
                    messageDeliveredTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Message Sent successfully Time:" + messageDeliveredTime);
                    value = true;
                    break;
                }else if (objectDelivered.exists())
                {
                    Log.d(GlobalVariables.Tag_Name,"Entered Delivered");
                    messageDeliveredTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Message Delivered successfully Time:" + messageDeliveredTime);
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
            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/conversation_text_row");
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
            UiSelector messageSent = new UiSelector().resourceId("com.whatsapp:id/action_mode_bar");
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

            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/menuitem_delete");
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
            UiSelector messageSent = new UiSelector().textContains("DELETE FOR EVERYONE");
            UiObject objectSent = device.findObject(messageSent);

            if(objectSent.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp deleteButtonDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Alert Displayed successfully Time:" + deleteButtonDisplayedTime);
                value = true;
                if(deletePhoto)
                {
                    deleteForEveryone();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Alert");
        }
        return  value;
    }
    public boolean deleteForEveryone(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("DELETE FOR EVERYONE");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                UiSelector selector1 = new UiSelector().textContains("OK");
                UiObject OKButton = device.findObject(selector1);
                if(OKButton.waitForExists(3000))
                {
                    OKButton.click();
                    Timestamp longClickTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Button Successfully Time:" + longClickTime);
                    value = true;
                }
                else
                {
                    Timestamp longClickTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Button Successfully Time:" + longClickTime);
                    value = true;
                }
                if(deletePhoto && !deleteLocation)
                {
                    attach();
                }
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

            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/input_attach_button");
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
    public boolean imageDisplayed(){
        boolean value = false;
        try{
            UiSelector messageSent = new UiSelector().textContains("image5mb");
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

            UiSelector selector = new UiSelector().textContains("image5mb");
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
    public boolean photoDisplayed(){
        boolean value = false;
        try{
            UiSelector messageSent = new UiSelector().descriptionContains("Photo");
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

            UiSelector selector = new UiSelector().descriptionContains("Photo");
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
                UiSelector messageSent = new UiSelector().resourceId("com.whatsapp:id/forward");
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

            UiSelector selector = new UiSelector().descriptionContains("View photo");
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
            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/send_current_location_btn");
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
                UiSelector messageSent = new UiSelector().resourceId("com.whatsapp:id/forward");
                UiObject objectSent = device.findObject(messageSent);

                if(objectSent.exists()){
                    deliveredLocationTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Delivered Location successfully Time:" + deliveredLocationTime);
                    value = true;
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
            UiSelector selector = new UiSelector().resourceId("com.whatsapp:id/message_info_holder");
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
    public boolean clickMoreOptions(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().descriptionContains("More options");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp clickMoreTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked More Options successfully Time:" + clickMoreTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking More Options");
        }
        return  value;
    }
    public boolean moreOptionsDisplayed(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("More");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp moreDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"More Options Displayed successfully Time:" + moreDisplayedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying More Options");
        }
        return  value;
    }
    public boolean clickMore(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("More");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp clickMoreTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked More successfully Time:" + clickMoreTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking More");
        }
        return  value;
    }
    public boolean clearChatDisplayed(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Clear chat");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp moreDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clear Chat Displayed successfully Time:" + moreDisplayedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Clear Chat");
        }
        return  value;
    }
    public boolean clickClearChat(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Clear chat");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp clickMoreTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Clear Chat successfully Time:" + clickMoreTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Clear Chat");
        }
        return  value;
    }
    public boolean clearDisplayed(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("CLEAR");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp moreDisplayedTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clear Displayed successfully Time:" + moreDisplayedTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Displaying Clear");
        }
        return  value;
    }
    public boolean clickClear(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("android:id/button1");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp clickMoreTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Clear successfully Time:" + clickMoreTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Clear");
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

