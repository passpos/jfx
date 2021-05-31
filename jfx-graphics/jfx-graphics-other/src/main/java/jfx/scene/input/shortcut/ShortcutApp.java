/*
 * Copyright (C) 2019 realpai <paiap@outlook.com>
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
package jfx.scene.input.shortcut;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import jfx.core.app.ContentBox;

/**
 * B-11
 *
 * @author realpai <paiap@outlook.com>
 */
public class ShortcutApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shortcut - 快捷键";
    private Scene scene;

    @Override
    public void index() {
        base();
        multiKey();
        characterKey();
        multiString();
        putAccelerator();
        setAccelerator();
    }

    public void base() {
        this.scene = getBaseScene();
    }

    /**
     * 方法1：组合快捷键
     */
    public void multiKey() {
        Button btn = new Button("确认");
        getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("快捷键");
            }
        });

        // 设置快捷键（组合Ctrl+Alt+C）
        KeyCombination kc = new KeyCodeCombination(
                KeyCode.C,
                KeyCombination.ALT_DOWN,
                KeyCombination.CONTROL_DOWN);

        // 绑定快捷键（到节点对象）
        Mnemonic mn = new Mnemonic(btn, kc);

        // 设置快捷键的应用区域为整个场景；
        scene.addMnemonic(mn);
    }

    /**
     * 方法2：
     *
     * 节点的的快捷键，会激活节点的setOnAction()点击事件；
     * 有些快捷键在设置时，也可以另外设置动作；
     */
    public void characterKey() {
        Button btn = new Button("确认");
        getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("快捷键");
            }
        });

        // 设置快捷键（组合Ctrl+Alt+C）
        KeyCombination kc = new KeyCharacterCombination(
                "C", KeyCombination.CONTROL_DOWN);

        // 绑定快捷键（到节点对象）
        Mnemonic mn = new Mnemonic(btn, kc);

        // 设置快捷键的应用区域为整个场景；
        scene.addMnemonic(mn);
    }

    /**
     * 方法3：从字符串解析快捷键组合，然后关联到具体的节点；
     *
     */
    public void multiString() {
        Button btn = new Button("btn5");
        getChildren().add(btn);

        KeyCombination kc = KeyCombination.valueOf("alt+k");
        Mnemonic mn = new Mnemonic(btn, kc);

        // 设置快捷键的应用区域为整个场景；
        scene.addMnemonic(mn);
    }

    /**
     * 方法4：全局快捷键
     * 将组合快捷键直接绑定到场景上，响应执行多线程任务。
     * 可以设置区分按键状态，并设置响应动作。
     *
     * 该方法设置的快捷键不会绑定在具体的节点上，而是在应用的全局生效；
     * 运行时，会创建一个后台任务，所以运行时非常灵活；
     *
     * Win下，SHORTCUT为Ctrl键；
     */
    public void putAccelerator() {
        KeyCombination kc = new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN);

        // 响应动作
        scene.getAccelerators().put(kc, new Runnable() {
            @Override
            public void run() {
                ol("快捷键");
            }
        });
    }

    /**
     * 方法5：对 MenuItem 执行setAccelerator()方法；
     */
    public void setAccelerator() {
        MenuItem itm6 = new MenuItem("itm6");
        itm6.setAccelerator(KeyCombination.valueOf("alt+j"));
    }
}
