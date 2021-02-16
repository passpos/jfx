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
import jfx.scene.shape.*;
import jfx.scene.shape.curve.*;
import jfx.scene.shape.d3d.*;
import jfx.scene.shape.line.*;
import jfx.scene.shape.path.*;

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

        // Shape
        appList.add(ShapeApp.class);
        appList.add(RectangleApp.class);
        appList.add(CircleApp.class);
        appList.add(EllipseApp.class);
        appList.add(ArcApp.class);
        appList.add(PolygonApp.class);

        // Shape - Curve
        appList.add(QuadCurveApp.class);
        appList.add(CubicCurveApp.class);

        // Shape - 3D
        appList.add(CoordinateSpaceApp.class);
        appList.add(BoxApp.class);
        appList.add(CylinderApp.class);
        appList.add(SphereApp.class);
        appList.add(MeshViewApp.class);

        // Shape - Line
        appList.add(LineApp.class);
        appList.add(PolylineApp.class);

        // Shape - Path
        appList.add(PathApp.class);
        appList.add(SVGPathApp.class);

    }
}
