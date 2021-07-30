/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.media.path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PathDemo extends AnchorPane {

    private TilePane tp = new TilePane();

    public void index() {
        imagePath();
        try {
            filePath();
            fileInputStreamPath();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PathDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        urlPath();
        tp.setHgap(10);
        tp.setVgap(10);
        getChildren().addAll(tp);
        setCustomStyle();
    }

    /**
     * Ant与Maven项目中，“相对路径”，相对于“包”的根；
     *
     * 使用file协议，必须搭配“file:”，相对于项目的根；
     *
     */
    public void imagePath() {
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
        ImageView iv1 = new ImageView(i1);
        ImageView iv2 = new ImageView(i2);
        ImageView iv3 = new ImageView(i3);
        ImageView iv4 = new ImageView(i4);

        tp.getChildren().addAll(iv1, iv2, iv3, iv4);
    }

    /**
     * 文件路径
     * @throws FileNotFoundException
     */
    public void filePath() throws FileNotFoundException {
        // 文件（文件：prj/src/img/1.jpg）
        File f1 = new File("D:/Projects/Java/JDM/jdm-base/jdm-common/src/img/1.jpg");
        File f2 = new File("src/img/1.jpg");

        FileInputStream fis1 = new FileInputStream(f1);
        FileInputStream fis2 = new FileInputStream(f2);

        Image i1 = new Image(fis1);
        Image i2 = new Image(fis2);

        ImageView iv1 = new ImageView(i1);
        ImageView iv2 = new ImageView(i2);

        tp.getChildren().addAll(iv1, iv2);
    }

    /**
     * 文件流路径
     *
     * @throws java.io.FileNotFoundException
     */
    public void fileInputStreamPath() throws FileNotFoundException {
        FileInputStream fis1 = new FileInputStream(new File("D:/Projects/Java/JDM/jdm-base/jdm-common/src/img/1.jpg"));
        FileInputStream fis2 = new FileInputStream("src/img/1.jpg");

        Image i1 = new Image(fis1);
        Image i2 = new Image(fis2);

        ImageView iv1 = new ImageView(i1);
        ImageView iv2 = new ImageView(i2);

        tp.getChildren().addAll(iv1, iv2);
    }

    /**
     * 加载“resources”资源文件夹中的文件
     */
    public void urlPath() {
        URL url1 = getClass().getClassLoader().getResource("images/1.jpg");
        Image i1 = new Image(url1.toExternalForm());

        ImageView iv1 = new ImageView(i1);

        tp.getChildren().addAll(iv1);
    }

    private void setCustomStyle() {
        tp.getChildren().forEach(new Consumer<Node>() {
            @Override
            public void accept(Node t) {
                ImageView iv = (ImageView) t;
                iv.setFitWidth(200);
                iv.setPreserveRatio(true);
            }
        });
    }
}
