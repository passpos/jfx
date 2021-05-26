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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.VBox;
import jfx.core.app.ContentBox;
import static jfx.core.app.ContentBox.getBaseScene;
import static jfx.core.app.ContentBox.ol;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class ClipboardDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - Clipboard 粘贴内容";
    private Clipboard clipboard;
    private VBox vBox;

    @Override
    public void index() {
        clipboard = Clipboard.getSystemClipboard();
        base();
    }

    /**
     * hasImage()只能判断截图到剪切板的情形。“复制图片文件”仅会被识别为文件；
     * hasFiles()判断复制到剪贴板中的是否包含文件，包括“图片文件”；
     *
     * clipboard.getImage() 从剪切板获取图片。包括截图和复制的图片文件；
     * clipboard.getFiles() 使用此种方式获取；
     */
    public void base() {
        Label l = new Label("请按下 Ctrl+V 粘贴内容");
        getChildren().add(l);

        vBox = new VBox();
        ScrollPane sp = new ScrollPane(vBox);
        getChildren().add(sp);

        sp.setFitToWidth(true);
        sp.setPrefWidth(400);
        sp.setPrefHeight(500);
        setTopAnchor(sp, 30.0);

        // 设置剪切复制的快捷键
        KeyCodeCombination kcc = new KeyCodeCombination(KeyCode.V, KeyCodeCombination.SHORTCUT_DOWN);

        getBaseScene().getAccelerators().put(kcc, new Runnable() {
            @Override
            public void run() {
                getFromClipboard(l);
            }
        });

        Button btn = new Button("清除");
        getChildren().add(btn);
        setLeftAnchor(btn, 300.0);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                vBox.getChildren().clear();
            }
        });
    }

    /**
     * 按下快捷键（Ctrl+V）后，将（从系统其他地方复制到的）内容放置到剪切板；
     *
     * @param l  Label
     * @param iv ImageView
     */
    private void getFromClipboard(Label l) {
        if (clipboard.hasString()) {
            l.setText(clipboard.getString());
            ol("getString()");
            return;
        }

        if (clipboard.hasImage()) {
            setImageView(clipboard.getImage());
            ol("by - getImage()");
            return;
        }
        if (clipboard.hasFiles()) {
            List<File> files = clipboard.getFiles();

            // 方式1
            for (File file : files) {
                Image img = null;
                try {
                    ol("by - getFiles()");
                    img = new Image(new FileInputStream(file));
                } catch (FileNotFoundException ex) {
                    ol(ex.getMessage());
                }
                if (img != null) {
                    ol("img");
                    setImageView(img);
                }
            }

            // 方式2（对图片文件不可行）
            Image img2 = clipboard.getImage();
            if (img2 != null) {
                setImageView(img2);
                ol(clipboard.getString());
                ol("img2");
            }

            // 方式3（对图片文件不可行）
            Object imgContent = clipboard.getContent(DataFormat.IMAGE);
            Image img3 = (Image) imgContent;
            if (img3 != null) {
                setImageView(img3);
                ol("img3");
            }
        }
    }

    private void setImageView(Image img) {
        ImageView ivs = new ImageView();
        ivs.setPreserveRatio(true);
        ivs.setImage(img);
        vBox.getChildren().add(ivs);
    }
}
