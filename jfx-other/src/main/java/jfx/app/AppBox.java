/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
 *
 * cBox program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * cBox program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with cBox program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.app;

import java.util.ArrayList;
import jfx.core.app.AbstractAppBox;
import jfx.core.app.AbstractSideBox;
import jfx.core.app.ContentBox;
import jfx.embed.swing.*;
import jfx.features.other.*;
import jfx.fxml.*;
import jfx.scene.media.*;
import jfx.scene.web.*;
import jfx.scene.web.editor.HTMLEditorApp;
import jfx.scene.web.editor.HTMLEditorDemo1;
import jfx.scene.web.editor.HTMLEditorSkinApp;
import jfx.scene.web.editor.JEditorPaneApp;
import jfx.scene.web.editor.WebEditor1;
import jfx.swing.*;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class AppBox extends AbstractAppBox {

    public AppBox(AbstractSideBox sideBox) {
        super(sideBox);
    }

    @Override
    public void initAppList() {        // 7
        ArrayList<Class<? extends ContentBox>> appList = getAppList();

        // FXML
        appList.add(FXMLDemoApp.class);
        appList.add(ImageViewDemoApp.class);
        appList.add(BuilderFactoryApp.class);
        appList.add(LocalizationApp.class);
        appList.add(StylesheetApp.class);

        // Media
        appList.add(AudioClipApp.class);
        appList.add(MediaApp.class);
        appList.add(MediaPlayerApp.class);
        appList.add(MediaViewApp.class);
        appList.add(MediaPlayerDemoApp.class);

        // Web
        appList.add(WebViewApp.class);
        appList.add(WebViewDemo6.class);
        appList.add(WebEngineApp.class);
        appList.add(WebHistoryApp.class);

        // Editor
        appList.add(HTMLEditorApp.class);
        appList.add(HTMLEditorDemo1.class);
        appList.add(HTMLEditorSkinApp.class);
        appList.add(JEditorPaneApp.class);
        appList.add(WebEditor1.class);

        // JavaFX Swing
        appList.add(SwingNodeApp.class);

        // Swing
        appList.add(JFrameApp.class);

        // Features
        appList.add(ReflectionApp.class);
        appList.add(CloneApp.class);
    }
}
