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
 * along with cBox program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.app;

import java.util.ArrayList;
import jfx.beans.*;
import jfx.beans.binding.*;
import jfx.beans.property.*;
import jfx.beans.value.*;
import jfx.collections.*;
import jfx.event.*;
import jfx.event.input.*;
import jfx.utils.app.AbstractAppBox;
import jfx.utils.app.AbstractSideBox;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class AppBox extends AbstractAppBox {

    public AppBox(AbstractSideBox sideBox) {
        super(sideBox);
    }

    /**
     * 在此处，将应用添加到应用集
     * step-7
     */
    @Override
    public void initAppList() {
        ArrayList<Class<? extends ContentBox>> appList = getAppList();

        // Beans
        appList.add(InvalidationListenerApp.class);
        appList.add(WeakListenerApp.class);
        appList.add(WeakChangeListenerApp.class);

        // Binding
        appList.add(BindingsApp.class);
        appList.add(BindingApp.class);
        appList.add(UnidirectionalBindingApp.class);
        appList.add(BidirectionalBindingApp.class);
        appList.add(ComputingApp.class);
        appList.add(LogicApp.class);
        appList.add(WhenApp.class);
        appList.add(SimpleListBindingApp.class);
        appList.add(DemoBindingApp.class);

        // Property
        appList.add(ChangeSupportApp.class);
        appList.add(PropertyApp.class);
        appList.add(ListPropertyApp.class);
        appList.add(SetPropertyApp.class);
        appList.add(MapPropertyApp.class);

        // Value
        appList.add(ChangeListenerApp.class);

        // Collections
        appList.add(ObservableListApp.class);

        // Event
        appList.add(EventApp.class);

        // InputEvent
        appList.add(KeyEventApp.class);
        appList.add(MouseEventApp.class);
    }

}
