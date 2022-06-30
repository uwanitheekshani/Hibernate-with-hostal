package lk.ijse.hostal.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class DashBoardFormController {
    public Label lblDate;
    public AnchorPane mainContext;
    public Label lblTime;
    public AnchorPane dashBoardContext;

    public void initialize() throws IOException {
        mainContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/StudentForm.fxml"));
        mainContext.getChildren().add(parent);
        loadDateAndTime();

    }

    private void loadDateAndTime() {
        Calendar clndr = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat(" aa");


        Timeline clock = new Timeline(new KeyFrame(Duration.INDEFINITE.ZERO, e -> {
            LocalTime currenttime = LocalTime.now();
            LocalDate currentdate = LocalDate.now();
            lblTime.setText(currenttime.getHour() + ":" + currenttime.getMinute() + ":" + currenttime.getSecond() + "  " + format1.format(clndr.getTime()));
            lblDate.setText(currentdate.getDayOfWeek()+","+ currentdate.getMonth() +" " + currentdate.getDayOfMonth() + "," + currentdate.getYear());

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void btnManageStudentsOnAction(ActionEvent actionEvent) throws IOException {
        mainContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/StudentForm.fxml"));
        mainContext.getChildren().add(parent);
    }

    public void btnManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        mainContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/RoomsForm.fxml"));
        mainContext.getChildren().add(parent);
    }

    public void btnReserveDetailsOnAction(ActionEvent actionEvent) {
    }


    public void backOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("LoginForm");
    }

    private void setUi (String location) throws IOException {
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }

    public void btnReserveRoomsOnAction(ActionEvent actionEvent) throws IOException {
        mainContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ReserveRoomForm.fxml"));
        mainContext.getChildren().add(parent);
    }

    public void btnManageUserOnAction(ActionEvent actionEvent) throws IOException {
        mainContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/UserForm.fxml"));
        mainContext.getChildren().add(parent);
    }
}
