module lsalih.deezerjavafxgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.json;
    requires javafx.media;
    requires com.opencsv;

    opens lsalih.deezerjavafxgui to javafx.fxml;
    exports lsalih.deezerjavafxgui;
    exports lsalih.deezerjavafxgui.deezer;
    opens lsalih.deezerjavafxgui.deezer to javafx.fxml;
    exports lsalih.deezerjavafxgui.deezer.model;
    opens lsalih.deezerjavafxgui.deezer.model to javafx.fxml;
    exports lsalih.deezerjavafxgui.deezer.dao;
    opens lsalih.deezerjavafxgui.deezer.dao to javafx.fxml;
    exports lsalih.deezerjavafxgui.deezer.view;
    opens lsalih.deezerjavafxgui.deezer.view to javafx.fxml;

}