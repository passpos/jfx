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
import jfx.scene.control.view.list.*;
import jfx.scene.control.view.table.*;
import jfx.scene.control.view.tree.*;
import jfx.scene.control.view.treetable.*;

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

        // List
        appList.add(ListViewApp.class);
        appList.add(ListViewDemo1.class);
        appList.add(ListViewDemo2.class);
        appList.add(ListCellApp.class);
        appList.add(CheckBoxListCellApp.class);
        appList.add(ChoiceBoxListCellApp.class);
        appList.add(ComboBoxListCellApp.class);
        appList.add(TextFieldListCellApp.class);

        // Table
        appList.add(TableViewApp.class);

        // Tree
        appList.add(TreeViewApp.class);
        appList.add(TreeViewDemo.class);
        appList.add(TreeItemApp.class);
        appList.add(TreeItemDemo.class);
        appList.add(TreeCellApp.class);
        appList.add(TreeCellDemo1.class);
        appList.add(TreeCellDemo2.class);
        appList.add(TreeCellDemo3.class);

        // TreeTable
        appList.add(TreeTableViewApp.class);

    }
}
