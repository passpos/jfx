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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.application;

import javafx.application.Application;
import jfx.application.demo.DemoApplication;
import jfx.utils.app.ContentBox;

/**
 * Application是抽象的；
 *
 * Application的启动方式和生命周期；
 * 封装 ------------ 方法 --------- 所处线程
 * static void ----- launch() ----- main
 * void ------------ init() ------- JavaFX-Launcher
 * abstract void --- start() ------ JavaFX Application Thread
 * void ------------ stop() ------- JavaFX Application Thread
 *
 * 要求：
 * 主函数不在Application实例中，否则报错误：
 * ——缺少 JavaFX 运行时组件, 需要使用该组件来运行此应用程序；
 * DemoApp必须是Application的子类；
 * DemoApp必须是公共类
 *
 * @author realpai <paiap@outlook.com>
 */
public class ApplicationApp extends ContentBox {

    public final static boolean SHOWING = false;
    public static final String TITLE = "Application - Application";

    public static void main(String[] args) {
        Application.launch(DemoApplication.class, args);
    }

    @Override
    public void index() {
        ol("这是Application的引导程序！");
    }

}
