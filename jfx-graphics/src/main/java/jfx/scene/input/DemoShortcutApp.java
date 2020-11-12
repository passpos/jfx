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
package jfx.scene.input;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DemoShortcutApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - Demo 快捷键";

    @Override
    public void index() {
        baseDemo();
        stringKeyCombination();
        keyCodeCombination();
        bySetAccelerator();
    }

    /**
     * 节点的的快捷键，会激活节点的setOnAction()点击事件；
     * 有些快捷键在设置时，也可以另外设置动作；
     */
    public void baseDemo() {
        Button btn = new Button("确认");
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
        getBaseScene().addMnemonic(mn);

        this.getChildren().add(btn);
    }

    /**
     * 方法4：组合快捷键（可以设置区分按键状态，并设置响应动作）
     *
     * 该方法设置的快捷键不会绑定具体的节点，在应用的全局生效，与平台无关；
     * 运行时，会创建一个后台任务，所以运行时非常灵活；
     *
     * Win下，SHORTCUT为Ctrl键；
     */
    public void keyCodeCombination() {
        // 设置快捷键
        KeyCombination kc = new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN);

        // 快捷键按下后的动作
        getBaseScene().getAccelerators().put(kc, new Runnable() {
            @Override
            public void run() {
                ol("快捷键");
            }
        });
    }

    /**
     * 方法5：从字符串解析快捷键组合，然后关联到具体的节点
     *
     */
    public void stringKeyCombination() {
        KeyCombination kc = KeyCombination.valueOf("alt+k");

        // 设置关联
        Button btn = new Button("btn5");
        Mnemonic mn = new Mnemonic(btn, kc);

        // 设置快捷键的应用区域为整个场景；
        getBaseScene().addMnemonic(mn);

        this.getChildren().add(btn);
    }

    /**
     * 方法6：对节点执行setAccelerator()方法；
     */
    public void bySetAccelerator() {
        MenuItem itm6 = new MenuItem("itm6");
        itm6.setAccelerator(KeyCombination.valueOf("alt+j"));
    }
}
