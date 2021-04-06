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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * Application的启动方式和生命周期；
 * 封装 ------------ 方法 --------- 所处线程
 * static void ----- launch() ----- main
 * void ------------ init() ------- JavaFX-Launcher
 * abstract void --- start() ------ JavaFX Application Thread
 * void ------------ stop() ------- JavaFX Application Thread
 *
 * @author realpai <paiap@outlook.com>
 */
public class ApplicationDemo extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        ol("Application中的start()方法！");
        stage.initStyle(StageStyle.UTILITY);
        // 设置窗口标题：
        stage.setTitle(Thread.currentThread().getName());

        // 设置窗口大小
        stage.setWidth(700);
        stage.setHeight(400);

        // 显示窗口;
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        ol("Hello World" + Thread.currentThread().getName());
        super.stop();
    }

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }
}
