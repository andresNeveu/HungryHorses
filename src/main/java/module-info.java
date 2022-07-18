module IAGroup {
    requires javafx.controls;
    requires javafx.fxml;

    opens views to javafx.fxml;
    opens smartRobot to javafx.fxml;
    opens model to javafx.fxml;
    exports views;
    exports smartRobot;
    exports model;
}
