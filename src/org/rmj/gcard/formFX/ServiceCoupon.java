/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.gcard.formFX;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.iface.GForm;
import org.rmj.gcard.formFX.dialog.ServiceCouponRun;

/**
 *
 * @author sayso
 */
public class ServiceCoupon implements GForm{
    private GRider poGRider;
    private JSONObject poJson;
    private boolean pbIsOk;
    private String psMessage = "";
    
    public void setGRider(GRider foGRider ) {
        poGRider = foGRider;
    }

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
        
        ServiceCouponRun instance = new ServiceCouponRun();
        instance.setData(poJson);
        
        try {
            CommonUtils.showModal(instance);
        } catch (Exception ex) {
            Logger.getLogger(ServiceCoupon.class.getName()).log(Level.SEVERE, null, ex);
            psMessage = ex.getMessage();
            pbIsOk = false;
            return false;
        }
        
        if (instance.isOkey()){
            poJson = instance.getData();
            psMessage = instance.getMessage();
            pbIsOk = true;
        } else{
            psMessage = instance.getMessage();
            pbIsOk = false;
        }
        
        return true;
        //validate the value of poJson here...
        //if not valid then assigned the reason to psMessage, set pbIsOkey to false, and return false
        //transfer the date from poJson to the form
        //show the form here...
        //if user cancelled the form then set the reason to psMessage, set pbIsOkey to false, and return false
        //transfer the value gathered from form to poJson and return true;
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
        
        if (!poJson.containsKey("sSourceNo")){
            psMessage = "UNSET source number.";
            return false;
        }
        
        if (!poJson.containsKey("sEngineNo")){
            psMessage = "UNSET engine number.";
            return false;
        }
        
        if (!poJson.containsKey("sFrameNox")){
            psMessage = "UNSET frame number.";
            return false;
        }
        
        if (!poJson.containsKey("sModelNme")){
            psMessage = "UNSET model name.";
            return false;
        }
        
        if (!poJson.containsKey("nYellowxx")){
            psMessage = "UNSET FSEC #1 quantity.";
            return false;
        }
        
        if (!poJson.containsKey("nWhitexxx")){
            psMessage = "UNSET FSEC #2 quantity.";
            return false;
        }
        
        if (!poJson.containsKey("nPointsxx")){
            psMessage = "UNSET G-Card points.";
            return false;
        }
        
        return true;
    }
}
