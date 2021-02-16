/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.application;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import jfx.core.app.ContentBox;

/**
 * Platform的所有开放方法都是静态的，所以不能实例化（无方法可用）；
 * @author realpai <paiap@outlook.com>
 */
public class PlatformApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "Application - Platform";

    @Override
    public void index() {
        baseDemo();
        supportDemo();
    }

    public void baseDemo() {
        ol("isAccessibilityActive() \t" + Platform.isAccessibilityActive());
        ol("isFxApplicationThread() \t" + Platform.isFxApplicationThread());
        ol("isImplicitExit() \t\t" + Platform.isImplicitExit());
        ol("isNestedLoopRunning() \t" + Platform.isNestedLoopRunning());
    }

    public void supportDemo() {
        ol("CONTROLS：\t\t" + Platform.isSupported(ConditionalFeature.CONTROLS));
        ol("EFFECT：\t\t" + Platform.isSupported(ConditionalFeature.EFFECT));
        ol("FXML：\t\t" + Platform.isSupported(ConditionalFeature.FXML));
        ol("GRAPHICS：\t\t" + Platform.isSupported(ConditionalFeature.GRAPHICS));
        ol("INPUT_METHOD：\t" + Platform.isSupported(ConditionalFeature.INPUT_METHOD));
        ol("INPUT_MULTITOUCH：\t" + Platform.isSupported(ConditionalFeature.INPUT_MULTITOUCH));
        ol("INPUT_POINTER：\t" + Platform.isSupported(ConditionalFeature.INPUT_POINTER));
        ol("INPUT_TOUCH：\t" + Platform.isSupported(ConditionalFeature.INPUT_TOUCH));
        ol("MEDIA：\t\t" + Platform.isSupported(ConditionalFeature.MEDIA));
        ol("SCENE3D：\t\t" + Platform.isSupported(ConditionalFeature.SCENE3D));
        ol("SHAPE_CLIP：\t\t" + Platform.isSupported(ConditionalFeature.SHAPE_CLIP));
        ol("SWING：\t\t" + Platform.isSupported(ConditionalFeature.SWING));
        ol("SWT：\t\t" + Platform.isSupported(ConditionalFeature.SWT));
        ol("TRANSPARENT_WINDOW：\t" + Platform.isSupported(ConditionalFeature.TRANSPARENT_WINDOW));
        ol("TWO_LEVEL_FOCUS：\t" + Platform.isSupported(ConditionalFeature.TWO_LEVEL_FOCUS));
        ol("UNIFIED_WINDOW：\t" + Platform.isSupported(ConditionalFeature.UNIFIED_WINDOW));
        ol("VIRTUAL_KEYBOARD：\t" + Platform.isSupported(ConditionalFeature.VIRTUAL_KEYBOARD));
        ol("WEB：\t\t" + Platform.isSupported(ConditionalFeature.WEB));
    }
}
