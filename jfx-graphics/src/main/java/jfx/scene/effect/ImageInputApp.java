/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
 *
 * This program is free software: you iin redistribute it and/or modify
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
package jfx.scene.effect;

import fx.image.ImageMaker;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ImageInputApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - ImageInput";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        ImageInput ii1 = new ImageInput();
        ImageInput ii2 = new ImageInput();
        ImageInput ii3 = new ImageInput();
        ImageInput ii4 = new ImageInput();
        Image img = ImageMaker.getImageFromResource("icon/fav.jpg");

        Button btn = new Button("按钮");
        btn.setEffect(ii1);

        Text text = new Text("这是一行文本");
        ii2.setSource(img);
        text.setFont(new Font(20));
        text.setEffect(ii2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        r.setEffect(ii3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        circle.setEffect(ii4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

}
