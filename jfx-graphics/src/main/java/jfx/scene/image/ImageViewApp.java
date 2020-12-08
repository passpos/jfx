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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ImageViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Image - ImageView";
    private ImageView iv;

    @Override
    public void index() {
        baseDemo();
        // radiusDemo();
        viewPortDemo();
        dragDemo();
        snapShotDemo();
    }

    /**
     * ImageView()
     * ImageView​(String url)
     * ImageView​(Image image)
     *
     * iv得到的是被压缩到20*20的内存中的图片，不再是磁盘中的原文件；
     * 这是由Image的第四个参数“preserveRatio”决定的；
     */
    public void baseDemo() {
        String path = "file:/D:/Projects/JavaFX/JFX/jfx-graphics/src/main/resources/imgs/1.jpg";
        Image img = new Image(path, 200, 200, true, true);
        // Image img = new Image(path);
        iv = new ImageView(img);

        // 保持缩放比例。如果不设置，或为false，图片将变形；
        iv.setPreserveRatio(true);
        // 如果缩放，保持清晰度；
        iv.setSmooth(true);
        iv.setPickOnBounds(true);

        // 下面同时设置时，使用可以将图片尺寸按比例约束到更小的那个值；
        iv.setFitWidth(600);
        iv.setFitHeight(300);

        // 如果设置了保持宽高比，下面得到的某一项就不再是设置高度或计算高度，可能是0
        iv.getFitWidth();
        iv.getFitHeight();

        // 下面打印的就是实际尺寸；
        ol(iv.prefWidth(-1));
        ol(iv.prefHeight(-1));
        this.getChildren().add(iv);
    }

    public void radiusDemo() {
        Rectangle rec = new Rectangle(iv.prefWidth(-1), iv.prefHeight(-1));
        rec.setArcWidth(30);
        rec.setArcHeight(30);
        iv.setClip(rec);

        // 用于调整图片中心点的位置偏移量（当图片远大于（约束）形状时，这样的调整十分有意义）；
        iv.setX(0);
    }

    public void viewPortDemo() {
        Rectangle2D rd = new Rectangle2D(0, 0, 100, 100);
        iv.setViewport(rd);
    }

    public void dragDemo() {
        iv.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getSceneX() >= 1920 - 100 || t.getSceneY() >= 1080 - 100) {
                    return;
                }
                if (t.getSceneX() <= 0 || t.getSceneY() <= 0) {
                    return;
                }
                Rectangle2D rd = new Rectangle2D(t.getSceneX(), t.getSceneY(), 100, 100);
                iv.setViewport(rd);
            }
        });
    }

    /**
     * 截取ImageView的截图，并输出到本地文件；
     */
    public void snapShotDemo() {
        WritableImage ss = iv.snapshot(null, null);
        BufferedImage fromFXImage = SwingFXUtils.fromFXImage(ss, null);
        try {
            ImageIO.write(fromFXImage, "png", new File("D:/new.png"));
        } catch (IOException ex) {
            ol(ex.getMessage());
        }
    }
}
