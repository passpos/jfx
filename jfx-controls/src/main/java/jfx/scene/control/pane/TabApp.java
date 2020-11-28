/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.pane;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import jfx.core.app.ContentBox;
import static jfx.core.app.ContentBox.ol;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class TabApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Pane - Tab 标签";
    public TabPane tp;

    @Override
    public void index() {
        tp = new TabPane();
        baseDemo();
        styleDemo();
        selectEventDemo();
        onClosedEventDemo();
        onCloseRequestDemo();
    }

    public void baseDemo() {
        // Tab Content
        HBox hb = new HBox();
        hb.getChildren().addAll(new Button("btn1"), new Button("btn2"));

        // Tab
        Tab t1 = new Tab();
        t1.setText("tab1 - closable");

        // 标签不可关闭，关闭按钮也会消失；
        t1.setClosable(false);

        Tab t2 = new Tab("tab2 - disable");
        t2.setContent(hb);

        // 所有标签不可关闭；
        // tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        // 禁用标签；
        t2.setDisable(true);

        tp.getTabs().add(t1);
        tp.getTabs().add(t2);
        getChildren().add(tp);

        tp.setPrefWidth(300);
        tp.setPrefHeight(150);
    }

    /**
     * 标签样式
     */
    public void styleDemo() {
        ImageView iv = new ImageView("file:D:\\Projects\\Java\\JFX\\jfx-controls\\src\\main\\resources\\img\\c1c.gif");
        iv.setFitWidth(15);
        iv.setFitHeight(15);

        Tab t3 = new Tab();
        t3.setText("tab3");
        t3.setGraphic(iv);

        tp.getTabs().add(t3);
    }

    /**
     * 选中改变事件。由于涉及到两种状态（选中/未选中），所以（切入/切离tab3）都
     * 会运行下列事件处理；
     */
    public void selectEventDemo() {
        Tab t4 = new Tab("tab4 - selectEvent");
        tp.getTabs().addAll(t4);

        t4.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                Tab tb = (Tab) t.getSource();
                ol("选中了" + tb.getText());
            }
        });
    }

    public void onClosedEventDemo() {
        Tab t5 = new Tab("tab5 - onClosed");
        tp.getTabs().addAll(t5);

        t5.setOnClosed(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                ol("啊！你真的把我关闭了！");
            }
        });
    }

    public void onCloseRequestDemo() {
        Tab t6 = new Tab("tab6 - onCloseRequest");
        tp.getTabs().addAll(t6);

        t6.setOnCloseRequest(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                ol("你竟然要关闭我？！哼！");
            }
        });
    }
}
