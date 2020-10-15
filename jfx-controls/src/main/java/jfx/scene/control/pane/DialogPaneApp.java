/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.pane;

import javafx.scene.control.Button;
import jfx.utils.app.ContentBox;

/**
 * DialogPane 位于 javafx.controls 模块，而不是java.graphics模块
 *
 * @author realpai <paiap@outlook.com>
 */
public class DialogPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Layout - DialogPane";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Button btn1 = new Button("btn1");
        Button btn2 = new Button("btn2");
        Button btn3 = new Button("btn3");
        Button btn4 = new Button("btn4");

//        this.getChildren().add(gp);
    }

}
