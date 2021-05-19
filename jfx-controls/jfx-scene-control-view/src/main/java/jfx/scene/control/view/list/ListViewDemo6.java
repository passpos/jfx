/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.list;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import jfx.core.app.ContentBox;
import jfx.core.common.Data;
import jfx.core.entity.FxPerson;

/**
 * B86 自定义自定义单元格样式（实现自定义的Callback对象）
 *
 * @author realpai <paiap@outlook.com>
 */
public class ListViewDemo6 extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "ListView - Demo6 自定义单元格";
    private ListView<FxPerson> lv;
    private ObservableList<FxPerson> list;
    // 用于commitEdit()的newValue；

    @Override
    public void index() {
        base();
    }

    public void base() {
        list = Data.getFxPersonList();
        lv = new ListView<>(list);
        lv.setFocusTraversable(true);
        lv.setEditable(true);

        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback;
        callback = new Callback<>() {

            @Override
            public ListCell<FxPerson> call(ListView<FxPerson> param) {
                return getCustomCell();
            }
        };
        lv.setCellFactory(callback);

        getChildren().add(lv);
    }

    /**
     * 创建自定义的ListCell实例；
     *
     * @return
     */
    private ListCell<FxPerson> getCustomCell() {
        ListCell<FxPerson> lc = new ListCell<>() {

            @Override
            protected void updateItem(FxPerson item, boolean empty) {
                super.updateItem(item, empty);
                if (empty == false && item != null) {
                    HBox hb = getCustomGraphic(this);
                    this.setGraphic(hb);
                }
            }

            @Override
            public void startEdit() {
                super.startEdit();
                HBox hb = getEditGraphic(this);
                this.setGraphic(hb);
                ol("start");
            }

            @Override
            public void commitEdit(FxPerson newValue) {
                super.commitEdit(newValue);
                ol("commit");
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                HBox hb = getCustomGraphic(this);
                this.setGraphic(hb);
            }
        };
        return lc;
    }

    private HBox getCustomGraphic(ListCell<FxPerson> cell) {
        ImageView iv = new ImageView(loadResource("icon/fav.jpg").toExternalForm());
        Button btn = new Button(cell.getItem().getName());
        Label ageLabel = new Label(cell.getItem().getAge());

        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.getChildren().addAll(iv, btn, ageLabel);

        return hb;
    }

    private HBox getEditGraphic(ListCell<FxPerson> cell) {
        ImageView iv = new ImageView(loadResource("icon/fav.jpg").toExternalForm());

        TextField nameField = new TextField(cell.getItem().getName());
        nameField.setPrefWidth(100.0);

        TextField ageField = new TextField(cell.getItem().getAge());
        ageField.setPrefWidth(50.0);

        /*
         * 所有的控件都具有 onAction 属性，该属性会阻止鼠标点击/ENTER按键事件向
         * 上级传递；
         * 其他所有属性出发的事件都不能阻止
         */
        nameField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FxPerson item = cell.getItem();
                String text1 = nameField.getText();
                String text2 = ageField.getText();
                if (text1.equals("") || text2.equals("")) {
                    cell.cancelEdit();
                } else {
                    item.setName(text1);
                    item.setAge(text2);
                    cell.commitEdit(item);
                }

            }
        });
        ageField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FxPerson item = cell.getItem();
                String text1 = nameField.getText();
                String text2 = ageField.getText();
                if (text1.equals("") || text2.equals("")) {
                    cell.cancelEdit();
                } else {
                    item.setName(text1);
                    item.setAge(text2);
                    cell.commitEdit(item);
                }
            }
        });

        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.getChildren().addAll(iv, nameField, ageField);

        return hb;
    }

}
