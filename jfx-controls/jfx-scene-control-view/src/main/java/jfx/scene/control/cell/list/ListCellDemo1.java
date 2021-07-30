/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.list;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import jfx.core.common.Data;

/**
 * B-88 拖拽排序
 *
 * @author passpos <paiap@outlook.com>
 */
public class ListCellDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListCell - 拖拽排序";
    private ListView<String> lv;
    private ObservableList<String> oal;

    @Override
    public void index() {
        base();
        setCell();
    }

    public void base() {
        oal = Data.getStringList();

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
     * setCellFactory | Callback - 鼠标拖拽排序
     *
     * 这里的拖拽时直接交换数据与位置，实际操作的项总是两个。
     * 这种方式虽然简单，但实际体验并不完美，因为当数据量比较大，只需要对个别项
     * 调整位置（如：只要求将最后一项提到靠前的某个位置）时，就需要进行大量的操
     * 作，才能实现此目的；
     *
     * 并不检测被拖项是放置在“放置处”的上面还是下面；
     */
    public void setCell() {
        Callback<ListView<String>, ListCell<String>> callback = new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(ListView<String> param) {
                // 创建Label
                Label l = new Label();
                l.setPrefHeight(20);
                l.setFont(new Font(15));

                // 将用于显示的文本置入Label中；
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false && item != null) {
                            l.setText(item);
                            setGraphic(l);
                        }
                    }
                };
                setDrag(param, cell, l);
                return cell;
            }
        };
        lv.setCellFactory(callback);
    }

    // 被拖项索引
    private int index = 0;

    // 放置（最后悬浮）处位置；
    private int position = 0;

    // 剪贴板中被拖项的数据；
    private String data1 = "";

    // 目标位置的内容；
    private String data2 = "";

    private void setDrag(ListView<String> param, ListCell<String> cell, Label l) {
        cell.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Dragboard db = cell.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                // 被拖拽项在ListView中的索引；
                index = cell.getIndex();
                // 被拖拽项的数据；
                data1 = l.getText();

                // 将被拖拽的内容文本放入剪切板
                Text text = new Text("data");
                WritableImage wi = new WritableImage(100, 20);
                text.snapshot(new SnapshotParameters(), wi);
                db.setDragView(wi);

                ClipboardContent cc = new ClipboardContent();
                cc.putString(data1);
                db.setContent(cc);
            }
        });
        cell.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t
            ) {
                t.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
        });

        // 拖拽进入某项（悬浮在其上）时，获取该项的位置索引，同时将焦点置于此处；
        cell.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t
            ) {
                // 如果悬浮处超出ListView范围，则取最后一项的索引；
                if (cell.getIndex() >= param.getItems().size()) {
                    position = param.getItems().size() - 1;
                } else {
                    position = cell.getIndex();
                }
                param.getFocusModel().focus(position);
            }
        });

        // 释放拖拽，获取被拖拽的内容和目标位置的内容；
        cell.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t
            ) {
                // 若放置在了原处，则不进行操作；
                if (position == index) {
                    return;
                }

                data1 = t.getDragboard().getString();
                data2 = param.getItems().get(position);

                // 这里没有操作ListCell，只操作了其中的数据；
                param.getItems().set(index, data2);
                param.getItems().set(position, data1);

                param.getSelectionModel().select(position);
            }
        });

        // 获取hover状态，设置hover样式
        cell.hoverProperty().addListener(new ChangeListener<Boolean>() {
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
    }
}
