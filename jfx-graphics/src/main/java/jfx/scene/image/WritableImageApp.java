/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.scene.image;

import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import jfx.core.app.ContentBox;

/**
 * public class WritableImage extends Image
 *
 * 向图片中写入像素；
 * WritableImage相比Image的优点，就是它可以得到一个 PixelWriter ，从而编辑自身；
 * PixelWriter pw = wi.getPixelWriter();
 *
 * @author realpai <paiap@outlook.com>
 */
public class WritableImageApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Image - WritableImage";

    @Override
    public void index() {
        baseDemo();
        constructorDemo1();
        constructorDemo2();
        constructorDemo3();
    }

    public void baseDemo() {

    }

    /**
     * WritableImage​(int width, int height)
     *
     * WritableImage是Image类型，需要使用PixelWriter，才能向其自身写入像素；
     */
    public void constructorDemo1() {
        WritableImage wi = new WritableImage(100, 100);
        PixelWriter pw = wi.getPixelWriter();

        // 向一个空白区域写入像素；
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                pw.setColor(i, j, Color.valueOf("#FFD733"));
            }
        }

        // 在区域的对角线上划线；
        for (int i = 0; i < 100; i++) {
            pw.setColor(i, i, Color.valueOf("#111111"));
        }

        ImageView iv = new ImageView(wi);
        this.getChildren().add(iv);
    }

    /**
     * 从一个Image中读取一定区域的像素，并传递给WritableImage的构造方法；
     *
     * 写入 WritableImage​ 的像素区域的大小必须小于 Image的大小；
     * WritableImage​(PixelReader reader, int width, int height)
     */
    public void constructorDemo2() {
        URL resource = getClass().getClassLoader().getResource("img/2.jpg");
        Image img = new Image(resource.toExternalForm());

        WritableImage wi = new WritableImage(img.getPixelReader(), 100, 100);
        PixelWriter pw = wi.getPixelWriter();

        // 在区域的对角线上划线；
        for (int i = 0; i < 100; i++) {
            pw.setColor(i, i, Color.valueOf("#ffffff"));
        }

        ImageView iv = new ImageView(wi);
        this.getChildren().add(iv);
        setLeftAnchor(iv, 200.0);
    }

    /**
     * 读取指定Image的指定区域
     * WritableImage​(PixelReader reader, int x, int y, int width, int height)
     * reader - the PixelReader to construct from
     * x - 要从Reader读取的区域的左上角的X坐标；
     * y - 要从Reader读取的区域的左上角的Y坐标；
     * width - 从上面指定的点开始，要读取的宽度；
     * height - 上面指定的点开始，要读取的高度；
     */
    public void constructorDemo3() {
        Image img = new Image("file:D:/Projects/JavaFX/JFX/jfx-graphics/src/main/resources/imgs/1.jpg");

        WritableImage wi = new WritableImage(img.getPixelReader(), 100, 100, 200, 200);
        PixelWriter pw = wi.getPixelWriter();
        PixelReader pr = img.getPixelReader();

        // 读取img中的100*100，并通过pw写入wi；
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int x = pr.getArgb(i, j);
                pw.setArgb(i, j, x);
            }
        }

        ImageView iv = new ImageView(wi);
        this.getChildren().add(iv);
        setTopAnchor(iv, 200.0);
    }
}
