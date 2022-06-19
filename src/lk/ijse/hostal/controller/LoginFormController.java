package lk.ijse.hostal.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginFormController {
    public TextField txtPassword;
    public ImageView imgVisibility;
    public ImageView imgVisibilityOff;
    public TextField txtUserName;
    public PasswordField pwdPassword;

    public void initialize(){
        imgVisibilityOff.setVisible(false);
        txtPassword.setVisible(false);
    }

    public void logInOnAction(ActionEvent actionEvent) {
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
}
