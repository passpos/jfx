/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import jfx.core.app.ContentBox;

/**
 * DialogPane 位于 javafx.controls 模块，而不是java.graphics模块
 *
 * @author realpai <paiap@outlook.com>
 */
public class DialogPaneApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Layout - DialogPane";
    private Dialog<Button> d;
    private DialogPane dp;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        dp = new DialogPane();

        d = new Dialog<>();
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

        // Header 优先级高于 HeaderText
        dp.setHeaderText("headerText");
        dp.setHeader(new Button("Header"));

        // Content 优先级高于 ContentText
        dp.setContentText("contentText");
        dp.setContent(new Button("Content"));

        // Button具有预定义顺序，用户不可控；
        dp.getButtonTypes().add(ButtonType.CLOSE);
        dp.getButtonTypes().add(ButtonType.CANCEL);
        dp.getButtonTypes().add(ButtonType.OK);

        dp.setPrefSize(300, 200);
    }

}
