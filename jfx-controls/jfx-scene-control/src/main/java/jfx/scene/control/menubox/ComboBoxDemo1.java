/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.menubox;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import static jfx.core.app.ContentBox.ol;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class ComboBoxDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "MenuBox - ComboBox 自定义单元格";
    private ComboBox<String> cb;

    @Override
    public void index() {
        base();
    }
    
    public void base() {
        cb = new ComboBox<>();
        cb.setPromptText("自定义单元格");
        cb.setPrefWidth(200.0);
        cb.getItems().addAll("abc", "abc", "abc", "abc");
        getChildren().add(cb);

        cb.setConverter(getConverter());
        cb.setCellFactory(getCall());
    }

    private StringConverter<String> getConverter() {
        StringConverter<String> sc = new StringConverter<String>() {
            @Override
            public String toString(String t) {
                if (t == null) {
                    return "cellDemo的toString()返回了null";
                }
                if (!"".equals(t)) {
                    return t;
                } else {
                    return null;
                }
            }

            @Override
            public String fromString(String input) {
                if (input == null) {
                    ol("input是null");
                    return null;
                }
                if ("".equals(input)) {
                    ol("input是空");
                    return null;
                }
                return input;
            }
        };

        return sc;
    }

    private Callback<ListView<String>, ListCell<String>> getCall() {
        Callback<ListView<String>, ListCell<String>> callback;
        callback = new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                MyListCell<String> mlc = new MyListCell<>();
                return mlc;
            }
        };

        return callback;
    }
}

class MyListCell<T> extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty == false) {
            HBox hb = new HBox();
            hb.getChildren().addAll(new Button("btn1"), new Button("btn2"));

            this.setPrefWidth(200.0);
            this.setGraphic(hb);
            this.setText(item);
            this.setContentDisplay(ContentDisplay.RIGHT);
        }
    }
}
