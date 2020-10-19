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
package jfx.scene.control.input;

import jfx.core.app.ContentBox;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DemoManageTextApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Input - Demo 文本操作演示";
    public TextField tf;
    int endIndex = 0;
    String subStr = "";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        // 待查找内容输入
        tf = new TextField();

        // 查找域
        TextArea ta = new TextArea();
        ta.setWrapText(true);

        // 动作按钮
        Button findBtn = new Button("查找");
        Button sortBtn = new Button("排序");
        HBox hb = new HBox(10);
        hb.getChildren().addAll(tf, findBtn, sortBtn);

        VBox vb = new VBox(10);
        vb.getChildren().addAll(hb, ta);

        AnchorPane.setTopAnchor(vb, 10.0);
        AnchorPane.setLeftAnchor(vb, 10.0);

        findBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ta.getParagraphs().forEach(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence t) {
                        ta.requestFocus();
                        String text = t.toString();
                        String findWord = tf.getText();
                        subStr = text.substring(endIndex);
                        if (subStr.contains(findWord)) {
                            int l = findWord.length();
                            // 子串查找的开始索引
                            int start = subStr.indexOf(findWord);
                            // 子串查找的结束索引位置
                            int end = start + l;

                            // 用于选择的实际开始位置
                            int startIndex = endIndex + start;
                            // 用于选择的实际结束位置
                            endIndex += end;

                            ta.selectRange(startIndex, endIndex);
                        } else {
                            ol("没有了");
                            endIndex = 0;
                        }
                    }
                });
            }
        });
        sortBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                char[] charry = ta.getText().toCharArray();
                ArrayList<String> list = new ArrayList<String>();

                for (char c : charry) {
                    list.add(String.valueOf(c));
                }

                ta.clear();

                list.stream().sorted((String o1, String o2) -> o1.compareTo(o2))
                        .forEach((String t1) -> {
                            ta.appendText(t1);
                        });

                Stream<String> stm = list.stream().sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }

                });
                stm.forEach(new Consumer<String>() {
                    @Override
                    public void accept(String t) {
                        ta.appendText(t);
                    }
                });
            }
        });
        this.getChildren().add(vb);
    }

}
