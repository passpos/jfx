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
import jfx.scene.chart.*;
import jfx.scene.control.*;
import jfx.scene.control.bar.*;
import jfx.scene.control.button.*;
import jfx.scene.control.dialog.*;
import jfx.scene.control.enums.*;
import jfx.scene.control.input.*;
import jfx.scene.control.menubox.*;
import jfx.scene.control.pane.*;
import jfx.scene.control.progress.*;
import jfx.scene.control.view.list.CheckBoxListCellApp;
import jfx.scene.control.view.list.ChoiceBoxListCellApp;
import jfx.scene.control.view.list.ComboBoxListCellApp;
import jfx.scene.control.view.list.ListCellApp;
import jfx.scene.control.view.list.ListViewApp;
import jfx.scene.control.view.list.ListViewDemo1App;
import jfx.scene.control.view.list.ListViewDemo2App;
import jfx.scene.control.view.list.TextFieldListCellApp;
import jfx.scene.control.view.table.TableViewApp;
import jfx.scene.control.view.tree.TreeCellApp;
import jfx.scene.control.view.tree.TreeViewApp;
import jfx.scene.control.view.treetable.TreeTableViewApp;

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

        // Chart
        appList.add(XYChartApp.class);
        appList.add(PieChartApp.class);
        appList.add(BarChartApp.class);
        appList.add(LineChartApp.class);
        appList.add(BubbleChartApp.class);
        appList.add(StackedBarChartApp.class);
        appList.add(ScatterChartApp.class);
        appList.add(AreaChartApp.class);
        appList.add(StackedAreaChartApp.class);
        appList.add(DemoChartApp.class);

        // Control
        appList.add(ContextMenuApp.class);
        appList.add(PaginationApp.class);
        appList.add(SeparatorApp.class);
        appList.add(SliderApp.class);
        appList.add(SpinnerApp.class);
        appList.add(TooltipApp.class);

        // Progress
        appList.add(ProgressBarApp.class);
        appList.add(ProgressIndicatorApp.class);

        // Bar
        appList.add(ButtonBarApp.class);
        appList.add(ScrollBarApp.class);

        // Button
        appList.add(CheckBoxApp.class);
        appList.add(HyperlinkApp.class);
        appList.add(MenuButtonApp.class);
        appList.add(SplitMenuButtonApp.class);
        appList.add(RadioButtonApp.class);
        appList.add(ToggleButtonApp.class);

        // Dialog
        appList.add(DialogApp.class);
        appList.add(AlertApp.class);
        appList.add(ChoiceDialogApp.class);
        appList.add(TextInputDialogApp.class);
        appList.add(AlertTypeApp.class);
        appList.add(DemoCustomeStageApp.class);

        // Input
        appList.add(TextAreaApp.class);
        appList.add(TextFieldApp.class);
        appList.add(DemoManageTextApp.class);

        // MenuBox
        appList.add(ColorPickerApp.class);
        appList.add(DatePickerApp.class);
        appList.add(MenuBarApp.class);
        appList.add(ChoiceBoxApp.class);
        appList.add(ComboBoxApp.class);
        appList.add(ChoiceBoxDemoApp.class);
        appList.add(ComboBoxDemoApp.class);

        // Pane
        appList.add(ScrollPaneApp.class);
        appList.add(SplitPaneApp.class);
        appList.add(TabPaneApp.class);
        appList.add(TitledPaneApp.class);

        // List
        appList.add(ListViewApp.class);
        appList.add(ListViewDemo1App.class);
        appList.add(ListViewDemo2App.class);
        appList.add(ListCellApp.class);
        appList.add(CheckBoxListCellApp.class);
        appList.add(ChoiceBoxListCellApp.class);
        appList.add(ComboBoxListCellApp.class);
        appList.add(TextFieldListCellApp.class);

        // Table
        appList.add(TableViewApp.class);

        // Tree
        appList.add(TreeViewApp.class);
        appList.add(TreeCellApp.class);

        // TreeTable
        appList.add(TreeTableViewApp.class);

    }
}
