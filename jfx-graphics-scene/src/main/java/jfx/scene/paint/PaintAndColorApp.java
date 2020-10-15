/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.paint;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import jfx.utils.app.ContentBox;

/**
 * public abstract class Paint extends Object
 * public final class Color extends Paint implements Interpolatable<Color>
 *
 * Paint是在渲染场景图时，用于填充形状和背景的颜色或渐变的基类。
 * @author realpai <paiap@outlook.com>
 */
public class PaintAndColorApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Paint - Paint + Color";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Rectangle r = new Rectangle(100, 100);
                r.setFill(Paint.valueOf("#EDEDED"));
                gp.add(r, j, i);
            }
        }

        ArrayList<Color> list = new ArrayList<>();
        list.add(Color.color(1.0, 0.8, 0));
        list.add(Color.color(1.0, 0.8, 0, 0.5));

        list.add(Color.valueOf("#EE6AA7"));
        list.add(Color.valueOf("#EE6AA777"));
        list.add(Color.valueOf("0xEE6AA7"));

        list.add(Color.RED);
        list.add(getColor(0.5, 0, 0.6, 0.3));

        list.add(Color.rgb(0, 0, 255));
        list.add(Color.rgb(0, 0, 255, 0.5));

        list.add(Color.hsb(150, 0.5, 0.5));
        list.add(Color.hsb(150, 0.5, 0.5, 0.5));

        list.add(Color.web("0x0055FF"));
        list.add(Color.web("0xBB55FF", 1.0));
        list.add(Color.web("0x00F"));

        list.add(Color.web("#FF0000"));
        list.add(Color.web("#00FF00", 1.0));
        list.add(Color.web("00CC0F"));
        list.add(Color.web("05DD55", 1.0));

        list.add(Color.web("rgb(0,0,255)"));
        list.add(Color.web("rgba(0,0,255,1.0)"));
        list.add(Color.web("rgb(0,0,100%)"));
        list.add(Color.web("rgba(0,0,100%,1.0)"));

        list.add(Color.web("hsl(270,100%,100%)"));
        list.add(Color.web("hsla(270,100%,100%,1.0)"));

        for (int i = 0; i < list.size(); i++) {
            Rectangle r = (Rectangle) gp.getChildren().get(i);
            r.setFill(list.get(i));
        }
        getChildren().add(gp);
    }

    /**
     * 创建具有指定的红、绿、蓝和alpha值的颜色，取值范围均为0.0-1.0。
     *
     * @param red
     * @param green
     * @param blue
     * @param opacity
     *
     * @return Color
     */
    private Color getColor(double red, double green, double blue, double opacity) {
        return new Color(red, green, blue, opacity);
    }
}
