/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.input;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
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
    public static final String TITLE = "Input - ClipboardContent 剪贴板内容";
    private TextField tf;
    
    @Override
    public void index() {
        baseDemo();
    }
    
    public void baseDemo() {
        // 被拖拽的节点
        Button src = new Button("独鹤归何晚");
        setTopAnchor(src, 40.0);
        setLeftAnchor(src, 50.0);

        // 拖拽放置的位置
        VBox vb = new VBox(10);
        vb.setPrefWidth(250);
        vb.setPrefHeight(300);
        vb.setStyle("-fx-border-color:#ff0055");
        
        Button btn = new Button("拖拽到此");
        btn.setMaxWidth(vb.getPrefWidth());
        
        tf = new TextField();
        tf.setAlignment(Pos.CENTER);
        
        vb.getChildren().addAll(btn, tf);
        setTopAnchor(vb, 70.0);
        setLeftAnchor(vb, 50.0);
        getChildren().addAll(src, vb);

        // 拖拽时，将数据放入剪切板
        dragAction(src, vb);
    }
    
    public void dragAction(Button src, VBox vb) {
        src.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Dragboard sdb = src.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                
                ClipboardContent cc = new ClipboardContent();
                cc.putString(src.getText());
                
                sdb.setContent(cc);
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
                String str = t.getDragboard().getString();
                tf.setText(str);
            }
        });
    }
    
}
