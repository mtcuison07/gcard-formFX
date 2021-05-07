package org.rmj.gcard.formFX.dialog;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.gcard.formFX.style.Gjson;

/**
 * Cash Supplement Controller class
 *
 * @author Michael T. Cuison
 * @since 2019.06.15
 */
public class CashSupplementController implements Initializable, Gjson {
    @FXML
    private Button btnExit;
    @FXML
    private FontAwesomeIconView glyphExit;
    @FXML
    private Button btnClose;
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
    @FXML
    private Button btnOkay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
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
        
        txtField01.setText(json.get("dTransact").toString());
        txtField02.setText(json.get("sClientNm").toString());
        txtField03.setText(json.get("sCardNmbr").toString());
        txtField04.setText(json.get("nAvlPoint").toString());
        txtField05.setText("");
        txtField06.setText(json.get("nCashAmtx").toString());
        
        btnClose.setOnAction(this::cmdButton_Click);
        btnExit.setOnAction(this::cmdButton_Click);
        btnOkay.setOnAction(this::cmdButton_Click);
    }

    private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId().toLowerCase();
        switch (lsButton){
            case "btnokay":
                if (txtField05.getText().isEmpty() || txtField05.getText().length() > 6){
                    MsgBox.showOk("Invalid OR Number detected..."); 
                    return;
                }
                
                json.put("sORNoxxxx", txtField05.getText());
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
}
