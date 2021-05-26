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
package jfx.scene.control.button;

import java.util.Iterator;
import java.util.function.Consumer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class CheckBoxApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Button - CheckBox 多选按钮";
    public VBox vb = new VBox();

    @Override
    public void index() {
        base();

        // 获取所有多选项的选择状态
        selectDemo();
    }

    public void base() {
        CheckBox cb1 = new CheckBox("cb1");
        cb1.setSelected(true);

        // 获取某个多选项的选择状态；
        cb1.selectedProperty().addListener((ov, t, t1) -> {
            ol(t1.toString());
        });

        CheckBox cb2 = new CheckBox("cb2");
        cb2.setIndeterminate(true);     // 不确定状态；

        CheckBox cb3 = new CheckBox("cb3");
        cb3.setAllowIndeterminate(true);

        CheckBox cb4 = new CheckBox("cb4");

        vb = new VBox();
        vb.getChildren().addAll(cb1, cb2, cb3, cb4);
        this.getChildren().add(vb);

    }

    /**
     * 获取所有多选项的选择状态；
     */
    public void selectDemo() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Object[] obj = vb.getChildren().toArray();
                for (Object o : obj) {
                    CheckBox c = (CheckBox) o;
                    ol(c.getText() + "的状态是" + c.isSelected() + "\n不确定状态是：" + c.isIndeterminate());
                }

                // 与上面的代码等价（一个是将子选项转换为数组，一个是进行迭代）；
                Iterator<Node> it = vb.getChildren().iterator();
                while (it.hasNext()) {
                    CheckBox c = (CheckBox) it.next();
                    ol(c.getText() + "的状态是" + c.isSelected() + "\n不确定状态是：" + c.isIndeterminate());
                }

                // 与上面的代码等价；
                vb.getChildren().forEach(new Consumer<Node>() {
                    @Override
                    public void accept(Node t) {
                        CheckBox c = (CheckBox) t;
                        ol(c.getText() + "的状态是" + c.isSelected() + "\n不确定状态是：" + c.isIndeterminate());
                    }

                });

                // 与上面的代码等价；
//                vb.getChildren().stream();
            }

        });
    }
}
