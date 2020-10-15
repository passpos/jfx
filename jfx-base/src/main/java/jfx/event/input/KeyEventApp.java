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

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jfx.utils.app.ContentBox;

/**
 * Package javafx.scene.input
 *
 * public final class KeyEvent extends InputEvent
 *
 * @author realpai <paiap@outlook.com>
 */
public class KeyEventApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - KeyEvent";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Button btn = new Button("按钮");
        // 按键按下
        btn.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                ol("按下的键 = " + t.getCode());
                ol("事件类型 = " + t.getEventType());
                ol("事件源 = " + t.getSource());
                ol("事件目标 = " + t.getTarget());
                ol("事件文本 = " + t.getText());

                if (t.getCode().getName().equals(KeyCode.A.getName())) {

                }
            }
        });
        // 按键释放
        btn.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                ol("释放");
            }
        });
        // 按键输入焦点，作用于输入框等拥有输入焦点的节点上；
        btn.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                ol("输入事件输入字符 = " + t.getCharacter());
            }
        });
        this.getChildren().add(btn);
    }

}
