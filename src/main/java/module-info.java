module org.example.wowantstobeamillionare {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.postgresql.jdbc;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires javafx.graphics;



    opens org.example.wowantstobeamillionare to javafx.fxml;
    exports org.example.wowantstobeamillionare;
    exports org.example.wowantstobeamillionare.controllers;
    opens org.example.wowantstobeamillionare.controllers to javafx.fxml;
    exports org.example.wowantstobeamillionare.controllers.questionBehaivior;
    opens org.example.wowantstobeamillionare.controllers.questionBehaivior to javafx.fxml;
    exports org.example.wowantstobeamillionare.controllers.guest;
    opens org.example.wowantstobeamillionare.controllers.guest to javafx.fxml;
    exports org.example.wowantstobeamillionare.controllers.player;
    opens org.example.wowantstobeamillionare.controllers.player to javafx.fxml;
    exports org.example.wowantstobeamillionare.controllers.sceneManager;
    opens org.example.wowantstobeamillionare.controllers.sceneManager to javafx.fxml;
}