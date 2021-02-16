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
package jfx.scene.control.progress;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ProgressIndicatorApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Progress - ProgressIndicator 进度指示器";
    private ProgressIndicator pi;

    @Override
    public void index() {
        baseDemo();
        unknownProgress();
        scheduleDemo();
    }

    public void baseDemo() {
        pi = new ProgressIndicator(0.25);

        // 下面设置尺寸的方法无效：
//        pi.setPrefWidth(200);
//        pi.setPrefHeight(200);
//        pi.setPrefSize(200, 200);
        pi.setMinSize(150, 150);
        pi.setProgress(0.5);
        this.getChildren().add(pi);
    }

    public void unknownProgress() {
        ProgressIndicator pi2 = new ProgressIndicator();
        pi2.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        pi2.setPrefWidth(20);
        pi2.setPrefHeight(20);
        setTopAnchor(pi2, 220.0);
        setLeftAnchor(pi2, 50.0);
        this.getChildren().add(pi2);
    }

    public void scheduleDemo() {
        ScheduledService<Double> ss = new ScheduledService<>() {
            protected double i = 0;

            @Override
            protected Task<Double> createTask() {
                Task<Double> task = new Task<>() {
                    @Override
                    protected Double call() throws Exception {
                        i += 0.01;
                        if (pi.getProgress() > 1 && i > 1) {
                            this.cancel();
                            ol(i);
                        }
                        return i;
                    }

                    @Override
                    protected void updateValue(Double value) {
                        super.updateValue(value);
                        pi.setProgress(value);
                    }
                };
                return task;
            }
        };

        ss.setDelay(Duration.ZERO);
        ss.setPeriod(Duration.millis(50));
        ss.start();
    }
}
