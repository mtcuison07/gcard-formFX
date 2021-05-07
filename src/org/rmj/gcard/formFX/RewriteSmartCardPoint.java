/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.gcard.formFX;

import org.json.simple.JSONObject;
import org.rmj.appdriver.iface.GForm;

/**
 *
 * @author sayso
 */
public class RewriteSmartCardPoint implements GForm{
    private JSONObject poJson;
    private boolean pbIsOk;
    private String psMessage = "";
    
    @Override
    public boolean setData(JSONObject foJson) {
        poJson = foJson;
        
        return true;
    }

    @Override
    public JSONObject getData(){
        if(pbIsOk){
            return poJson;
        }
        else{
            return null;
        }
    }

    @Override
    public boolean showGUI() {
        if (!validateJSON()) {
            pbIsOk = false;
            return false;
        }
        //validate the value of poJson here...
        //if not valid then assigned the reason to psMessage, set pbIsOkey to false, and return false
        //transfer the date from poJson to the form
        //show the form here...
        //if user cancelled the form then set the reason to psMessage, set pbIsOkey to false, and return false
        //transfer the value gathered from form to poJson and return true;
        return true;
    }

    @Override
    public boolean isOkey() {
        return pbIsOk;
    }

    @Override
    public String getMessage() {
        return psMessage;
    }
    
    private boolean validateJSON(){
        if (poJson == null){
            psMessage = "UNSET JSON value.";
            return false;
        }
        
        if (!poJson.containsKey("dTransact")){
            psMessage = "UNSET transaction date.";
            return false;
        }
        
        if (!poJson.containsKey("sClientNm")){
            psMessage = "UNSET client name.";
            return false;
        }
        
        if (!poJson.containsKey("sGCardNox")){
            psMessage = "UNSET G-Card number";
            return false;
        }
        
        if (!poJson.containsKey("nAvlPoint")){
            psMessage = "UNSET current points";
            return false;
        }
        
        return true;
    }
}
