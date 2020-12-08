/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.paint;

import fx.image.ImageMaker;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import jfx.core.app.ContentBox;

/**
 * 图片填充
 * @author realpai <paiap@outlook.com>
 */
public class ImagePatternApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Paint - ImagePattern";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);

        for (int i = 0; i < 5; i++) {
            Rectangle r = new Rectangle(150, 150);
            r.setFill(Paint.valueOf("#EDEDED"));
            gp.add(r, i, 0);
        }

        Image image = ImageMaker.getImageFromResource("icon/fav.jpg");
        Image image1 = ImageMaker.getImageFromResource("img/1.jpg");

        // Image适应填充形状
        Rectangle r1 = (Rectangle) gp.getChildren().get(0);
        r1.setFill(new ImagePattern(image));

        /*
         * image - 用于填充的Image对象；
         *
         * 指定应该从Image对象的哪个点开始填充到图形；
         * x - 定位矩形的x原点；
         * y - 定位矩形的y原点；
         *
         * width - 定位矩形的宽度（Image对象缩放后的宽度/图形宽度）；
         * height - 定位矩形的高度（Image对象缩放后的高度/图形高度）；
         *
         * proportional - 坐标是否与ImagePattern填充的形状成比例；
         */
        Rectangle r2 = (Rectangle) gp.getChildren().get(1);
        r2.setFill(new ImagePattern(image, 0, 0, 0.5, 0.5, true));

        Rectangle r3 = (Rectangle) gp.getChildren().get(2);
        r3.setFill(new ImagePattern(image, 0, 0, 0.2, 0.2, true));

        Rectangle r4 = (Rectangle) gp.getChildren().get(3);
        r4.setFill(new ImagePattern(image, 16, 16, 0, 0, false));

        Rectangle r5 = (Rectangle) gp.getChildren().get(4);
        r5.setFill(new ImagePattern(image, 700, 500, 0.5, 0.5, false));

        Circle circle = new Circle(100);
        circle.setFill(new ImagePattern(image));

        Polygon polygon = new Polygon(100, 0, 0, 200, 200, 200);
        polygon.setFill(new ImagePattern(image1));

        HBox hBox = new HBox(circle, polygon);
        hBox.setSpacing(10.0);
        setTopAnchor(hBox, 200.0);

        getChildren().addAll(gp, hBox);
    }
}
