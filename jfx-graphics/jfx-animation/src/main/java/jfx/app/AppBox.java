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
import jfx.animation.*;
import jfx.animation.demo.*;
import jfx.animation.transition.*;
import jfx.core.app.AbstractAppBox;
import jfx.core.app.AbstractSideBox;
import jfx.core.app.ContentBox;

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
    public void initAppList() {        // 7
        ArrayList<Class<? extends ContentBox>> appList = getAppList();

        // Animation - Timeline
        appList.add(TimelineApp.class);
        appList.add(TimelineDemo.class);
        appList.add(AnimationTimerApp.class);

        // Animation - Transition
        appList.add(TranslateTransitionApp.class);
        appList.add(RotateTransitionApp.class);
        appList.add(ScaleTransitionApp.class);
        appList.add(FadeTransitionApp.class);
        appList.add(FillTransitionApp.class);
        appList.add(StrokeTransitionApp.class);
        appList.add(PathTransitionApp.class);
        appList.add(ParallelTransitionApp.class);
        appList.add(SequentialTransitionApp.class);

        // Animation - Demo
        appList.add(PhotoSwitcher.class);
        appList.add(Snow.class);

    }
}
