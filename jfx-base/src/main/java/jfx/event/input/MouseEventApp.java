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
package jfx.event.input;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import jfx.core.app.ContentBox;

/**
 * Package javafx.scene.input
 *
 * public class MouseEvent extends InputEvent
 *
 * @author realpai <paiap@outlook.com>
 */
public class MouseEventApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - MouseEvent";
    public double X;
    public double Y;

    @Override
    public void index() {
        baseDemo();
        mouseDragDemo();
        mouseEventDemo();
        dragButtonDemo();
    }

    public void baseDemo() {
        Button btn = new Button("按钮");
        ol(btn);
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("鼠标箭头相对场景左上角的X = " + t.getSceneX());
                ol("鼠标箭头相对屏幕左上角的X = " + t.getScreenX());
                ol("鼠标箭头相对按钮左上角的X = " + t.getX());
                ol("事件类型 = " + t.getEventType());
                ol("事件源 = " + t.getSource());
                ol("事件目标 = " + t.getTarget());
                ol("按下的鼠标键 = " + t.getButton());
            }
        });
        btn.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("setOnMousePressed");
            }
        });
        btn.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("鼠标右键被按下 = " + t.isSecondaryButtonDown());
                if (t.getClickCount() == 2 && t.getButton() == MouseButton.MIDDLE) {
                    ol("中键双击");
                }
            }
        });
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            }
        });
        btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            }
        });
        btn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            }
        });
        btn.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            }
        });
        this.getChildren().add(btn);
    }

    public void mouseDragDemo() {
        Button btn = new Button("按钮");
        // 指针可以拖离到按钮以外的区域；
        btn.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("setOnMouseDragged");
            }
        });

        // 下面几个拖拽事件生效的前提，是在这里设置了拖拽检测，且设置了startFullDrag()；
        btn.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                btn.startFullDrag();
                ol("setOnDragDetected");
            }
        });

        // 仅当指针在按钮内部拖动时生效；
        btn.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent t) {
                ol("setOnMouseDragOver");
            }
        });
        btn.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent t) {
                ol("setOnMouseDragEntered");
            }
        });
        btn.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent t) {
                ol("setOnMouseDragExited");
            }
        });
        btn.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent t) {
                ol("setOnMouseDragReleased");
            }
        });

        setLeftAnchor(btn, 100.0);
        this.getChildren().add(btn);
    }

    public void mouseEventDemo() {
        Button btn = new Button("按钮");
        ol(btn);
        btn.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("setOnMousePressed");
                t.setDragDetect(true);
                // 鼠标指针上次点击的位置和这次点击的是否相同；
                t.isStillSincePress();
            }
        });
        btn.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("setOnDragDetected");
            }
        });
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("通过Circle的fireEvent()");
            }
        });

        Circle circle = new Circle(100, Color.CHOCOLATE);
        // 设置选择绑定；
        circle.setPickOnBounds(true);
        // 使鼠标点击透过该节点；
        circle.setMouseTransparent(false);
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("Circle setOnMouseClicked");
                // 用于触摸事件；
                t.isSynthesized();

                // 运行到这里时，执行btn上的指定类型的动作/事件（这里的t是MOUSE_CLICKED）；
                Event.fireEvent(btn, t);

                // 可以通过下面的方式获取事件；
                MouseEvent copyFor = t.copyFor(circle, circle);
            }
        });
        setTopAnchor(circle, 100.0);
        setLeftAnchor(circle, 100.0);

        setLeftAnchor(btn, 200.0);
        this.getChildren().addAll(btn, circle);
    }

    public void dragButtonDemo() {
        Button btn = new Button("drag me!");
        setTopAnchor(btn, 100.0);
        btn.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Y = t.getY();
                X = t.getX();
            }
        });
        btn.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                setTopAnchor(btn, t.getSceneY() - Y);
                setLeftAnchor(btn, t.getSceneX() - getSideBox().getWidth() - X);
            }
        });
        this.getChildren().add(btn);
    }

}
