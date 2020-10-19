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
package jfx.core.launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        test(stage);
        throw new UnsupportedOperationException("Not supported yet.");

    }

    public static void test(Stage stage) {
        Image img = new Image("file:src/fav.jpg");
        System.out.println(img.getUrl());
        //ImageView iv = new ImageView(img);
        AnchorPane ap = new AnchorPane();
        Scene scene = new Scene(ap);
        stage.getIcons().add(img);
        stage.setScene(scene);
        stage.show();
    }

}
