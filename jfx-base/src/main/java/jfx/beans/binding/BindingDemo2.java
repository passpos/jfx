/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.beans.binding;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import static javafx.scene.layout.AnchorPane.setTopAnchor;
import jfx.core.app.ContentBox;

/**
 * B-55 同步输入框中的输入的文本。
 *
 * @author passpos <paiap@outlook.com>
 */
public class BindingDemo2 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - Demo syncInputText";
    private TextField tf1;
    private TextField tf2;

    @Override
    public void index() {
        base();
        //syncInputText();
        bindInputText();
    }

    public void base() {
        tf1 = new TextField();
        tf2 = new TextField();
        getChildren().addAll(tf1, tf2);
        setTopAnchor(tf1, 30.0);
        setTopAnchor(tf2, 60.0);
    }

    /**
     * 方法1 通过监听器实行；
     */
    public void syncInputText() {
        tf1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                tf2.setText(t1);
            }
        });
        tf2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                tf1.setText(t1);
            }
        });
    }

    /**
     * 方法2 属性绑定
     */
    public void bindInputText() {
        tf1.textProperty().bindBidirectional(tf2.textProperty());
    }
}
