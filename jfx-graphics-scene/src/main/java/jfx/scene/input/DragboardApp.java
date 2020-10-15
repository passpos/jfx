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
import java.io.Serializable;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import jfx.utils.app.ContentBox;

/**
 * public final class Dragboard extends Clipboard
 *
 * @author realpai <paiap@outlook.com>
 */
public class DragboardApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - Dragboard";
    public DataFormat pf = new DataFormat("data/person");

    @Override
    public void index() {
        baseDemo();
        dragFileDemo();
        dragCustomeDataDemo();
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

    public void dragFileDemo() {
        HBox hBox = new HBox();
        hBox.setPrefWidth(200.0);
        hBox.setPrefHeight(250.0);
        hBox.setStyle("-fx-background-color:#556699");
        setTopAnchor(hBox, 70.0);

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(230.0);
        hBox.getChildren().add(iv);

        // 设置拖入提示效果
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
        hBox.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                hBox.setBorder(Border.EMPTY);
            }
        });

        // 设置拖入释放后的行为
        hBox.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                ol(t.getTransferMode());
                t.acceptTransferModes(t.getTransferMode());
            }
        });
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
        this.getChildren().add(hBox);
    }

    public void dragCustomeDataDemo() {
        // 数据源
        Person p = new Person("独鹤归何晚", "14", "file:D:/Image Picka/c22a98014a90f603f49156232e12b31bb151edde.jpg");
        Button src = new Button(p.getName());
        setTopAnchor(src, 40.0);
        setLeftAnchor(src, 250.0);

        // 拖拽时，将数据放入剪切板
        src.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Dragboard sdd = src.startDragAndDrop(TransferMode.COPY_OR_MOVE);

                ClipboardContent cc = new ClipboardContent();
                cc.put(pf, p);

                sdd.setContent(cc);
            }
        });

        VBox vb = new VBox(10);
        dragTarget(vb);
        vb.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                t.acceptTransferModes(TransferMode.COPY_OR_MOVE);

            }
        });
        vb.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                Dragboard db = t.getDragboard();
                // db.getContent(DataFormat.lookupMimeType("data/person"));
                Object c = db.getContent(pf);
                Person p = (Person) c;

                TextField name = (TextField) vb.getChildren().get(1);
                TextField age = (TextField) vb.getChildren().get(2);
                ImageView avater = (ImageView) vb.getChildren().get(3);

                name.setText(p.getName());
                age.setText(p.getAge());
                avater.setImage(new Image(p.getAvater()));
            }
        });
        this.getChildren().addAll(src, vb);
    }

    private void dragTarget(VBox vb) {
        vb.setPrefWidth(250);
        vb.setPrefHeight(300);
        vb.setStyle("-fx-border-color:#ff0055");

        Button btn = new Button("个人详情");
        btn.setMaxWidth(vb.getPrefWidth());

        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        tf1.setAlignment(Pos.CENTER);
        tf2.setAlignment(Pos.CENTER);

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(vb.getPrefWidth());

        vb.getChildren().addAll(btn, tf1, tf2, iv);
        setTopAnchor(vb, 70.0);
        setLeftAnchor(vb, 250.0);
    }
}

/**
 * 必须实现序列化接口
 * @author realpai <paiap@outlook.com>
 */
class Person implements Serializable {

    private String name;
    private String age;
    private String avater;

    public Person(String name, String age, String avater) {
        this.name = name;
        this.age = age;
        this.avater = avater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

}
