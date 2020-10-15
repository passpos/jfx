/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.concurrent;

import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class WorkerInterfaceApp extends ContentBox {

    public final static Boolean SHOWING = false;
    public final static String TITLE = "Concurrent - Worker接口";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        ol(Thread.currentThread().getName());

    }
}
