package com.assignment.ijse.serenitymentalhealth;

import com.assignment.ijse.serenitymentalhealth.config.FactoryConfiguration;
import com.assignment.ijse.serenitymentalhealth.entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/login-signup-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/images/Leonardo_Phoenix_09_A_modern_and_elegant_logo_for_Serenity_Men_0.jpg")));
        stage.setTitle("SMHTC");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        initializeDatabase();

        launch();
    }

    public static void initializeDatabase(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }


}