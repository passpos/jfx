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
package jfx.swing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class JFrameApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Swing - JFrame";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        JPanel jPanel = getJPanel();
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);
    }

    public static JPanel getJPanel() {
        JPanel jPanel = new JPanel();
        JButton jButton1 = new JButton("按钮1");
        JButton jButton2 = new JButton("按钮2");
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        listenerDemo(jButton1, jButton2);

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        jPanel.setLayout(fl);
        jPanel.setBackground(Color.green);
        return jPanel;
    }

    public static void listenerDemo(JButton jButton1, JButton jButton2) {
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ol("jButton1");
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ol("jButton2");
            }
        });
    }
}
