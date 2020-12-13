/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import jfx.core.app.ContentBox;

/**
 * 拖拽文件类型
 * @author passpos <paiap@outlook.com>
 */
public class DragboardDemo extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Input - DragboardDemo 拖拽文件类型";
    private HBox hBox;
    private ImageView iv;

    @Override
    public void index() {
        baseDemo();
        dragAction();
    }

    public void baseDemo() {
        Label l = new Label("拖拽文件到下面框内");
        l.setStyle("-fx-background-color:#995555");
        getChildren().add(l);

        // 从别处拖拽文件到下面节点区域上
        hBox = new HBox();
        hBox.setPrefWidth(200.0);
        hBox.setPrefHeight(250.0);
        hBox.setStyle("-fx-background-color:#556699");
        setTopAnchor(hBox, 70.0);
        getChildren().add(hBox);

        // 如果是图片文件，就显示到这里
        iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(230.0);
        hBox.getChildren().add(iv);
    }

    /**
     * 拖拽动作
     *
     * 拖拽的数据传输模式是 MOVE；
     */
    public void dragAction() {
        Paint p1 = Paint.valueOf("#773333");
        Paint p2 = Paint.valueOf("#337733");
        BorderWidths bw1 = new BorderWidths(1);
        BorderWidths bw2 = new BorderWidths(5);

        // 设置拖入时的效果
        hBox.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                BorderStroke bss = new BorderStroke(p2, null, null, bw2);
                hBox.setBorder(new Border(bss));
            }
        });

        // 设置拖过（未释放）的行为
        hBox.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                ol("t.getTransferMode() - " + t.getTransferMode());
                t.acceptTransferModes(t.getTransferMode());
            }
        });

        // 设置拖出的行为
        hBox.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                BorderStroke bss = new BorderStroke(p1, null, null, bw1);
                hBox.setBorder(new Border(bss));
            }
        });

        // 设置拖入释放后的行为
        hBox.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                if (t.getTransferMode() != TransferMode.MOVE) {
                    return;
                }

                Dragboard db = t.getDragboard();
                Image img = null;
                ol("db.hasImage()" + db.hasImage());
                ol("db.hasFiles()" + db.hasFiles());
                ol("db.hasUrl()" + db.hasUrl());

                // 是否是图片
                if (db.hasImage()) {
                    img = db.getImage();
                }

                // 是否是文件
                if (db.hasFiles() && img != null) {
                    List<File> files = db.getFiles();
                    try {
                        img = new Image(new FileInputStream(files.get(0)));
                    } catch (FileNotFoundException ex) {
                        ol(ex.getMessage());
                    }
                }

                // 是否是URL
                if (db.hasUrl()) {
                    img = new Image(db.getUrl());
                }

                // 将img设置到ImageView
                if (img != null) {
                    iv.setImage(img);
                } else {
                    ob("未能捕获图片");
                }

                t.setDropCompleted(true);
            }
        });

    }
}
