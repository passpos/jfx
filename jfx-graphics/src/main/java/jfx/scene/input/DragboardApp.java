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

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;
import jfx.core.app.ContentBox;

/**
 * final Dragboard extends Clipboard
 * B80 - B81
 * @author realpai <paiap@outlook.com>
 */
public class DragboardApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - Dragboard";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Label label = new Label("Hello World!");
        TextField tf = new TextField();
        setLeftAnchor(tf, 250.0);

        // 拖动检测
        label.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                // 1. 启动拖动
                Dragboard sdb = label.startDragAndDrop(TransferMode.COPY);
                // 1.1 设置拖拽动态效果
                Text text = new Text(label.getText());
                WritableImage wi = new WritableImage((int) label.getWidth(), (int) label.getHeight());
                text.snapshot(new SnapshotParameters(), wi);
                sdb.setDragView(wi);

                // 2. 拖动Label时，取出Label中的文本数据，并放到ClipboardContent中；
                ClipboardContent cc = new ClipboardContent();
                cc.putString(label.getText());

                // 3. 将ClipboardContent中的数据放到（拖拽）剪切板中，供其他对象获取；
                sdb.setContent(cc);
            }
        });

        // 拖动经过
        tf.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                t.acceptTransferModes(TransferMode.COPY);
            }
        });

        // 拖动释放
        tf.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                tf.setText(t.getDragboard().getString());

                // 不执行下面的操作，拖拽完成后，被拖拽节点的数据传输模式将会为空；
                t.setDropCompleted(true);
            }
        });

        // 执行剪切动作；
        label.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                if (t.getTransferMode() == TransferMode.MOVE) {
                    label.setText("");
                }
            }
        });
        this.getChildren().addAll(label, tf);
    }

}
