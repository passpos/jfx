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
package jfx.scene.control;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class SliderApp extends ContentBox {
    
    public static final boolean SHOWING = false;
    public static final String TITLE = "Control - Slider 滑动条";
    private Slider s;
    private int t;
    
    @Override
    public void index() {
        baseDemo();
        
        eventDemo();
        
        textConvert();
        
        listenerDemo();
        
        changeDemo();
        
        dynamicChangeDemo5();
    }

    /**
     * new Slider(0, 100, 50)
     *
     * 参数1 最小值，一般从0开始；
     * 参数2 最大值，一般为100，也可以是其他数值；
     * 参数3 当前值，代表滑块的当前位置；
     *
     * 参数1和2并不决定Slider的长度。
     */
    public void baseDemo() {
        Slider s1 = new Slider();
        setRightAnchor(s1, 20.0);
        // 设置方向
        s1.setOrientation(Orientation.VERTICAL);

        // 参数： min，max，cunrrent（max为null时滑块消失）
        s = new Slider(0, 100, 50);
        s.setPrefWidth(400);
        s.setPrefHeight(60);

        // 显示刻度
        s.setShowTickMarks(true);

        // 显示刻度标签文字
        s.setShowTickLabels(true);

        // 安组平均分配尺寸，每组的尺寸为下面的数值
        s.setMajorTickUnit(20);

        // 设置滑块的当前位置（当前值）
        s.setValue(30);
        
        this.getChildren().addAll(s1, s);
    }

    /**
     * 单击事件
     */
    public void eventDemo() {
        s.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ol(t);
            }
        });
    }

    /**
     * 标签文本转换
     */
    public void textConvert() {
        s.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double t) {
                return t + "天";
            }
            
            @Override
            public Double fromString(String string) {
                ol("没有文本输入，不起作用");
                return null;
            }
            
        });
    }

    /**
     * 事件监听
     */
    public void listenerDemo() {
        s.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                ol(t1);
            }
        });
    }
    
    public void changeDemo() {
        s.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                ol(t1);
            }
        });
    }

    /**
     * 动态改变进度1
     */
    public void dynamicChangeDemo1() {
        s.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                new Thread(new Runnable() {
                    @Override
                    public synchronized void run() {
                        s.setValue(0);
                        for (int i = 0; i < 100; i++) {
                            s.setValue(i);
                            try {
                                this.wait(100);
                            } catch (InterruptedException ex) {
                                ol(ex.getMessage());
                            }
                        }
                    }
                }).start();
            }
        });
    }

    /**
     * 动态改变进度2
     */
    public void dynamicChangeDemo2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                s.setValue(0);
                for (int i = 0; i < 100; i++) {
                    s.setValue(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ol(ex.getMessage());
                    }
                }
            }
        }).start();
    }

    /**
     * 动态改变进度3
     */
    public void dynamicChangeDemo3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                s.setValue(0);
                ol("外部：" + Thread.currentThread().getName());
                for (int i = 0; i < 100; i++) {
                    t = i;

                    /*
                     * 使用此种写法，任务是在“JavaFX Application Thread”线程中；
                     * 由于是共享线程，可以用来更新组件。但当任务很多时，有些任务就
                     * 会卡顿。
                     *
                     * 而上面的两种写法，任务运行在其他的线程中，不能用来更新组件。
                     *
                     * 所有JavaFX代码的默认线程是：JavaFX Application Thread
                     */
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ol("内部：" + Thread.currentThread().getName());
                            s.setValue(t);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ol(ex.getMessage());
                    }
                }
            }
        }).start();
    }

    /**
     * 动态改变进度4
     */
    public void dynamicChangeDemo4() {
        s.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Schedule sc = new Schedule(s);

                // 延迟运行时间（马上运行）
                sc.setDelay(Duration.millis(0));

                // 运行间隔（每50毫秒运行一次）
                sc.setPeriod(Duration.millis(50));
                
                sc.start();
            }
        });
    }

    /**
     * 动态改变进度5
     */
    public void dynamicChangeDemo5() {
        s.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Schedule sc = new Schedule(s);

                // 延迟运行时间（马上运行）
                sc.setDelay(Duration.millis(0));

                // 运行间隔（每50毫秒运行一次）
                sc.setPeriod(Duration.millis(50));
                
                sc.start();

                /*
                 * 要求updateValue()执行下面这条语句：
                 * super.updateValue(value);
                 */
                sc.valueProperty().addListener(new ChangeListener<Integer>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Integer> ov,
                            Integer t,
                            Integer t1
                    ) {
                        ol("所有t：" + t);
                        ol("所有t1：" + t1);
                        ol("----------");
                        if (t1 != null) {
                            ol("t1 != null时，t：" + (t));
                            ol("t1 != null时，t1 + 10：" + (t1 + 10));
                            ol("----------------------");
                            s.setValue(t1);
                        }
                    }
                });
            }
        });
    }
    
}

class Schedule extends ScheduledService<Integer> {
    
    public int i = 0;
    public Slider s;
    
    public Schedule(Slider s) {
        this.s = s;
    }
    
    @Override
    protected Task<Integer> createTask() {
        Task<Integer> task = new Task<>() {
            @Override
            protected Integer call() throws Exception {
                i += 1;
                if (i > 100) {
                    this.cancel();
                }
                return i;
            }

            /**
             * 上面计算的数值会被传递到这里作为参数
             *
             * 这里的线程仍然是：JavaFX Application Thread
             */
            @Override
            protected void updateValue(Integer value) {
                super.updateValue(value);

                // 在这里运行任务，上面的语句可以省略；
                s.setValue(value);
            }
            
        };
        return task;
    }
    
}
