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
import jfx.beans.binding.*;
import jfx.beans.listener.*;
import jfx.beans.property.*;
import jfx.beans.value.listener.*;
import jfx.collections.*;
import jfx.core.app.AbstractAppBox;
import jfx.core.app.AbstractSideBox;
import jfx.core.app.ContentBox;
import jfx.event.*;
import jfx.event.input.*;

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
        // Binding
        appList.add(BindingApp.class);
        appList.add(BindingDemo1.class);
        appList.add(BindingDemo2.class);
        appList.add(BindingDemo3.class);
        appList.add(BindingsApp.class);
        appList.add(UnidirectionalBindingApp.class);
        appList.add(BidirectionalBindingApp.class);
        appList.add(ComputingApp.class);
        appList.add(LogicApp.class);
        appList.add(WhenApp.class);
        appList.add(CollectionBindingApp.class);
        appList.add(CollectionBindingDemo1.class);
        appList.add(CollectionBindingDemo2.class);
        appList.add(CollectionBindingDemo3.class);
        appList.add(CollectionBindingDemo4.class);

        // Property
        appList.add(PropertyChangeSupportApp.class);
        appList.add(PropertyApp.class);
        appList.add(ListPropertyApp.class);
        appList.add(ListPropertyDemo.class);
        appList.add(SetPropertyApp.class);
        appList.add(MapPropertyApp.class);

        // Value
        // Collections
        appList.add(ObservableListApp.class);

        // Listener
        // Beans.Listener
        appList.add(InvalidationListenerApp.class);
        appList.add(WeakListenerApp.class);
        appList.add(WeakListenerDemo.class);
        // Value.Listener
        appList.add(ChangeListenerApp.class);
        appList.add(WeakListenerApp.class);

        // Event
        appList.add(EventApp.class);

        // InputEvent
        appList.add(KeyEventApp.class);
        appList.add(MouseEventApp.class);
    }

}
