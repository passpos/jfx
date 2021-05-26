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

import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import jfx.core.app.ContentBox;

/**
 * B79
 *
 * @author realpai <paiap@outlook.com>
 */
public class ClipboardApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - Clipboard 复制内容";
    private Clipboard clipboard;

    @Override
    public void index() {
        base();
        putToClipboard();
    }

    /**
     * 设置“粘贴”的快捷键
     */
    public void base() {
        KeyCodeCombination kcc = new KeyCodeCombination(KeyCode.V, KeyCodeCombination.SHORTCUT_DOWN);

        getBaseScene().getAccelerators().put(kcc, new Runnable() {
            @Override
            public void run() {
                getFromClipboard();
            }
        });

    }

    /**
     * 放置的内容会存在于系统剪切板中；
     * 先将数据封装到 ClipboardContent ，再将ClipboardContent放入剪切板；
     *
     * 一般的，内容总是从系统的其他位置（通过按下Ctrl+C后）获得，用户不必关注这
     * 一点，但当需要从当前程序中复制内容时，就需要注册对应的数据格式，并设置快
     * 捷键（Ctrl+C），并将数据封装后放入系统剪切板；
     */
    public void putToClipboard() {
        ClipboardContent cc = new ClipboardContent();
        cc.put(DataFormat.PLAIN_TEXT, "放置内容成功");

        clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(cc);
    }

    /**
     * 从剪切板获取内容
     */
    private void getFromClipboard() {
        Label l1 = new Label("请按下 Ctrl+V 粘贴内容");
        getChildren().add(l1);

        Label l2 = new Label();
        getChildren().add(l2);
        setLeftAnchor(l2, 300.0);

        if (clipboard.hasString()) {
            l2.setText(clipboard.getString());
            ol("getString()");
        }
    }
}
