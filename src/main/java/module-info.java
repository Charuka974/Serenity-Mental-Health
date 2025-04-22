module com.assignment.ijse.serenitymentalhealth {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.base;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires java.naming;
    requires mysql.connector.j;
    requires spring.security.crypto;


    opens com.assignment.ijse.serenitymentalhealth.dto.tm to javafx.base;
    opens com.assignment.ijse.serenitymentalhealth.config to jakarta.persistence;
    opens com.assignment.ijse.serenitymentalhealth.entity to org.hibernate.orm.core;

    opens com.assignment.ijse.serenitymentalhealth to javafx.fxml;
    exports com.assignment.ijse.serenitymentalhealth;
    exports com.assignment.ijse.serenitymentalhealth.controller;

    opens com.assignment.ijse.serenitymentalhealth.controller to javafx.fxml;
}