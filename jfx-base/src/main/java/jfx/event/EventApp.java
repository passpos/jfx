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
package jfx.event;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class EventApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Event - Event";

    @Override
    public void index() {
        eventFilterDemo();
        eventHandlerDemo();
    }

    public void eventFilterDemo() {
        Button btn = new Button("按钮 - EventFilter");

        // 过滤器——监听所有的鼠标事件类型；
        btn.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol(t.getEventType());
            }
        });
        btn.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("鼠标点击事件");
            }
        });

        this.getChildren().add(btn);
    }

    /**
     * 事件传递
     *
     * Filter执行多次，事件（要求必须是同一类型）会从父组件传递到子组件；
     * Handler执行多次，事件会从子组件传递到父组件；
     *
     * Filter先于Handler执行，然后才是setOn…()设置的事件（传递方式默认是Handler机制）；
     * Button会阻止事件传递；
     *
     */
    public void eventHandlerDemo() {
        Button btn = new Button("按钮 - EventHandler");
        setTopAnchor(btn, 50.0);

        btn.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("Filter - 按钮点击事件 - " + t.getSource() + " - " + t.getTarget());
            }
        });
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("Filter - 节点应用点击事件 - " + t.getSource() + " - " + t.getTarget());
            }
        });
        getBaseScene().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("Filter - 场景图点击事件 - " + t.getSource() + " - " + t.getTarget());
            }
        });

        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("Handler - 按钮点击事件 - " + t.getSource() + " - " + t.getTarget());
                // 阻止事件继续传递；
                // t.consume();
            }
        });
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("Handler - 节点应用点击事件 - " + t.getSource() + " - " + t.getTarget());
            }
        });
        getBaseScene().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol("Handler - 场景图点击事件 - " + t.getSource() + " - " + t.getTarget());
            }
        });

        this.getChildren().add(btn);
    }
}
