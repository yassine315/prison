module com.ynr.prison {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
    exports com.ynr.prison;
    
    opens com.ynr.prison.controller to javafx.fxml;
}