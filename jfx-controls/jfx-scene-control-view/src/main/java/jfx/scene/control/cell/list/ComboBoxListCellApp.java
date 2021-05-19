/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.scene.control.cell.list;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.common.Data;
import jfx.core.entity.FxPerson;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ComboBoxListCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListCell - ComboBoxListCell";
    private ListView<FxPerson> lv;
    private ObservableList<FxPerson> oal;

    @Override
    public void index() {
        base();
        comboBoxListCellDemo();
    }

    public void base() {
        lv = new ListView<>(Data.getFxPersonList());
        lv.setPrefHeight(200.0);
        getChildren().add(lv);
    }

    /**
     * setCellFactory | Callback - ComboBoxListCell
     *
     * 注意：修改是对 数据源选项 进行修改。
     * 进行第一次修改时，数据源选项 发生改变；
     * 修改其他选项，或进行第二次修改，ObservableList中的数据显示的就不再是初始化时的状态；
     *
     * 解决办法就是：
     * 选项列表 和 数据源选项列表 分离。如下面的 callback2，使用的是另外的数据；
     * 而 callback1，既在选项中加载了 ObservableList<> items，又在 callback1 中使用了items；
     */
    public void comboBoxListCellDemo() {
        lv.setEditable(true);
        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback1 = ComboBoxListCell.forListView(new StringConverter<FxPerson>() {
            @Override
            public String toString(FxPerson t) {
                return "";
            }

            @Override
            public FxPerson fromString(String string) {
                return new FxPerson();
            }
        }, oal);

        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback2 = ComboBoxListCell.forListView(new StringConverter<FxPerson>() {
            @Override
            public String toString(FxPerson t) {
                return t.getName() + " - 双击查看效果";
            }

            // 仅在有支持输入能力的控件上执行以下方法；
            @Override
            public FxPerson fromString(String string) {
                return new FxPerson();
            }
        },
                // 用于双击后的展示；
                new FxPerson("回复", "12"),
                new FxPerson("持久化", "12"));

        lv.setCellFactory(callback2);
    }
}
