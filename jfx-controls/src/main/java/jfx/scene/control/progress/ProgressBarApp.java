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
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ProgressBarApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Progress - ProgressBar 进度条";
    private ProgressBar pb;

    @Override
    public void index() {
        baseDemo();

        unknownProgress();

        setSchedule();
    }

    public void baseDemo() {
        pb = new ProgressBar(0.25d);
        pb.setProgress(0.5);
        pb.setPrefWidth(200);
        pb.setPrefHeight(20);
        this.getChildren().add(pb);
    }

    /**
     * 进度未知的进度条
     */
    public void unknownProgress() {
        ProgressBar pb2 = new ProgressBar();
        pb2.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        pb2.setPrefWidth(200);
        setTopAnchor(pb2, 30.0);
        this.getChildren().add(pb2);
    }

    /**
     * 设置动态进度
     */
    public void setSchedule() {

        ScheduledService<Double> ss = new ScheduledService<Double>() {

            protected double i = 0;

            @Override
            protected Task<Double> createTask() {
                Task<Double> task = new Task<Double>() {
                    @Override
                    protected Double call() throws Exception {
                        i += 0.01;
                        if (pb.getProgress() > 1 && i > 1) {
                            this.cancel();
                            ol(i);
                        }
                        return i;
                    }

                    @Override
                    protected void updateValue(Double value) {
                        pb.setProgress(value);
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
