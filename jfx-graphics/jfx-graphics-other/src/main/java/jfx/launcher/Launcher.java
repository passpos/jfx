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
package jfx.launcher;

import jfx.core.app.Switcher;
import jfx.app.AppBox;
import jfx.app.SideBox;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class Launcher extends Application {

    private Stage primaryStage;
    private Switcher switcher;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        setSwitcher();
        primaryStage.setScene(switcher.getBaseScene());
        primaryStage.show();
    }

    protected void setSwitcher() {
        SideBox sBox = new SideBox();       // 1
        AppBox appBox = new AppBox(sBox);   // 2
        sBox.setAppBox(appBox);
        switcher = new Switcher(this, primaryStage, sBox, appBox);  // 3
    }
}
