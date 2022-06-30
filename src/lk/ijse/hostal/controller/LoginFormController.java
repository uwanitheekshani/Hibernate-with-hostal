package lk.ijse.hostal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostal.bo.BOFactory;
import lk.ijse.hostal.bo.custom.UserBO;
import lk.ijse.hostal.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtPassword;
    public ImageView imgVisibility;
    public ImageView imgVisibilityOff;
    public TextField txtUserName;
    public PasswordField pwdPassword;
    public AnchorPane logInContext;
    public CheckBox cbxPassword;
    public TextField txtUserId;


    public void logInOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
         final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


        String id=txtUserId.getText();
        String password=txtPassword.getText();

        User user=userBO.searchUser(id);
        // UserLogin password2=userBO.searchUser(password);

        if (user!=null) {
            if (txtPassword.getText().equals(user.getPassword()) || pwdPassword.getText().equals(user.getPassword())) {
                setUi("DashBoardForm");
                //new Alert(Alert.AlertType.CONFIRMATION, "").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Incorrect Password..!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Incorrect User Id..!").show();
        }
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

    public void visibilityPasswordOnMouseClicked(MouseEvent mouseEvent) {
        if (cbxPassword.isSelected()){
            txtPassword.setText(pwdPassword.getText());
            txtPassword.setVisible(true);
            pwdPassword.setVisible(false);
            return;
        }
        txtPassword.setText(pwdPassword.getText());
        pwdPassword.setVisible(true);
        txtPassword.setVisible(false);

    }
}
