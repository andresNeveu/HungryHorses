module IAGroup {
    requires javafx.controls;
    requires javafx.fxml;

    opens hungryHorses to javafx.fxml;
    opens views to javafx.fxml;
    //opens model to javafx.fxml;
    exports views;
    exports hungryHorses;
}
