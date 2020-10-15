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
package jfx.scene.control.bar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ButtonBarApp extends ContentBox implements EventHandler<ActionEvent> {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Bar - ButtonBar 按钮栏";

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (ButtonBar.getButtonData(btn) == ButtonData.APPLY) {
            ol("按钮APPLY");
        }
        if (ButtonBar.getButtonData(btn) == ButtonData.NO) {
            ol("按钮NO");
        }
        if (ButtonBar.getButtonData(btn) == ButtonData.FINISH) {
            ol("按钮FINISH");
        }

    }

    @Override
    public String toString() {
        return "ButtonBarApp{" + "title=" + TITLE + '}';
    }

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        ButtonBar bb = new ButtonBar();
        Button btn1 = new Button("btn1-APPLY");
        Button btn2 = new Button("btn2-FINISH");
        Button btn3 = new Button("btn3-NO");
        btn1.setOnAction(this);
        btn2.setOnAction(this);
        btn3.setOnAction(this);

        ButtonBar.setButtonData(btn1, ButtonBar.ButtonData.APPLY);
        ButtonBar.setButtonData(btn2, ButtonBar.ButtonData.FINISH);
        ButtonBar.setButtonData(btn2, ButtonBar.ButtonData.NO);

        bb.getButtons().addAll(btn1, btn2, btn3);
        bb.setButtonOrder(ButtonBar.BUTTON_ORDER_NONE);
        ButtonBar.setButtonUniformSize(btn3, true);

        this.getChildren().add(bb);
    }
}
