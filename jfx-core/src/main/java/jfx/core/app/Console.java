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

package jfx.core.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class Console extends FlowPane {

    /**
     * 设置控制台的默认样式
     */
    public void setDefaultStyle() {
        // 设置布局的内边距和子元素的对齐方式
        this.setPadding(new Insets(10.0));
        this.setAlignment(Pos.CENTER_LEFT);

        // 设置子元素的水平与垂直间隔
        this.setHgap(10.0);
        this.setVgap(5.0);

        this.setOpacity(0.7);
    }
}
