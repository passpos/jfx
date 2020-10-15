/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.layout.pane;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import jfx.utils.app.ContentBox;

/**
 * 网格布局
 * @author realpai <paiap@outlook.com>
 */
public class GridPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Layout - GridPane";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Button btn1 = new Button("btn1");
        Button btn2 = new Button("btn2");
        Button btn3 = new Button("btn3");
        Button btn4 = new Button("btn4");

        GridPane gp = new GridPane();
        gp.add(btn1, 0, 0); // 参数1是Node实例；
        gp.add(btn2, 1, 0); // 参数2是列号，整数类型，自0开始；
        gp.add(btn3, 0, 1); // 参数3是行号，整数类型，自0开始；
        gp.add(btn4, 1, 1);
        gp.setHgap(5);                          // 子件的水平外边距；
        gp.setVgap(5);                          // 子件的垂直外边距；

        this.getChildren().add(gp);
    }

}
