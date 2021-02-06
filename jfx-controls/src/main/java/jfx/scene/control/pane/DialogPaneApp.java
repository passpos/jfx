/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import jfx.core.app.ContentBox;

/**
 * DialogPane 位于 javafx.controls 模块，而不是java.graphics模块
 *
 * @author realpai <paiap@outlook.com>
 */
public class DialogPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Layout - DialogPane";
    private Dialog<Button> d;
    private DialogPane dp;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        d = new Dialog<>();
        dp = new DialogPane();
        d.setDialogPane(dp);

        // 激发dialog
        Button btn = new Button("点击");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                d.show();
            }
        });
        getChildren().add(btn);

        dp.setHeaderText("headerText");
        dp.setContentText("contentText");
    }

}
