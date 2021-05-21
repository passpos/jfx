/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.beans.listener;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import jfx.core.app.ContentBox;

/**
 * B-54 防止内存泄漏
 *
 * 使用匿名内部类的方式，本身潜在一些安全隐患，这在事件监听中尤其凸显；
 * 可以使用 Interface javafx.beans.WeakListener 的实现：
 * WeakInvalidationListener
 * WeakChangeListener
 * 参考：WeakChangeListenerApp
 *
 * 这里是将Listener提取到公用位置，用完后就移除；
 *
 * @author passpos <paiap@outlook.com>
 */
public class WeakListenerDemo extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Listener - Beans WeakListener noLeak";
    private SimpleIntegerProperty sip;
    private InvalidationListener listener;

    @Override
    public void index() {
        base();
        noLeakDemo();
    }

    public void base() {
        sip = new SimpleIntegerProperty();
        listener = new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                ContentBox.ol("noLeakDemo：" + o);
            }
        };
        sip.addListener(listener);
    }

    public void noLeakDemo() {
        sip.set(5);

        // 移除
        sip.removeListener(listener);
        sip.set(10);
    }

}
