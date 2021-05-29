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
package jfx.embed.swing;

import javafx.embed.swing.SwingNode;
import javax.swing.JPanel;
import jfx.core.app.ContentBox;
import jfx.swing.JFrameApp;

/**
 * SwingNode extends Node
 *
 * @author realpai <paiap@outlook.com>
 */
public class SwingNodeApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "JFX - SwingNode";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        JPanel jPanel = JFrameApp.getJPanel();

        SwingNode sn = new SwingNode();
        sn.setContent(jPanel);
        sn.resize​(500, 200);
        sn.prefWidth(500);
        sn.prefHeight(200);

        setTopAnchor(sn, 30.0);
        getChildren().add(sn);
    }
}
