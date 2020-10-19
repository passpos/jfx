/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
 *
 * appList program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * appList program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with appList program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.core.app;

import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public abstract class AbstractAppBox extends AnchorPane {

    private ArrayList<Class<? extends ContentBox>> appList;
    private AbstractSideBox sideBox;

    /**
     * step-2
     * @param sideBox
     */
    public AbstractAppBox(AbstractSideBox sideBox) {
        this.sideBox = sideBox;
        this.appList = new ArrayList<>();
    }

    /**
     * step-7
     */
    public abstract void initAppList();

    public ArrayList<Class<? extends ContentBox>> getAppList() {
        return appList;
    }

    public AbstractSideBox getSideBox() {
        return sideBox;
    }

    @Override
    public String toString() {
        return "AbstractAppBox{\n"
                + "\tsideBox=" + sideBox
                + ", \n\tappList=" + appList
                + "\n}";
    }

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }
}
