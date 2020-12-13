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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import jfx.core.app.ContentBox;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class DragboardDemo extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - DragboardDemo";
    public DataFormat df = new DataFormat("data/person");

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox();
        hBox.setPrefWidth(200.0);
        hBox.setPrefHeight(250.0);
        hBox.setStyle("-fx-background-color:#556699");
        setTopAnchor(hBox, 70.0);

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(230.0);
        hBox.getChildren().add(iv);

        // 设置拖入时的效果
        hBox.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                hBox.setBorder(new Border(new BorderStroke(
                        Paint.valueOf("#ff0000"),
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(0),
                        new BorderWidths(3.0)
                )));
            }
        });

        // 设置拖过（未释放）的行为
        hBox.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                ol(t.getTransferMode());
                t.acceptTransferModes(t.getTransferMode());
            }
        });

        // 设置拖出的行为
        hBox.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                hBox.setBorder(Border.EMPTY);
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
                if (db.hasImage()) {
                    img = db.getImage();
                } else if (db.hasFiles()) {
                    List<File> files = db.getFiles();
                    try {
                        img = new Image(new FileInputStream(files.get(0)));
                    } catch (FileNotFoundException ex) {
                        ol(ex.getMessage());
                    }
                } else if (db.hasUrl()) {
                    img = new Image(db.getUrl());
                }
                if (img != null) {
                    iv.setImage(img);
                } else {
                    ob("未能捕获图片");
                }
            }
        });

        getChildren().add(hBox);
    }
}
