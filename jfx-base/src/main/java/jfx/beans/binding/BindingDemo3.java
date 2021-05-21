/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.beans.binding;

import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class BindingDemo3 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - Demo Converter";
    private TextField tf1;
    private TextField tf2;

    @Override
    public void index() {
        base();
        converter();
    }

    public void base() {
        tf1 = new TextField();
        tf2 = new TextField();
        getChildren().addAll(tf1, tf2);
        setTopAnchor(tf1, 30.0);
        setTopAnchor(tf2, 60.0);
    }

    /**
     * 对输入字符串中的特定字符进行转换。
     *
     * 虽然是双向绑定，但转换仅发生在tf2中；
     */
    public void converter() {
        StringConverter<String> sc = getStringConverter();
        tf1.textProperty().bindBidirectional(tf2.textProperty(), sc);
    }

    private StringConverter<String> getStringConverter() {
        StringConverter<String> sc = new StringConverter<String>() {
            @Override
            public String toString(String t) {
                return t;
            }

            @Override
            public String fromString(String string) {
                if (string.contains("5")) {
                    String str = string.replace("5", "五");
                    return str;
                }
                return string;
            }
        };
        return sc;
    }

}
