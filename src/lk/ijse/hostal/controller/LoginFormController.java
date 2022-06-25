package lk.ijse.hostal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtPassword;
    public ImageView imgVisibility;
    public ImageView imgVisibilityOff;
    public TextField txtUserName;
    public PasswordField pwdPassword;
    public AnchorPane logInContext;

    public void initialize(){
        imgVisibilityOff.setVisible(false);
        txtPassword.setVisible(false);
    }

    public void logInOnAction(ActionEvent actionEvent) throws IOException {

//        attempts++;
//        if (attempts<=3) {
//            String tempCashierUserName = txtCashierName.getText();
//            String tempCashierPassword = pwdUserPassword.getText();
//            if (tempCashierUserName.equals("user") && tempCashierPassword.equals("5678")) {
////                Stage stage = (Stage) LogInContext.getScene().getWindow();
////                stage.close();
                setUi("DashBoardForm");
//            } else {
//                new Alert(Alert.AlertType.WARNING, "Try again").show();
//                txtCashierName.clear();
//                pwdUserPassword.clear();
//            }
//        }else {
//            txtCashierName.setEditable(false);
//            pwdUserPassword.setEditable(false);
//        }
    }

    public void btnVisibilityOnMouseEntered(MouseEvent mouseEvent) {
        imgVisibilityOff.setVisible(true);
        pwdPassword.setVisible(false);
        txtPassword.setVisible(true);
        txtPassword.setText(pwdPassword.getText());
    }

    public void btnVisibilityOnMouseExisted(MouseEvent mouseEvent) {
        imgVisibilityOff.setVisible(false);
        pwdPassword.setVisible(true);
        txtPassword.setVisible(false);
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) logInContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void shutDownOnMouseClicked(MouseEvent mouseEvent) {
        closeForm(logInContext);
    }

    public static void closeForm(AnchorPane anchorPane){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }
}
