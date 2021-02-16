/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.layout.pane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import jfx.core.app.ContentBox;

/**
 * 块布局
 *
 * @author realpai <paiap@outlook.com>
 */
public class TilePaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Layout - TilePane";
    public TilePane tp1;
    public TilePane tp2;

    @Override
    public void index() {
        baseDemo();
        listener();
    }

    public void baseDemo() {
        Button btn1 = new Button("btn1");
        Button btn2 = new Button("btn2");
        Button btn3 = new Button("btn3");
        Button btn4 = new Button("btn4");
        Button btn5 = new Button("btn5");
        Button btn6 = new Button("btn6");
        btn1.setPrefWidth(200);
        btn2.setPrefWidth(200);
        btn3.setPrefWidth(200);
        btn4.setPrefWidth(200);
        btn5.setPrefWidth(200);
        btn6.setPrefWidth(200);

        tp1 = new TilePane();
        tp1.getChildren().addAll(btn1, btn2, btn3, btn4);

        // 设置布局的内边距
        tp1.setPadding(new Insets(10.0));
        // 设置子元素的水平间隔
        tp1.setHgap(10.0);
        // 设置子元素的垂直间隔
        tp1.setVgap(5.0);
        tp1.setStyle("-fx-background-color:#447755");

        tp2 = new TilePane();
        tp2.getChildren().addAll(btn5, btn6);
        tp2.setStyle("-fx-background-color:#774455");
        this.getChildren().addAll(tp1, tp2);
        setTopAnchor(tp2, 200.0);
    }

    /**
     * setPrefTileWidth()，对每个元素块的宽度进行设置
     *
     * 块本身的宽度是不变的，但在 布局内 会占用设定的宽度。
     */
    public void listener() {
        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                tp1.setPrefWidth(t1.doubleValue());
                tp2.setPrefWidth(t1.doubleValue());
                tp2.setPrefTileWidth(t1.doubleValue());
            }
        });
    }
}
