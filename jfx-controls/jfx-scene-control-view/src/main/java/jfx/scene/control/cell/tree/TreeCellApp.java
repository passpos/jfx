/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.tree;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import jfx.core.app.ContentBox;
import jfx.core.utils.TreeUtils;

/**
 * TreeCell - 节点类型，用于填充并传染一个树单元格视图；
 *
 * index，该属性在TreeView各项的展开状态不同时，所有项的索引不同，但总是自上而下
 * ，从0开始，null项为-1；
 *
 * updateItem(T item, boolean empty)，开发人员不应调用该方法，但它是开发人员重写
 * 以允许他们自定义单元格外观的最佳方法。
 * - item 数据，而非TreeItem
 * - empty 此单元格是否代表列表中的数据。 如果为空，则它不表示任何域数据，而是一
 * 个用于呈现“空”行的单元格。
 *
 * 在TreeView中，有时会使用“缓加载”技术，Tree中较深层次的子项。仅在需要时才会
 * 被加载并渲染。此时，以加载的项目就会应用该方法，而缓加载的项，在加载时才会应
 * 用该方法；
 *
 * TreeCell 本身虽然包含了用于展示的数据对象、所在TreeView和一个TreeItem对象，平
 * 常情况下，直接访问它们，将会是null。仅当TreeView渲染完毕后，用户直接操作
 * TreeCell，在触发的事件动作中，可以访问到它们。
 *
 * 例如：
 * 在 tv.setCellFactory(callback) 中，callback.call() 方法要求返回一个TreeCell，
 * 这个TreeCell一般由用户进行实例化，之后，通过该 TreeCell 是访问不到 TreeView、
 * TreeItem 和 数据 的，但在类似 TreeCell.setOnDragDetected()方法内，就可以了；
 *
 * 除此之外，在 TreeCell.updateItem(item, empty)中，则可以通过 item 参数，直接访
 * 问数据对象；
 *
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Tree - TreeCell";
    private TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        fillData();
        setItemValue();
    }

    public void fillData() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();
        root = tu.getTreeItem();

        getChildren().add(tv);
    }

    public void setItemValue() {
        Button b = new Button("setItemValue() - 点击");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String value = root.getValue();
                if (value.equals("China")) {
                    root.setValue("中国");
                } else {
                    root.setValue("China");
                }
                root.setGraphic(new Button(root.getValue()));
            }
        });
        getChildren().add(b);
        setLeftAnchor(b, 300.0);

    }
}
