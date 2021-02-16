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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jfx.core.app.ContentBox;

/**
 * final Dragboard extends Clipboard
 * B80 - B81
 *
 * 被拖拽的对象总是持有数据的节点；
 * @author realpai <paiap@outlook.com>
 */
public class DragboardApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - Dragboard";
    private TextField tf;
    private Button btn;
    private VBox vBox;

    @Override
    public void index() {
        baseDemo();
        dragAction();
    }

    public void baseDemo() {
        // 被拖对象
        btn = new Button("拉我一下 ^_^");

        // 拖拽放置处
        tf = new TextField();
        setLeftAnchor(btn, 50.0);

        vBox = new VBox(tf);
        vBox.setPrefSize(200, 100);
        vBox.setStyle("-fx-background-color: #773377; -fx-padding: 10px;");
        setLeftAnchor(vBox, 50.0);
        setTopAnchor(vBox, 50.0);

        getChildren().addAll(btn, vBox);

    }

    public void dragAction() {
        // 拖动检测
        btn.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                btn.setText("舒服 - ^w^");

                // 1. 启动拖动
                Dragboard sdb = btn.startDragAndDrop(TransferMode.COPY);

                // 1.1 设置拖拽动态效果
                Text text = new Text(btn.getText());
                WritableImage wi = new WritableImage((int) btn.getWidth(), (int) btn.getHeight());
                text.snapshot(new SnapshotParameters(), wi);
                sdb.setDragView(wi);

                // 2. 拖动Label时，取出Label中的文本数据，并放到ClipboardContent中；
                ClipboardContent cc = new ClipboardContent();
                cc.putString(btn.getText());

                // 3. 将ClipboardContent中的数据放到（拖拽）剪切板中，供其他对象获取；
                sdb.setContent(cc);
            }
        });

        // 执行剪切动作；
        btn.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                if (t.getTransferMode() == TransferMode.MOVE) {
                    btn.setText("");
                }
            }
        });

        // 拖动经过
        vBox.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                t.acceptTransferModes(TransferMode.COPY);
            }
        });

        // 拖动释放
        vBox.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                tf.setText(t.getDragboard().getString());

                /* 不执行下面的操作，拖拽完成后，被拖拽节点的数据传输模式将会为空；
                 * 该方法只能在 DRAG_DROPPED handler 中调用；
                 */
                t.setDropCompleted(true);
            }
        });

    }

}
