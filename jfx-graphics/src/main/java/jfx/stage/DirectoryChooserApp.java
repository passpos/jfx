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
package jfx.stage;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DirectoryChooserApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Stage - DirectoryChooser";
    private VBox vBox;

    @Override
    public void index() {
        vBox = new VBox(10);

        Button btn = new Button("文件夹选择窗口");
        baseDemo(btn);

        vBox.getChildren().add(btn);
        this.getChildren().add(vBox);
    }

    public void baseDemo(Button btn) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Stage stage = new Stage();

                DirectoryChooser dc = new DirectoryChooser();
                dc.setTitle("选择文件夹");
                dc.setInitialDirectory(new File("D:" + File.separator + "Projects"));
                File f = dc.showDialog(stage);

                if (f != null) {
                    ob(f.getAbsolutePath(), 300.0);
                    File[] lf = f.listFiles();
                    for (File file : lf) {
                        ob(file.getAbsolutePath(), 300.0);
                    }
                }
            }
        });
    }
}
