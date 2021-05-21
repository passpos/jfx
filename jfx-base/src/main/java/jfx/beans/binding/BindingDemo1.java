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

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import jfx.core.app.ContentBox;

/**
 * B-55
 *
 * @author realpai <paiap@outlook.com>
 */
public class BindingDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - Demo";
    private Button btn;

    @Override
    public void index() {
        base();
        slider();
    }

    public void base() {
        btn = new Button("绑定按钮");
        getChildren().add(btn);

        btn.prefWidthProperty().bind(this.widthProperty());
    }

    /**
     * 由于 Slider 的最大值是100，而btn移动的数量以Slider的value为单位，
     * 所以移动量可能偏小，如果需要更大的移动量，就调大Slider参数2的大小，这并不
     * 改变Slider的长度（这由widthProperty决定）。
     */
    public void slider() {
        Slider s = new Slider(0, 100, 5);
        s.setPrefWidth(300);
        setTopAnchor(s, 100.0);
        getChildren().add(s);

        btn.translateYProperty().bind(s.valueProperty().multiply(3));
    }
}
