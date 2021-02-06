/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.scene.control.dialog;

import java.util.Optional;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.util.Callback;
import jfx.core.app.ContentBox;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class DialogDemo extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Dialog - Demo 自定义对话框";
    private Dialog<Button> d;

    @Override
    public void index() {
        baseDemo();
        eventDemo();
    }

    /**
     * Dialog不必添加到父节点，就可以展示；
     */
    public void baseDemo() {
        d = new Dialog<>();
        d.setTitle("Title - 错误！");
        d.setContentText("Content - 不要胡乱点击！");
        d.setHeaderText("Header");
        d.setGraphic(new Button("Graphic"));

        d.getDialogPane().setPrefSize(300, 300);

        // 向dialog添加按钮
        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
        d.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        d.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        // 从dialog对象中获取相应的按钮，并设置单击事件。如果dialog不具有该按钮，运行错误；
        Button b1 = (Button) d.getDialogPane().lookupButton(ButtonType.CLOSE);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol(t.toString());
            }
        });

        // 激发dialog
        Button btn = new Button("点击");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                d.show();
            }
        });

        getChildren().add(btn);
    }

    /**
     * 事件监听
     */
    public void eventDemo() {
        d.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent t) {
                ol("OnCloseRequest");
            }
        });
        d.setResultConverter(new Callback<ButtonType, Button>() {
            @Override
            public Button call(ButtonType param) {
                ol(param);
                return null;
            }
        });

        Optional<Button> saw = d.showAndWait();
        saw.ifPresent(new Consumer<Button>() {
            @Override
            public void accept(Button t) {
            }
        });
    }
}
