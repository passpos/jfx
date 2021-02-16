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

import jfx.core.app.ContentBox;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TextAreaApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - TextArea 多行文本输入";
    public TextArea ta;

    @Override
    public void index() {

        baseDemo();

        // 常见的选择操作：
        selectDemo();

        // 格式化文本
        formatDemo();

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                getPrimaryStage().close();
            }
        });
    }

    /**
     * 一般操作
     */
    public void baseDemo() {
        ta = new TextArea("TextArea1（30列5行）");
        ta.setWrapText(true);      // 设置自动换行（不会出现水平滚动条）；
        ta.setPrefColumnCount(30);
        ta.setPrefRowCount(5);
//        ta.setPrefWidth(100);        // 对宽高的设置优先生效；
        ta.appendText("blabla");
        ta.deleteText(0, 2);
        ta.insertText(0, "“这是插入文本”");
        ta.replaceText(0, 1, "“这是替换文本”");
        this.getChildren().add(ta);
    }

    // 常见的选择操作：
    public void selectDemo() {
        TextArea lta = new TextArea("TextArea2");
        lta.setText("因为使用textArea.clear()清空了内容，所以重新设置后只有这么一句");
        lta.selectAll();
        lta.selectForward();
        lta.selectBackward();
        lta.selectPositionCaret(5);
        lta.selectRange(7, 15);
        lta.home();
        lta.selectEnd();
        lta.end();
        lta.selectHome();
        lta.setEditable(false);
        lta.clear();
        this.getChildren().add(lta);
    }

    // 设置内容
    public void formatDemo() {
        TextArea lta = new TextArea();

        lta.setText("TextArea2");
        lta.setTextFormatter(new TextFormatter<String>(new StringConverter<String>() {
            @Override
            public String toString(String t) {
                return t;
            }

            @Override
            public String fromString(String string) {
                if (string.contains("a")) {
                    String value = string.replace("a", "b");
                    return value;
                }
                return string;
            }
        }));

        // 提交内容（默认是通过输入焦点切换进行提交）；
        // ta.commitValue();
        AnchorPane.setTopAnchor(lta, 200.0);
        this.getChildren().add(lta);
    }
}
