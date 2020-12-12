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
package jfx.scene.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ClipboardApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - Clipboard";
    private Clipboard clipboard;

    @Override
    public void index() {
        clipboard = Clipboard.getSystemClipboard();
        baseDemo();
    }

    /**
     * hasImage()只能判断截图到剪切板的情形。“复制图片文件”仅会被识别为文件；
     * hasFiles()判断复制到剪贴板中的是否包含文件，包括“图片文件”；
     *
     * clipboard.getImage() 从剪切板获取图片。包括截图和复制的图片文件；
     * clipboard.getFiles() 使用此种方式获取；
     */
    public void baseDemo() {
        Label label = new Label("粘贴内容");
        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(300.0);
        setTopAnchor(iv, 50.0);
        KeyCodeCombination kcc = new KeyCodeCombination(KeyCode.V, KeyCodeCombination.SHORTCUT_DOWN);

        getBaseScene().getAccelerators().put(kcc, new Runnable() {
            @Override
            public void run() {
                putContentToClipboardDemo(label, iv);
                getContentFromClipboardDemo();
            }
        });
        this.getChildren().addAll(label, iv);
    }

    /**
     * 放置的内容会存在于系统剪切板中；
     *
     * @param label Label
     * @param iv    ImageView
     */
    public void putContentToClipboardDemo(Label label, ImageView iv) {
        if (clipboard.hasString()) {
            label.setText(clipboard.getString());
            ol("getString()");
        } else if (clipboard.hasImage()) {
            iv.setImage(clipboard.getImage());
            ol("by - getImage()");
        } else if (clipboard.hasFiles()) {
            List<File> files = clipboard.getFiles();
            Image img1 = null;
            try {
                // 方式1
                img1 = new Image(new FileInputStream(files.get(0)));
            } catch (FileNotFoundException ex) {
                ol(ex.getMessage());
            }

            // 方式2（对图片文件不可行）
            Image img2 = clipboard.getImage();
            ol(clipboard.getString());
            ol(img2.getUrl());

            // 方式3（对图片文件不可行）
            Object imgContent = clipboard.getContent(DataFormat.IMAGE);
            Image img3 = (Image) imgContent;

            iv.setImage(img2);
            ol("by - getFiles()");
        }
    }

    /**
     * 从剪切板获取内容
     */
    public void getContentFromClipboardDemo() {
        ClipboardContent cc = new ClipboardContent();
        cc.put(DataFormat.PLAIN_TEXT, "Hello World!");

        clipboard.setContent(cc);
    }
}
