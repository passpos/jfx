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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jfx.core.app.ContentBox;

/**
 * Image类表示图形图像，用于从指定的URL加载图像。类似于File类； ImageView类用于显示图像；
 *
 * @author realpai <paiap@outlook.com>
 */
public class ImageApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Image - Image";
    private Image img;

    @Override
    public void index() {
        try {
            pathDemo();
            // baseDemo();
            constructorDemo1();
            // constructorDemo2();
            // constructorDemo3();
            // listenerDemo();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 1. 支持URL（通过file协议）
     * Ant项目中，可以使用“相对路径”，相对于“包”的根；
     * Maven项目中，不能单独使用相对路径，必须搭配“file:”，相对于项目的根；
     * 通过类加载器
     *
     * 2. 加载文件输入流
     *
     * @throws FileNotFoundException
     */
    public void pathDemo() throws FileNotFoundException {
        // 1. 绝对路径（文件：D:/Projects/JavaFX/JFX/jfx-graphics/jfx-scene/src/img/1.jpg）
        // URL方式
        Image i1 = new Image("file:D:/Projects/JavaFX/JFX/jfx-graphics/jfx-scene/src/img/1.jpg");
        // Ant与Maven项目
        Image i2 = new Image("file:D:/Projects/JavaFX/JFX/jfx-graphics/jfx-scene/src/img/1.jpg");

        // 2. 相对路径（文件：prj/src/img/1.jpg）
        // URL方式
        Image i3 = new Image("file:src/img/1.jpg");
        // Ant与Maven项目
        Image i4 = new Image("img/1.jpg");

        // 错误用法
        // Image i5 = new Image("file:src/img/1.jpg");
        // Image i6 = new Image("src/img/1.jpg");

        // 加载“resources”资源文件夹的文件（URL方式）
        URL url = getClass().getClassLoader().getResource("img/1.jpg");
        Image i7 = new Image(url.toExternalForm());

        // 文件（文件：prj/src/img/1.jpg）
        File f1 = new File("D:/Projects/JavaFX/JFX/jfx-graphics/jfx-scene/src/img/1.jpg");
        File f2 = new File("src/img/1.jpg");

        // 文件流
        FileInputStream fis1 = new FileInputStream(new File("D:/Projects/JavaFX/JFX/jfx-graphics/jfx-scene/src/img/1.jpg"));
        FileInputStream fis2 = new FileInputStream("src/img/1.jpg");
        FileInputStream fis3 = new FileInputStream(f1);
        FileInputStream fis4 = new FileInputStream(f2);

        Image i8 = new Image(fis1);
        Image i9 = new Image(fis2);
        Image i10 = new Image(fis3);
        Image i11 = new Image(fis4);

        ImageView iv = new ImageView(i7);
        getChildren().add(iv);
    }

    public void baseDemo() throws FileNotFoundException {
        Image i1 = new Image("file:D:/3.jpg", 600, 600, false, true);
        ol("----------------------preserveRatio 不保持等比例缩放");
        ol(i1.getRequestedWidth());
        ol(i1.getRequestedHeight());
        ol(i1.getWidth());
        ol(i1.getHeight());

        Image i2 = new Image("file:D:/3.jpg", 600, 600, true, true);
        ol("----------------------preserveRatio 保持等比例缩放");
        ol(i2.getRequestedWidth());
        ol(i2.getRequestedHeight());
        ol(i2.getWidth());
        ol(i2.getHeight());

        Image i3 = new Image("file:D:/3.jpg", 600, 600, true, true, true);
        ol("----------------------backgroundLoading true");
        ol(i3.getWidth());
        ol(i3.getHeight());
    }

    /**
     * Image​(InputStream is)；
     * Image​(InputStream is, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)；
     *
     * preserveRatio 保持等比例缩放，如果提供了尺寸限制，会根据较小的尺寸进行缩放；
     * smooth 保持图片质量；
     *
     * @throws FileNotFoundException
     */
    public void constructorDemo1() throws FileNotFoundException {
//        FileInputStream fis1 = new FileInputStream(new File("D:/1.jpg"));
//        Image i1 = new Image(fis1);
//
//        FileInputStream fis2 = new FileInputStream(new File("D:/2.jpg"));
//        // Image i2 = new Image(fis2, 600, 300, false, true);
//        Image i2 = new Image(fis2, 600, 200, true, true);
        Image i2 = new Image("file:src/3.jpg");

        ImageView iv = new ImageView(i2);
        this.getChildren().add(iv);
    }

    /**
     * Image​(String url)；
     * Image​(String url, boolean backgroundLoading)；
     * Image​(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)；
     * Image​(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading)；
     *
     * 要将文件路径用作URL参数，需要在路径前加上协议名
     *
     * @throws FileNotFoundException
     */
    public void constructorDemo2() throws FileNotFoundException {
        img = new Image("file:D:/3.jpg", true);

        ImageView iv = new ImageView(img);
        iv.setPreserveRatio(true);
        iv.setFitWidth(450);
        this.getChildren().add(iv);
    }

    /**
     * 通过类加载器加载图片到URL对象中；
     *
     * @throws FileNotFoundException
     */
    public void constructorDemo3() throws FileNotFoundException {
        URL url = getClass().getClassLoader().getResource("img/3.gif");
        img = new Image(url.toExternalForm());

        ImageView iv = new ImageView(img);
        this.getChildren().add(iv);
    }

    /**
     * 只有异步加载才会显示加载进度；
     */
    public void listenerDemo() {
        img.errorProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                ol(t1);
            }
        });
        img.exceptionProperty().addListener(new ChangeListener<Exception>() {
            @Override
            public void changed(ObservableValue<? extends Exception> ov, Exception t, Exception t1) {
                ol(t1.getMessage());
            }
        });
        img.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                ol(t1.doubleValue());
            }
        });
    }

}
