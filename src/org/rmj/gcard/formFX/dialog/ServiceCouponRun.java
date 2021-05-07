/**
* Object for searching from tables.
* 
* @author  Michael Torres Cuison
* @version 1.0
* @since   2018-04-26 © Guanzon MIS-SEG 2018 and beyond 
*/

package org.rmj.gcard.formFX.dialog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import org.json.simple.JSONObject;

public class ServiceCouponRun extends Application {   
    private double xOffset = 0; 
    private double yOffset = 0;
    
    private static JSONObject poData;
    private static boolean bOkey;
    private static String sMessage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ServiceCoupon.fxml"));

        /*SET PARAMETERS TO CLASS*/
        ServiceCouponController instance = new ServiceCouponController();
        instance.setData(poData);
        
        fxmlLoader.setController(instance);
        Parent parent = fxmlLoader.load();
        
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image("org/rmj/gcard/formFX/style/ic_launcher1.png"));
        primaryStage.setTitle("Service Coupon");
        primaryStage.showAndWait();
        
        if (instance.isOkey()){
            sMessage = instance.getMessage();
            poData = instance.getData();
            bOkey = true;
        } else {
            sMessage = instance.getMessage();
            poData = null;
            bOkey = false;
        }
    }

    public void setData(JSONObject data){
        poData = data;
    }
    
    public JSONObject getData(){
        return poData;
    }
    
    public boolean isOkey(){
        return bOkey;
    }
    
    public String getMessage(){
        return sMessage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}