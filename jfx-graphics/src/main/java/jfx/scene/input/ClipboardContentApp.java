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
import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import static javafx.scene.layout.AnchorPane.setTopAnchor;
import javafx.scene.layout.VBox;
import jfx.core.app.ContentBox;

/**
 * ClipboardContent extends HashMap
 * 用于放置剪贴板数据；
 *
 * 除了数种常见格式之外，还支持用户自定义的数据（通过 DataFormat类）
 * @author passpos <paiap@outlook.com>
 */
public class ClipboardContentApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - ClipboardContent";
    public DataFormat pf = new DataFormat("data/person");

    @Override
    public void index() {
        dragCustomeDataDemo();
    }

    public void dragCustomeDataDemo() {
        // 数据源
        Person p = new Person("独鹤归何晚", "14", "file:src/main/resources/imgs/demo.jpg");
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

    /**
     * 社会资被拖拽的可视对象
     * @param vb
     */
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
