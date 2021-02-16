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
        String title = btn.getTitle();

        // 为按钮设置宽度；
        btn.setPrefWidth(this.getBtnBox().getPrefWidth());

        if (title.startsWith("Control - ")) {
            btn.setStyle("-fx-background-color: #ffe4aa");
        }

        if (title.startsWith("Progress - ")) {
            btn.setStyle("-fx-background-color: #aae4aa");
        }

        if (title.startsWith("Bar - ")) {
            btn.setStyle("-fx-background-color: #aa99c4");
        }

        if (title.startsWith("Button - ")) {
            btn.setStyle("-fx-background-color: #ffe4aa");
        }

        if (title.startsWith("Dialog - ")) {
            btn.setStyle("-fx-background-color: #aae4aa");
        }

        if (title.startsWith("Input - ")) {
            btn.setStyle("-fx-background-color: #aa99c4");
        }

        if (title.startsWith("MenuBox - ")) {
            btn.setStyle("-fx-background-color: #ffe4aa");
        }

        if (title.startsWith("Pane - ")) {
            btn.setStyle("-fx-background-color: #aae4aa");
        }

        btn.setAlignment(Pos.CENTER_LEFT);
    }

}
