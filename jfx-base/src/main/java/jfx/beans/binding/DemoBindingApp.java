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
package jfx.beans.binding;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DemoBindingApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - Demo";
    private Button btn;
    private TextField tf1;
    private TextField tf2;

    @Override
    public void index() {
        baseDemo();
        syncInputTextDemo();
        converterDemo();
        sliderDemo();
    }

    public void baseDemo() {
        btn = new Button("绑定按钮");
        this.getChildren().add(btn);

        btn.prefWidthProperty().bind(this.widthProperty());
    }

    /**
     * 同步输入框中的输入的文本。
     */
    public void syncInputTextDemo() {
        tf1 = new TextField();
        tf2 = new TextField();
        this.getChildren().addAll(tf1, tf2);
        setTopAnchor(tf1, 30.0);
        setTopAnchor(tf2, 60.0);

        // 方法1
        tf1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                tf2.setText(t1);
            }
        });
        tf2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                tf1.setText(t1);
            }
        });

        // 方法2
        tf1.textProperty().bindBidirectional(tf2.textProperty());
    }

    /**
     * 对输入字符串中的特定字符进行转换。
     *
     * 由于是双向绑定，所以转换发生在两边的输入框中；
     */
    public void converterDemo() {
        tf1.textProperty().bindBidirectional(tf2.textProperty(), new StringConverter<String>() {
            @Override
            public String toString(String t) {
                return t;
            }

            @Override
            public String fromString(String string) {
                if (string.contains("5")) {
                    String str = string.replace("5", "五");
                    return str;
                }
                return string;
            }
        });
    }

    /**
     * 由于 Slider 的最大值是100，而btn移动的数量以Slider的value为单位，
     * 所以移动量可能偏小，如果需要更大的移动量，就调大Slider参数2的大小，这并不
     * 改变Slider的长度（这由widthProperty决定）。
     */
    public void sliderDemo() {
        Slider s = new Slider(0, 100, 5);
        this.getChildren().add(s);
        setTopAnchor(s, 100.0);
        s.setPrefWidth(300);

        btn.translateYProperty().bind(s.valueProperty());

    }
}
