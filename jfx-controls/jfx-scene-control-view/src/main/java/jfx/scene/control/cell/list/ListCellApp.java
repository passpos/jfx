/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.list;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.util.Callback;
import jfx.core.app.ContentBox;

/**
 * B88 鼠标悬停效果
 *
 * @author realpai <paiap@outlook.com>
 */
public class ListCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "List - ListCell";
    private ListView<String> lv;
    private ObservableList<String> oal;

    @Override
    public void index() {
        baseHover();
    }

    public void baseHover() {
        oal = FXCollections.observableArrayList();
        oal.add("data - a");
        oal.add("data - b");
        oal.add("data - c");
        oal.add("data - d");
        oal.add("data - e");

        lv = new ListView<>();
        // lv = new ListView<>(oal);
        lv.setItems(oal);
        // lv.setPlaceholder(new Label("没有数据"));
        // lv.setOrientation(Orientation.HORIZONTAL);

        // 小尺寸下，会自动出现滚动条；
        lv.setPrefWidth(300.0);
        lv.setPrefHeight(200.0);

        // 设置列表单元的尺寸
        lv.setFixedCellSize(50);

        ol(lv.getSelectionModel().getSelectedIndex());
        lv.setCellFactory(getCallback());

        getChildren().add(lv);
    }

    /**
     * setCellFactory | Callback - ListCell
     * @return
     */
    private Callback<ListView<String>, ListCell<String>> getCallback() {
        Callback<ListView<String>, ListCell<String>> callback = new Callback<ListView<String>, ListCell<String>>() {
            public int position = 0;

            @Override
            public ListCell<String> call(ListView<String> param) {
                // 创建Label
                Label l = new Label();
                l.setPrefHeight(20);
                l.setFont(new Font(16));
                l.setStyle("-fx-background-color:#33cc77");

                // 将用于显示的文本置入Label中；
                ListCell<String> lc = getListCell(l);

                /**
                 * 获取hover状态，设置hover样式
                 * position 获取 ListView 中某个“数据”在整个列表中的索引位置；
                 */
                lc.hoverProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                        if (t1 && l.getText().equals("") != true) {
                            position = param.getItems().indexOf(l.getText());
                            l.setPrefHeight(24);
                            l.setFont(new Font(18));
                        } else {
                            l.setPrefHeight(20);
                            l.setFont(new Font(16));
                        }
                    }
                });
                return lc;
            }
        };

        return callback;
    }

    private ListCell<String> getListCell(Label l) {
        // 将用于显示的文本置入Label中；
        ListCell<String> lc = new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty == false && item != null) {
                    l.setText(item);
                    setGraphic(l);
                }
            }
        };

        return lc;
    }

    public void selection() {
        lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            }
        });

    }
}
