/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.list;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import static javafx.scene.layout.AnchorPane.setTopAnchor;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.common.Converter;
import jfx.core.common.Data;
import utils.entity.demo.sample.Person;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class ListViewDemo3 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListView - Demo3 更新基本类型属性";
    private ListView<Person> lv;
    private ObservableList<Person> list;

    @Override
    public void index() {
        base();
        modifyDataDemo();
    }

    public void base() {
        list = Data.getPersonList();
        lv = new ListView<>(list);

        lv.setEditable(true);
        StringConverter<Person> pc = Converter.getPersonConverter();
        lv.setCellFactory(TextFieldListCell.forListView(pc));
        getChildren().add(lv);
    }

    public void modifyDataDemo() {
        Button b1 = new Button("点击修改");
        getChildren().add(b1);
        setTopAnchor(b1, 300.0);
        setLeftAnchor(b1, 300.0);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                list.get(1).setName("西风又起");
                list.get(1).setAge(30);
            }
        });

        Button b2 = new Button("点击更新");
        getChildren().add(b2);
        setTopAnchor(b2, 300.0);
        setLeftAnchor(b2, 350.0);
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                // 执行这个方法后，仅更新了属性的操作，才会更新到界面；
                lv.refresh();
            }
        });
    }

}
