/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.input;

import java.io.Serializable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import jfx.core.app.ContentBox;

/**
 * 剪贴板上可以放置用户自定义的数据；
 *
 * 除了数种常见格式之外，还支持用户自定义的数据（通过 DataFormat类）
 * @author passpos <paiap@outlook.com>
 */
public class ClipboardContentDemo extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - ClipboardContentDemo 自定义数据类型";
    public DataFormat df;
    private Button src;
    private VBox vb;
    private Person p;

    @Override
    public void index() {
        baseDemo();
        dragAction();
    }

    public void baseDemo() {
        // 设置数据格式
        DataFormat mt = DataFormat.lookupMimeType("data/person");
        if (mt == null) {
            df = new DataFormat("data/person");
        }
        // 数据源
        p = new Person("独鹤归何晚", "14", "file:src/main/resources/imgs/1.jpg");

        // 被拖节点
        src = new Button(p.getName());
        getChildren().add(src);
        setTopAnchor(src, 40.0);
        setLeftAnchor(src, 50.0);

        // 放置节点
        vb = new VBox(10);
        getChildren().add(vb);
        draggedTarget(vb);
    }

    public void dragAction() {
        // 拖拽时，将数据放入剪切板
        src.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Dragboard sdd = src.startDragAndDrop(TransferMode.COPY_OR_MOVE);

                ClipboardContent cc = new ClipboardContent();
                cc.put(df, p);

                sdd.setContent(cc);
            }
        });

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
                Object c = db.getContent(df);
                Person p = (Person) c;

                TextField name = (TextField) vb.getChildren().get(1);
                TextField age = (TextField) vb.getChildren().get(2);
                ImageView avater = (ImageView) vb.getChildren().get(3);

                name.setText(p.getName());
                age.setText(p.getAge());
                avater.setImage(new Image(p.getAvater()));
            }
        });

    }

    /**
     * 设置拖拽目的处的可视对象
     * @param vb
     */
    private void draggedTarget(VBox vb) {
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
        setTopAnchor(vb, 100.0);
        setLeftAnchor(vb, 50.0);
    }
}

/**
 * 必须实现序列化接口
 * @author realpai <paiap@outlook.com>
 */
class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String age;
    private String avater;

    Person(String name, String age, String avater) {
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
