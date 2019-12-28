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
	requires javafx.base;
	requires java.sql.rowset;
    exports com.ynr.prison;
    
    
    opens com.ynr.prison.nitification to javafx.fxml;
    opens com.ynr.beans to org.hibernate.orm.core, javafx.base;
    opens com.ynr.util to org.hibernate.orm.core;
    opens com.ynr.prison.controller to javafx.fxml;
}