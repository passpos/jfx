/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.input;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;
import jfx.core.app.ContentBox;

/**
 * setOnDragDetected
 *
 * @author passpos <paiap@outlook.com>
 */
public class DragEventApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - DragEvent 拖拽事件";

    @Override
    public void index() {
        demo();
    }

    public void demo() {
        Rectangle rect1 = new Rectangle(100, 100);
        rect1.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = rect1.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString("Hello!");
                db.setContent(content);
                event.consume();
            }
        });

        Rectangle rect2 = new Rectangle(100, 100);

        rect2.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        Button button = new Button();

        rect2.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    System.out.println("Dropped: " + db.getString());
                    success = true;
                    button.setText(db.getString());
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        getChildren().addAll(button, rect1, rect2);
        setTopAnchor(rect1, 30.0);
        setTopAnchor(rect2, 30.0);
        setLeftAnchor(rect2, 150.0);
    }
}
