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
import jfx.application.*;
import jfx.concurrent.*;
import jfx.core.app.AbstractAppBox;
import jfx.core.app.AbstractSideBox;
import jfx.core.app.ContentBox;
import jfx.geometry.*;
import jfx.scene.*;
import jfx.scene.canvas.*;
import jfx.scene.image.*;
import jfx.scene.input.*;
import jfx.scene.paint.*;
import jfx.scene.paint.material.*;
import jfx.scene.text.*;
import jfx.scene.transform.*;
import jfx.stage.*;

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

        // Application
        appList.add(ApplicationApp.class);
        appList.add(PlatformApp.class);

        // Concurrent
        appList.add(WorkerInterfaceApp.class);
        appList.add(TaskApp.class);
        appList.add(ServiceApp.class);
        appList.add(ScheduledServiceApp.class);

        // Scene
        appList.add(SceneApp.class);
        appList.add(SubSceneApp.class);
        appList.add(NodeApp.class);
        appList.add(CursorApp.class);
        appList.add(GroupApp.class);
        appList.add(PerspectiveCameraApp.class);
        appList.add(AmbientLightApp.class);
        appList.add(PointLightApp.class);

        // Canvas
        appList.add(CanvasApp.class);

        // Geometry
        appList.add(InsetsApp.class);
        // Geometry - Enum
        appList.add(PosApp.class);

        // Image
        appList.add(ImageApp.class);
        appList.add(ImageViewApp.class);
        appList.add(PixelReaderApp.class);
        appList.add(PixelWriterApp.class);
        appList.add(PixelFormatApp.class);
        appList.add(WritablePixelFormatApp.class);
        appList.add(WritableImageApp.class);

        // Input
        appList.add(ClipboardApp.class);
        appList.add(ClipboardContentApp.class);
        appList.add(ClipboardContentDemo.class);
        appList.add(DragboardApp.class);
        appList.add(DragboardDemo.class);
        appList.add(DragEventApp.class);
        appList.add(ShortcutDemo.class);

        // Paint
        appList.add(PaintAndColorApp.class);
        appList.add(LinearGradientApp.class);
        appList.add(ImagePatternApp.class);
        appList.add(RadialGradientApp.class);

        // Paint - Material
        appList.add(PhongMaterialApp.class);

        // Text
        appList.add(TextApp.class);
        appList.add(FontApp.class);

        // Transform
        appList.add(TranslateApp.class);
        appList.add(ScaleApp.class);
        appList.add(RotateApp.class);
        appList.add(ShearApp.class);
        appList.add(AffineApp.class);

        // Satge
        appList.add(DirectoryChooserApp.class);
        appList.add(FileChooserApp.class);
        appList.add(ScreenApp.class);
        appList.add(StageApp.class);
        appList.add(StageDemo.class);
        appList.add(WindowApp.class);
    }
}
