package org.rmj.gcard.formFX.dialog;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.rmj.gcard.formFX.style.Gjson;

/**
 * Service Coupon Controller class
 *
 * @author Michael T. Cuison
 * @since 2019.06.28
 */
public class ServiceCouponController implements Initializable, Gjson{

    @FXML
    private Button btnExit;
    @FXML
    private FontAwesomeIconView glyphExit;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnOkay;
    @FXML
    private TextField txtField01;
    @FXML
    private TextField txtField02;
    @FXML
    private TextField txtField03;
    @FXML
    private TextField txtField04;
    @FXML
    private TextField txtField05;
    @FXML
    private TextField txtField06;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtField04.focusedProperty().addListener(txtField_Focus);
        txtField05.focusedProperty().addListener(txtField_Focus);
        txtField06.focusedProperty().addListener(txtField_Focus);
        
        loadData();       
    }    

    @Override
    public void setData(JSONObject json) {
        this.json = json;
    }

    @Override
    public JSONObject getData() {
        return json;
    }
    
    @Override
    public String getMessage() {
        return sMessage;
    }

    @Override
    public boolean isOkey() {
        return bOkey;
    }
    
    private void loadData(){
        bOkey = false;        
        
        txtField01.setText(json.get("sEngineNo").toString());
        txtField02.setText(json.get("sFrameNox").toString());
        txtField03.setText(json.get("sModelNme").toString());
        txtField04.setText(json.get("nYellowxx").toString());
        txtField05.setText(json.get("nWhitexxx").toString());
        txtField06.setText(json.get("nPointsxx").toString());        
        txtField06.setDisable(!json.get("sEngineNo").toString().equals("M02910000010"));
        
        
        btnClose.setOnAction(this::cmdButton_Click);
        btnExit.setOnAction(this::cmdButton_Click);
        btnOkay.setOnAction(this::cmdButton_Click);
    }

    private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId().toLowerCase();
        switch (lsButton){
            case "btnokay":
                json.clear();
                json.put("nYellowxx", Integer.parseInt(txtField04.getText()));
                json.put("nWhitexxx", Integer.parseInt(txtField05.getText()));
                
                if (txtField06.disableProperty().getValue() == false)
                    json.put("nPointsxx", Double.valueOf(txtField06.getText()).longValue());
                
                sMessage = json.toJSONString();
                bOkey = true;
                break;
            default:
                sMessage = "Saving interupted...";
                bOkey = false;
        }
        
        unloadForm();
    }
    
    private void unloadForm(){
        Stage stage = (Stage) txtField01.getScene().getWindow();
        stage.close();
    }
    
    private JSONObject json;
    private String sMessage = "";
    private boolean bOkey = false;
    private boolean bLoaded = false;
    
    final ChangeListener<? super Boolean> txtField_Focus = (o,ov,nv)->{
        if (!bLoaded) return;

        TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        
        if (lsValue == null) return;
        
        if(!nv){ /*Lost Focus*/
            switch (lnIndex) {
                case 4: //fsec 1
                case 5: //fsec 2
                    int lnValue = 0;
                    try {
                        lnValue = Integer.parseInt(lsValue);
                    } catch (NumberFormatException e) {
                        System.out.println("String can't be converted to Integer...");
                        lnValue = 0;
                    }
                    txtField.setText(String.valueOf(lnValue));
                    break;
                case 6: //forwarded
                    long lnLValue = 0;
                    
                    try {
                        lnLValue = new Double(lsValue).longValue();
                    } catch (NumberFormatException e) {
                        System.out.println("String can't be converted to Long...");
                        lnLValue = (long) 0.0;
                    }                
                    txtField.setText(String.valueOf(lnLValue));
            }
        }
    };
    
    
}
