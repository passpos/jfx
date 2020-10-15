/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.media;

import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class MediaPlayerApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Media - MediaPlayer";
    private Media media;
    private MediaPlayer mp;
    private Slider slider1;
    private Slider slider2;
    private boolean press = false;

    @Override
    public void index() {
        baseDemo();
        controlDemo();
        eventDemo();
    }

    public void baseDemo() {
        URL audioURL = this.getClass().getResource("audio/天宫舞曲-许镜清.mp3");
        media = new Media(audioURL.toExternalForm());
        mp = new MediaPlayer(media);

        // 播放速率
        // audio.setRate(0.125);
        // 回放次数（会影响getTotalDuration()）
        // mp.setCycleCount(2);
        mp.setAutoPlay(true);
        // 从某个时间点开始播放
        // mp.seek(Duration.seconds(100));
        // 解除mp与media的关联（同一个mp可以同时播放多个media，包括多个相同的media）
        // mp.dispose();
    }

    /**
     * 播放结束，二次播放时，默认是从mp.setStartTime()设置的时间开始，而不是从0
     * 开始；
     */
    public void controlDemo() {
        Button b1 = new Button("播放");
        Button b2 = new Button("停止");
        Button b3 = new Button("暂停");
        Button b4 = new Button("平衡");
        Button b5 = new Button("静音");
        slider1 = new Slider();
        slider2 = new Slider();
        slider2.setPrefWidth(250);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, slider1, slider2);
        hBox.setSpacing(10);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mp.play();
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mp.stop();
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mp.pause();
            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mp.setBalance(0);
            }
        });
        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (mp.isMute()) {
                    mp.setMute(false);
                } else {
                    mp.setMute(true);
                }
            }
        });

        // 音量
        mp.setVolume(0.5);
        slider1.setValue(mp.getVolume() * 100);
        // mp.volumeProperty().bind(slider1.valueProperty());
        slider1.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                mp.setVolume(t1.doubleValue() / 100);
            }
        });

        // 自动设置进度
        mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> ov, Duration t, Duration t1) {
                if (!press) {
                    slider2.setValue(t1.toSeconds());
                }
            }
        });

        // 拖拽进度
        slider2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                press = true;
            }
        });
        // 设置拖拽进度
        slider2.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                mp.seek(Duration.seconds(slider2.getValue()));
                press = false;
            }
        });
        getChildren().add(hBox);
    }

    public void eventDemo() {
        mp.setOnReady(new Runnable() {
            @Override
            public void run() {
                ol(media.getDuration().toSeconds());
                ol(mp.getTotalDuration().toSeconds());
                ol("准备阶段");
                double totalTime = mp.getTotalDuration().toSeconds();
                ol(totalTime);
                slider2.setMax(totalTime);
                slider2.setMin(0);
                slider2.setValue(0);
            }
        });
        mp.setOnPlaying(new Runnable() {
            @Override
            public void run() {
                ol("播放状态");
            }
        });
        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                ol("播放结束");
            }
        });

        mp.setOnPaused(new Runnable() {
            @Override
            public void run() {
                ol("暂停状态");
            }
        });
        mp.setOnStopped(new Runnable() {
            @Override
            public void run() {
                ol("停止状态");
            }
        });
        mp.setOnRepeat(new Runnable() {
            @Override
            public void run() {
                ol("重播");
            }
        });
        mp.setOnStalled(new Runnable() {
            @Override
            public void run() {
                ol("停顿状态");
            }
        });
        mp.setOnHalted(new Runnable() {
            @Override
            public void run() {
                ol("休止状态");
            }
        });
        mp.setOnError(new Runnable() {
            @Override
            public void run() {
                ol("出错");
            }
        });
        mp.setOnMarker(new EventHandler<MediaMarkerEvent>() {
            @Override
            public void handle(MediaMarkerEvent t) {
            }
        });
    }

}
