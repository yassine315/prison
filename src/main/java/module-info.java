module com.ynr.prison {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.persistence;
	requires mysql.connector.java;
	requires java.sql;
	requires java.logging;
	requires java.naming;
	requires org.hibernate.orm.core;
    exports com.ynr.prison;
    
    
    opens com.ynr.beans to org.hibernate.orm.core, javafx.base;
    opens com.ynr.prison.controller to javafx.fxml;
}