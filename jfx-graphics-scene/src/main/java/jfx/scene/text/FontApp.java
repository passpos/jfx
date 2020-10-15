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

import java.util.function.Consumer;
import static javafx.scene.layout.AnchorPane.setTopAnchor;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import jfx.utils.app.ContentBox;

/**
 * Win下，在 系统设置 - 个性化 - 字体，查看系统支持的字体名称和显示效果；
 * 支持 TTF、TTC、OTF格式的字体文件；
 *
 * @author realpai <paiap@outlook.com>
 */
public class FontApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Text - Font";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        VBox vBox = new VBox();

        Font f1 = Font.font("Courier", FontWeight.THIN, FontPosture.ITALIC, 25);
        Text t1 = new Text("Hello World");
        t1.setFont(f1);
        setTopAnchor(t1, 10.0);

        vBox.getChildren().add(t1);
        Font.getFamilies().forEach(new Consumer<String>() {
            @Override
            public void accept(String f) {
                Text t = new Text(f);
                vBox.getChildren().add(t);
            }
        });
        getChildren().addAll(vBox);

    }
}
