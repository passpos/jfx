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
package jfx.scene.web;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.web.HTMLEditor;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class HTMLEditorApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Web - HTMLEditor";
    private HTMLEditor editor;

    @Override
    public void index() {
        setEditor();
        setEditorStyle();
        baseDemo();
    }

    public void baseDemo() {
        // 与属性绑定冲突
        // editor.setPrefSize(500, 350);
        editor.setHtmlText("<p>这是一个HTML段落</p>"
                + "<img src=\""
                + getClass().getResource("/img/1.jpg")
                + "\" />");

        Button btn = new Button("提交");
        btn.setPrefWidth(50);
        btn.setPrefHeight(20);
        getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol(editor.getHtmlText());
            }
        });
    }

    /**
     * Set the value of editor
     *
     */
    private void setEditor() {
        editor = new HTMLEditor();
        setTopAnchor(editor, 30.0);
        getChildren().add(editor);
    }

    private void setEditorStyle() {
        String css = "-fx-font: 12 cambria;"
                + "-fx-border-color: black;"
                + "-fx-border-style: dotted;"
                + "-fx-border-width: 1.5;";
        editor.setStyle(css);
        editor.prefWidthProperty().bind(this.widthProperty());
        editor.prefHeightProperty().bind(this.heightProperty());
    }
}
