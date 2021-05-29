/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.web.editor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.web.HTMLEditor;
import jfx.core.app.ContentBox;

/**
 * HTMLEditor 内容源码查看
 *
 * @author realpai <paiap@outlook.com>
 */
public class HTMLEditorDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Editor - HTMLEditor内容源码查看";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        // 用于tab1的编辑器
        HTMLEditor editor = new HTMLEditor();
        editor.setHtmlText("");

        // 用于tab2的文本域
        TextArea ta = new TextArea();
        ta.setWrapText(true);

        // 将编辑器中内容的源码，转换到文本与中
        Button btn = new Button("显示源码");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ta.setText(editor.getHtmlText());
            }
        });

        // 工具栏
        ToolBar tb = new ToolBar();
        tb.setOrientation(Orientation.HORIZONTAL);
        tb.prefWidthProperty().bind(this.widthProperty());
        tb.getItems().add(btn);

        // 标签布局与两个标签
        TabPane tp = new TabPane();
        tp.setSide(Side.TOP);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);

        Tab tab1 = new Tab();
        tab1.setText("HTML 编辑器");
        tab1.setContent(editor);
        tab1.setTooltip(new Tooltip("编辑器"));

        Tab tab2 = new Tab();
        tab2.setText("HTML 源码");
        tab2.setContent(ta);
        tab2.setTooltip(new Tooltip("源码"));

        tp.getTabs().addAll(tab1, tab2);

        tp.prefWidthProperty().bind(this.widthProperty());
        tp.prefHeightProperty().bind(this.heightProperty());

        getChildren().addAll(tb, tp);
        setTopAnchor(tp, 30.0);
    }

}
