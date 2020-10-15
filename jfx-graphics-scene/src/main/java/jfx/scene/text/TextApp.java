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
package jfx.scene.text;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.utils.app.ContentBox;

/**
 * Text是Shape的子类
 * @author realpai <paiap@outlook.com>
 */
public class TextApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Text - Text";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Font font1 = new Font("Courier", 25);
        Font font2 = new Font("Impact", 25);
        Font font3 = Font.loadFont(loadResource("fonts/汉仪尚巍手书.ttf").toExternalForm(), 25);
        Font font4 = Font.loadFont(loadResource("fonts/simsunb.ttf").toExternalForm(), 25);
        Font font5 = Font.loadFont(loadResource("fonts/msyh.ttc").toExternalForm(), 25);
        Font font6 = Font.loadFont(loadResource("fonts/HiraginoSansGBW6.otf").toExternalForm(), 25);

        Label label = new Label("Text是Shape的子类");
        label.setFont(font5);
        setLeftAnchor(label, 300.0);

        Text t = new Text("Hello World");
        setTopAnchor(t, 10.0);

        Text t1 = new Text("Hello World - Courier");
        t1.setFont(font1);
        setTopAnchor(t1, 50.0);

        Text t2 = new Text("Hello World - Impact");
        t2.setFont(font2);
        setTopAnchor(t2, 100.0);

        Text t3 = new Text("Hello World - 汉仪尚巍手书");
        t3.setFont(font3);
        setTopAnchor(t3, 150.0);

        Text t4 = new Text("Hello World - SimsunNB");
        t4.setFont(font4);
        setTopAnchor(t4, 200.0);

        Text t5 = new Text("Hello World - HiraginoSansGBW6");
        t5.setFont(font5);
        setTopAnchor(t5, 250.0);

        Text t6 = new Text("Hello World - 微软雅黑");
        t6.setFont(font6);
        setTopAnchor(t6, 300.0);

        getChildren().addAll(label, t, t1, t2, t3, t4, t5, t6);
    }
}
