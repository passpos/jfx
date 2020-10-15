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
package jfx.scene.control.input;

import jfx.utils.app.ContentBox;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Tooltip;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TextFieldApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - TextField 单行文本输入";
    public TextField tf;

    @Override
    public void index() {
        baseDemo();
        this.getChildren().addAll(tf);

        // 限制用户只能输入规定的字符；
        limitDemo();

        // 检查、限制字数
        checkTextDemo();
    }

    public void baseDemo() {
        tf = new TextField();
        tf.setText("blabla");
        tf.setFocusTraversable(false);          // 光标聚焦；
        tf.setPromptText("请输入多于8个字符"); // 提示文本（与预填文本冲突）；

        Tooltip tltp = new Tooltip();
        tltp.setText("输入提示");
        tf.setTooltip(tltp);
    }

    /**
     * 限制用户只能输入规定的字符
     */
    public void limitDemo() {
        tf.setTextFormatter(new TextFormatter<String>(new UnaryOperator<Change>() {
            @Override
            public Change apply(Change t) {
                // t.getText()是每次输入的字符；
                String value = t.getText();
                if (value.matches("[a-z]*")) {
                    return t;
                }
                return null;
            }
        }));
    }

    /**
     * 检查、限制字数
     */
    public void checkTextDemo() {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.length() < 8) {
                    ol(t1.length());
                } else {
                    ol("字数OK");
                }
            }

        });
    }
}
