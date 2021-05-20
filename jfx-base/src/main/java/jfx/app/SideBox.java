/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY;
 * without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.app;

import javafx.geometry.Pos;
import jfx.core.app.AbstractSideBox;
import jfx.core.app.ButtonWrapper;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class SideBox extends AbstractSideBox {

    @Override
    public void setButtonStyle(ButtonWrapper btn) {
        String title = btn.getText();
        // 为按钮设置宽度；
        btn.setPrefWidth(this.getBtnBox().getPrefWidth());
        // 设置按钮对齐；
        btn.setAlignment(Pos.CENTER_LEFT);

        if (title.startsWith("Beans - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aa99c4");
            } else {
                btn.setStyle("-fx-background-color: #aa66aa");
            }
        }

        if (title.startsWith("Binding - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aae4aa");
            } else {
                btn.setStyle("-fx-background-color: #aae4c4");
            }
        }

        if (title.startsWith("Property - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #ffe4aa");
            } else {
                btn.setStyle("-fx-background-color: #ffe4c4");
            }
        }

        if (title.startsWith("Values - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aa99c4");
            } else {
                btn.setStyle("-fx-background-color: #aa66aa");
            }
        }

        if (title.startsWith("Collections - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #ffe4aa");
            } else {
                btn.setStyle("-fx-background-color: #ffe4c4");
            }
        }

        if (title.startsWith("Collections - Transformation - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aae4aa");
            } else {
                btn.setStyle("-fx-background-color: #aae4c4");
            }
        }

        if (title.startsWith("Event - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aa99c4");
            } else {
                btn.setStyle("-fx-background-color: #aa66aa");
            }
        }

        if (title.startsWith("Input - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #ffe4aa");
            } else {
                btn.setStyle("-fx-background-color: #ffe4c4");
            }
        }

        if (title.startsWith("Listener - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aae4aa");
            } else {
                btn.setStyle("-fx-background-color: #aae4c4");
            }
        }
    }

}
