/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.web;

import java.util.function.Consumer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class WebHistoryApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Web - WebHistory";
    private WebView wv;
    private WebEngine engine;
    private WebHistory history;
    private ObservableList<WebHistory.Entry> entries;

    @Override
    public void index() {
        baseDemo();
        actionDemo();
    }

    public void actionDemo() {
        HBox hBox = new HBox();
        hBox.setSpacing(15.0);
        Button button1 = new Button("后退");
        Button button2 = new Button("前进");
        Button button3 = new Button("信息");
        hBox.getChildren().addAll(button1, button2, button3);
        getChildren().add(hBox);

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    history.go(-1);
                } catch (Exception e) {
                }
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    history.go(1);
                } catch (Exception e) {
                }
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("当前索引 - " + history.getCurrentIndex());
                ol("最大存储数量 - " + history.getMaxSize());
                ol("当前存储数量 - " + entries.size());
                entries.forEach(new Consumer<WebHistory.Entry>() {
                    @Override
                    public void accept(WebHistory.Entry t) {
                        t.getTitle();
                    }
                });
            }
        });
    }

    public void baseDemo() {
        wv = new WebView();
        getChildren().add(wv);
        setTopAnchor(wv, 30.0);

        engine = wv.getEngine();
        engine.load("https://www.hao123.com");

        history = engine.getHistory();
        entries = history.getEntries();

        wv.prefWidthProperty().bind(this.widthProperty());
        wv.prefHeightProperty().bind(this.heightProperty().subtract(30));
    }
}
