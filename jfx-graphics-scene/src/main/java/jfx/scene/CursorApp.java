/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import jfx.utils.app.ContentBox;

/**
 * 该类用于设置鼠标指针样式
 * @author realpai <paiap@outlook.com>
 */
public class CursorApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Scene - Cursor 鼠标光标指针";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Button btn1 = new Button("CLOSED_HAND");
        btn1.setCursor(Cursor.CLOSED_HAND);

        Button btn2 = new Button("CROSSHAIR");
        btn2.setCursor(Cursor.CROSSHAIR);

        Button btn3 = new Button("DEFAULT");
        btn3.setCursor(Cursor.DEFAULT);

        Button btn4 = new Button("DISAPPEAR");
        btn4.setCursor(Cursor.DISAPPEAR);

        Button btn5 = new Button("E_RESIZE");
        btn5.setCursor(Cursor.E_RESIZE);

        Button btn6 = new Button("HAND");
        btn6.setCursor(Cursor.HAND);

        Button btn7 = new Button("H_RESIZE");
        btn7.setCursor(Cursor.H_RESIZE);

        Button btn8 = new Button("MOVE");
        btn8.setCursor(Cursor.MOVE);

        Button btn9 = new Button("NE_RESIZE");
        btn9.setCursor(Cursor.NE_RESIZE);

        Button btn10 = new Button("NONE");
        btn10.setCursor(Cursor.NONE);

        Button btn11 = new Button("NW_RESIZE");
        btn11.setCursor(Cursor.NW_RESIZE);

        Button btn12 = new Button("N_RESIZE");
        btn12.setCursor(Cursor.N_RESIZE);

        Button btn13 = new Button("OPEN_HAND");
        btn13.setCursor(Cursor.OPEN_HAND);

        Button btn14 = new Button("SE_RESIZE");
        btn14.setCursor(Cursor.SE_RESIZE);

        Button btn15 = new Button("SW_RESIZE");
        btn15.setCursor(Cursor.SW_RESIZE);

        Button btn16 = new Button("S_RESIZE");
        btn16.setCursor(Cursor.S_RESIZE);

        Button btn17 = new Button("TEXT");
        btn17.setCursor(Cursor.TEXT);

        Button btn18 = new Button("V_RESIZE");
        btn18.setCursor(Cursor.V_RESIZE);

        Button btn19 = new Button("WAIT");
        btn19.setCursor(Cursor.WAIT);

        Button btn20 = new Button("W_RESIZE");
        btn20.setCursor(Cursor.W_RESIZE);

        TilePane tp = new TilePane();
        tp.getChildren().addAll(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20);
        tp.setPadding(new Insets(10.0));

        getChildren().addAll(tp);
    }
}
