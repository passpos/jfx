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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import jfx.core.app.ContentBox;

/**
 * B79
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
        Button btn = new Button("粘贴内容");

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(300.0);

        getChildren().addAll(btn, iv);
        setTopAnchor(iv, 50.0);

        // 设置剪切复制的快捷键
        KeyCodeCombination kcc = new KeyCodeCombination(KeyCode.V, KeyCodeCombination.SHORTCUT_DOWN);

        getBaseScene().getAccelerators().put(kcc, new Runnable() {
            @Override
            public void run() {
                getContentFromClipboardDemo(btn, iv);
                putContentToClipboardDemo();
            }
        });

    }

    /**
     * 按下快捷键后，将（从系统其他地方复制到的）内容放置到剪切板；
     *
     * 放置的内容会存在于系统剪切板中；
     *
     * @param btn Button
     * @param iv  ImageView
     */
    private void getContentFromClipboardDemo(Button btn, ImageView iv) {
        if (clipboard.hasString()) {
            btn.setText(clipboard.getString());
            ol("getString()");
        } else if (clipboard.hasImage()) {
            iv.setImage(clipboard.getImage());
            ol("by - getImage()");
        } else if (clipboard.hasFiles()) {
            List<File> files = clipboard.getFiles();
            // 方式1
            Image img1 = null;
            try {
                ol("by - getFiles()");
                img1 = new Image(new FileInputStream(files.get(0)));
            } catch (FileNotFoundException ex) {
                ol(ex.getMessage());
            }
            if (img1 != null) {
                iv.setImage(img1);
                ol("img1");
            }

            // 方式2（对图片文件不可行）
            Image img2 = clipboard.getImage();
            if (img2 != null) {
                iv.setImage(img2);
                ol(clipboard.getString());
                ol("img2");
            }

            // 方式3（对图片文件不可行）
            Object imgContent = clipboard.getContent(DataFormat.IMAGE);
            Image img3 = (Image) imgContent;
            if (img3 != null) {
                iv.setImage(img3);
                ol("img3");
            }
        }
    }

    /**
     * 从剪切板获取内容
     *
     * 先将数据封装到 ClipboardContent ，在将ClipboardContent放入剪切板；
     */
    private void putContentToClipboardDemo() {
        ClipboardContent cc = new ClipboardContent();
        cc.put(DataFormat.PLAIN_TEXT, "放置内容成功");

        clipboard.setContent(cc);
    }
}
