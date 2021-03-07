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
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import jfx.core.app.ContentBox;

/**
 * B88
 *
 * @author passpos <paiap@outlook.com>
 */
public class ListCellDemo1 extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "List - ListCell 拖拽排序";
    private ListView<String> lv;
    private ObservableList<String> oal;

    @Override
    public void index() {
        base();
        sortListByDragDemo();
    }

    public void base() {
        oal = FXCollections.observableArrayList();
        oal.add("data - a");
        oal.add("data - b");
        oal.add("data - c");
        oal.add("data - d");
        oal.add("data - e");

        lv = new ListView<>();
        // lv2 = new ListView<>(oal2);
        lv.setItems(oal);
        // lv.setPlaceholder(new Label("没有数据"));
        // lv.setOrientation(Orientation.HORIZONTAL);

        // 小尺寸下，会自动出现滚动条；
        lv.setPrefWidth(300.0);
        lv.setPrefHeight(200.0);

        // 设置列表单元的尺寸
        lv.setFixedCellSize(50);

        ol(lv.getSelectionModel().getSelectedIndex());

        getChildren().add(lv);
    }

    /**
     * setCellFactory | Callback - ListCell - 鼠标拖拽排序
     */
    public void sortListByDragDemo() {
        Callback<ListView<String>, ListCell<String>> callback = new Callback<ListView<String>, ListCell<String>>() {

            public int index = 0;
            public int position = 0;

            // 从鼠标悬停位置获得的内容；
            public String data1 = "";

            // 目标位置的内容；
            public String data2 = "";

            @Override
            public ListCell<String> call(ListView<String> param) {
                // 创建Label
                Label l = new Label();
                l.setPrefHeight(20);
                l.setFont(new Font(15));

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

                // 将被拖拽的内容文本放入剪切板
                lc.setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        Dragboard db = lc.startDragAndDrop(TransferMode.COPY_OR_MOVE);

                        index = lc.getIndex();
                        data1 = l.getText();
                        Text text = new Text("data");
                        WritableImage wi = new WritableImage(100, 20);
                        text.snapshot(new SnapshotParameters(), wi);
                        db.setDragView(wi);

                        ClipboardContent cc = new ClipboardContent();
                        cc.putString(data1);
                        db.setContent(cc);
                    }
                });
                lc.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent t) {
                        t.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                });
                // 拖拽进入某项时，获取该项的位置索引，同时将焦点置于此处；
                lc.setOnDragEntered(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent t) {
                        if (lc.getIndex() >= param.getItems().size()) {
                            position = param.getItems().size() - 1;
                        } else {
                            position = lc.getIndex();
                        }
                        param.getFocusModel().focus(position);
                    }
                });
                // 释放拖拽，获取被拖拽的内容和目标位置的内容；
                lc.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent t) {
                        if (position == index) {
                            return;
                        }
                        data1 = t.getDragboard().getString();
                        data2 = param.getItems().get(position);
                        param.getItems().set(index, data2);
                        param.getItems().set(position, data1);

                        param.getSelectionModel().select(position);
                    }
                });
                // 获取hover状态，设置hover样式
                lc.hoverProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                        if (t1 && l.getText().equals("") != true) {
                            position = param.getItems().indexOf(l.getText());
                            l.setPrefHeight(24);
                            l.setFont(new Font(18));
                        } else {
                            l.setPrefHeight(20);
                            l.setFont(new Font(15));
                        }
                    }
                });
                return lc;
            }
        };
        lv.setCellFactory(callback);

    }
}
