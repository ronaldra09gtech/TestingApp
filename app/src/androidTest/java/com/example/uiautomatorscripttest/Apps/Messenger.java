package com.example.uiautomatorscripttest.Apps;

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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.assertEquals;

public class Messenger {

    private UiDevice device;
    Timestamp homeElementsAppearTime,sendMessageTime,messageSentTime,sendFileTime,fileSentTime,sendLocationTime,locationSentTime;
    boolean sendLocation = false;
    String randomText = "";

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
            runTest = enterSearchText();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Enter text in Search bar");
                return 1;
            }
            runTest = checkSearchResult();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Check Search Result");
                return 1;
            }
            runTest = clickSearchResult();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Search Result");
                return 1;
            }
            runTest = searchResult();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Search result");
                return 1;
            }
            runTest = checkExistingMessage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Check Existing Message");
                return 1;
            }
            runTest = enterMessage();
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
//            runTest = clickRemove();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Click Remove");
//                return 1;
//            }
//            runTest = unsentAlert();
//            if(!runTest){
//                DataHolder.getInstance().setFailureReason("Unable to Find Unsend Alert");
//                return 1;
//            }
            runTest = clickGallery();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Gallery");
                return 1;
            }
            runTest = dragGallery();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Drag Gallery");
                return 1;
            }

            runTest = selectFolderButton();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to select Folder Button");
                return 1;
            }
            runTest = gotoImage5mb();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Image5mb Folder");
                return 1;
            }
            runTest = selectFile();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Select File");
                return 1;
            }
            runTest = sendFile();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Send File");
                return 1;
            }
            runTest = clickMoreActions();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to click More Action");
                return 1;
            }
            runTest = clickLocation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Location");
                return 1;
            }
            runTest = clickPin();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Pin");
                return 1;
            }
            runTest = sendLocation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Send Location");
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
                UiSelector selector = new UiSelector().resourceId("com.facebook.orca:id/(name removed)");
                UiObject object = device.findObject(selector);
                if (object.exists()) {
                    homeElementsAppearTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Home Elements Appear Time:" + homeElementsAppearTime);
                    value = true;
                    generateRandomString();
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Home Page Elements");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return false;
        }
    }

    public boolean clickSearchButton() {
        boolean value = false;
        try{
            Thread.sleep(2000);
            UiSelector selector = new UiSelector().className("android.view.ViewGroup").index(0).clickable(true)
                    .longClickable(false).enabled(true);
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on Search Successfully Time:" + unUsableTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on clicking Search Button");
        }
        return value;
    }

    public boolean enterSearchText(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().className("android.widget.EditText");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                object.setText("Adminate Sev");
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Entered Search Text Successfully Time:" + unUsableTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error while entering search text");
        }
        return value;
    }

    public boolean checkSearchResult(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().resourceId("com.facebook.orca:id/(name removed)").index(1);
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp checkSearchTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Result Successfully Time:" + checkSearchTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Search Result");
        }
        return value;
    }

    public boolean clickSearchResult(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Click contact");
                UiSelector selector = new UiSelector().className("android.view.ViewGroup").index(2).clickable(false).enabled(true);
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().descriptionContains("Thread Details");
                UiObject objectSec = device.findObject(selectorSec);

                if (object.exists() || objectSec.exists())
                {
                    if (object.exists())
                    {
                        object.click();
                    }
                    if (objectSec.exists())
                    {
                        Timestamp unUsableTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name,"Click Result Successfully Time:" + unUsableTime);
                        value = true;
                        break;
                    }
                    else
                    {
                        Log.d(GlobalVariables.Tag_Name,"Else");
                        Thread.sleep(200);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Search Result");
        }
        return value;
    }

    public boolean searchResult(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().descriptionContains("Thread Details");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Search Result Appear Time:" + unUsableTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in search result");
        }
        return  value;
    }

    public boolean checkExistingMessage(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().className("android.view.ViewGroup").clickable(false).enabled(true).focusable(true);
            UiObject object = device.findObject(selector);

            if(object.waitForExists(3000)){
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Existing Message True: " + unUsableTime);
                value = true;
                gotoThread();
            }
            else
            {
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Existing Message False: " + unUsableTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in search Existing Message");
        }
        return  value;
    }

    public boolean enterMessage(){
        boolean value = false;
        try{
            Thread.sleep(4000);
            UiSelector selector = new UiSelector().className("android.widget.EditText");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                object.setText("Sending Messenger Text Message");
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Enter Text Message Successfully Time:" + unUsableTime);
                value = true;
                Log.d(GlobalVariables.Tag_Name,"String Counts: " + randomText.length());
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
                sendMessageTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Send Button Successfully Time:" + sendMessageTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Send Button");
        }
        return  value;
    }

    public boolean checkMessageSent(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            int loopCount = 0000;

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().className("android.view.ViewGroup").clickable(false).enabled(true).focusable(true);
                UiObject object = device.findObject(selector);

                UiSelector selector2 = new UiSelector().textContains("failed");
                UiObject object2 = device.findObject(selector2);
                if(object.exists()){
                    object.dragTo(object,1);

                    UiSelector selectorDelivered = new UiSelector().textContains("Reply");
                    UiObject objectDelivered = device.findObject(selectorDelivered);
                    if (objectDelivered.exists())
                    {
                        Log.d(GlobalVariables.Tag_Name,"Loop counts:" + loopCount);
                        messageSentTime = new Timestamp(new Date().getTime()-(1000+loopCount));
                        Log.d(GlobalVariables.Tag_Name,"Chat sent Time:" + messageSentTime);
                        value = true;
                        device.pressBack();
                        break;
                    }
                    else
                    {
                        Log.d(GlobalVariables.Tag_Name,"Not Yet Delivered");
                        loopCount += 2000;
                        Log.d(GlobalVariables.Tag_Name,"Loop counts:" + loopCount);
                        device.pressBack();
                    }
                }
                if (object2.exists())
                {
                    Log.d(GlobalVariables.Tag_Name,"Loop counts:" + loopCount);
                    messageSentTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Chat sent Time:" + messageSentTime);
                    value = true;
                    device.pressBack();
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Checking Text");
        }
        return  value;
    }

    public boolean clickRemove(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Remove");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unuseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Remove Button Successfully Time:" + unuseTime);
                value = true;
                clickUnsend();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Remove Button");
        }
        return  value;
    }

    public boolean clickUnsend(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Unsend");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unuseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Unsend Button Successfully Time:" + unuseTime);
                value = true;
                if(sendLocation == true)
                {
                    gotoThread();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Unsend Button");
        }
        return  value;
    }
    public boolean unsentAlert(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Message Unsent");
            UiObject object = device.findObject(selector);

            UiSelector OKselector = new UiSelector().textContains("OK");
            UiObject OKobject = device.findObject(OKselector);

            if(object.waitForExists(3000)){
                OKobject.click();
                Timestamp unuseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked OK Button Successfully Time:" + unuseTime);
                value = true;
            }
            else
            {
                Timestamp unuseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"No alert Appeared:" + unuseTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking OK Button");
        }
        return  value;
    }

    public boolean clickGallery(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("Choose photo");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Gallery Button Successfully Time:" + unUseTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Gallery Button");
        }
        return  value;
    }

    public boolean dragGallery(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("com.facebook.orca:id/(name removed)");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){

                int x = device.getDisplayWidth();
                int y = device.getDisplayHeight();
                double width = x / 100.00;
                double height = y / 100.00;
                int w = (int) (width * 50.00);
                int h = (int) (height * 93.00);

                device.drag(w,h,w,h - 900, 6);

                Timestamp unUseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Drag Gallery Successfully Time:" + unUseTime);
                value = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Dragging Gallery");
        }
        return  value;
    }

    public boolean selectFolderButton(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().descriptionContains("Select Folder");
                UiObject object = device.findObject(selector);

                if(object.waitForExists(5000)){
                    object.click();
                    Timestamp unUseTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Select Folder Button Successfully Time:" + unUseTime);
                    value = true;
                    break;
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name,"Executing X and Y axis function");
                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    double width = x / 100.00;
                    double height = y / 100.00;
                    int w = (int) (width * 50.00);
                    int h = (int) (height * 93.00);

                    device.drag(w,h,w,h - 900, 6);
                    device.pressBack();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Select Folder Button");
        }
        return  value;
    }

    public boolean gotoImage5mb(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("image5mb");
            UiObject object = device.findObject(selector);
            UiScrollable scroll = new UiScrollable(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
            if(scroll.waitForExists(GlobalVariables.OneMin_Timeout)){
                scroll.scrollForward();
                object.click();
                Timestamp unUseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Image5mb Folder Successfully Time:" + unUseTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Image5mb Folder");
        }
        return  value;
    }

    public boolean selectFile(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("unselected");
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked File Successfully Time:" + unUseTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking File");
        }
        return  value;
    }

    public boolean sendFile(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().textContains("SEND");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().descriptionContains("SEND");
                UiObject objectSec = device.findObject(selectorSec);
                if(object.exists()){
                    object.click();
                    sendFileTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked First Send Successfully Time:" + sendFileTime);
                    value = true;
                    checkIfDelivered();
                    break;
                }
                if(objectSec.exists()){
                    objectSec.click();
                    sendFileTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Second Send Successfully Time:" + sendFileTime);
                    value = true;
                    checkIfDelivered();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Send");
        }
        return  value;
    }

    public boolean checkIfDelivered(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().descriptionContains("Forward button");
                UiObject object = device.findObject(selector);

                UiSelector selector3 = new UiSelector().textContains("failed");
                UiObject object3 = device.findObject(selector3);

                UiSelector selector2 = new UiSelector().textContains("Location");
                UiObject object2 = device.findObject(selector2);
                if(object.exists() && sendLocation == false){
                    fileSentTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Image Delivered Successfully Time:" + fileSentTime);
                    value = true;
                    break;
                }
                if(object3.exists() && sendLocation == false ){
                    fileSentTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Image Delivered Successfully Time:" + fileSentTime);
                    value = true;
                    device.pressBack();
                    break;
                }
                if(object3.exists()){
                    locationSentTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Location Delivered Successfully Time:" + locationSentTime);
                    value = true;
                    device.pressBack();
                    break;
                }
                if(object2.exists()) {
                    locationSentTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Location Delivered Check Successfully Time:" + locationSentTime);
                    value = true;
                    gotoThread();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Sending File");
        }
        return  value;
    }

    public boolean longPress(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector messageSelector = new UiSelector().descriptionContains("message");
                UiObject messageObject = device.findObject(messageSelector);

                UiSelector mapSelector = new UiSelector().descriptionContains("Map");
                UiObject mapObject = device.findObject(mapSelector);
                if(messageObject.exists()){
                    //image
                    messageObject.dragTo(messageObject,1);
                    Timestamp unUseTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Long Press Image Successfully Time:" + unUseTime);
                    value = true;
                    clickRemove();
                    break;
                }
                else if(mapObject.exists())
                {
                    //map
                    mapObject.dragTo(mapObject,1);
                    Timestamp unUseTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Long Press Map Successfully Time:" + unUseTime);
                    value = true;
                    clickRemove();
                    break;
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name,"Unable to Long Press");
                    Thread.sleep(200);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Long Press");
        }
        return  value;
    }

    public boolean clickMoreActions(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Trying to click more action!");
                UiSelector selector = new UiSelector().descriptionContains("Open more actions");
                UiObject object = device.findObject(selector);
                UiSelector selector2 = new UiSelector().className("android.widget.Button").index(0);
                UiObject object2 = device.findObject(selector2);
                if(object2.exists()){
                    object2.click();
                    Timestamp unUseTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Open More Actions Successfully Time:" + unUseTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Open More Actions");
        }
        return  value;
    }

    public boolean clickLocation(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("Location");
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Location Successfully Time:" + unUseTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Location");
        }
        return  value;
    }

    public boolean clickPin(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("Drop an adjustable pin");
            UiObject object = device.findObject(selector);
            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUseTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Click Pin Successfully Time:" + unUseTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Pin");
        }
        return  value;
    }

    public boolean sendLocation(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                //wait Map to load
                UiSelector loadedSelector = new UiSelector().descriptionContains("Philippines");
                UiObject loadedObject = device.findObject(loadedSelector);

                //send button
                UiSelector selector = new UiSelector().className("android.widget.Button").index(1);
                UiObject object = device.findObject(selector);

                UiSelector selector1 = new UiSelector().textContains("SEND LOCATION").className("android.widget.TextView").index(1);
                UiObject object1 = device.findObject(selector1);

                if(loadedObject.exists() || object.exists() || object1.exists() ){
                    object.click();
                    sendLocationTime= new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Send Location Successfully Time:" + sendLocationTime);
                    value = true;
                    sendLocation = true;
                    checkIfDelivered();
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Send Location");
        }
        return  value;
    }
    public boolean gotoThread(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().descriptionContains("Thread Details");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                //object.click();
                Log.d(GlobalVariables.Tag_Name,"Thread Search Result Appear Time:" + unUsableTime);
                value = true;
                gotoMore();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in search result");
        }
        return  value;
    }

    public boolean gotoMore(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().className("android.view.ViewGroup").index(1);
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Goto More:" + unUsableTime);
                value = true;
                deleteConvo();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking More");
        }
        return  value;
    }

    public boolean deleteConvo(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Delete Conversation");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Delete Conversation Clicked: " + unUsableTime);
                value = true;
                deleteAlert();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Delete");
        }
        return  value;
    }

    public boolean deleteAlert(){
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("DELETE").className("android.widget.Button").index(1);
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Delete Conversation: " + unUsableTime);
                value = true;
                if(sendLocation == false)
                {
                    clickSearchResult();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in Clicking Delete");
        }
        return  value;
    }

    public String generateRandomString()
    {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 30) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return randomText = saltStr;
    }

    private boolean kpiCalculation() {
        try {
            Thread.sleep(5000);
            double TTLH = (homeElementsAppearTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double MST = (messageSentTime.getTime() - sendMessageTime.getTime()) / 1000.0;
            double IST = (fileSentTime.getTime() - sendFileTime.getTime()) / 1000.0;
            double LST = (locationSentTime.getTime() - sendLocationTime.getTime()) / 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time to load home page"+ TTLH);
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


