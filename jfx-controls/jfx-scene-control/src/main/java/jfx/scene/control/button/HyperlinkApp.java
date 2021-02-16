/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.button;

import jfx.core.app.ContentBox;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class HyperlinkApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Button - Hyperlink 超链接";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Hyperlink h1 = new Hyperlink("www.baidu.com");
        Hyperlink h2 = new Hyperlink("www.bilibili.com", new Button("网址"));

        HBox hb = new HBox();
        hb.getChildren().addAll(h1, h2);

        h2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                HostServices host = getApplication().getHostServices();
                host.showDocument(h2.getText());
            }
        });

        this.getChildren().add(hb);
    }

}
