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
package jfx.scene.media;

import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class MediaViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Media - MediaView";
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
        URL videoURL = this.getClass().getResource("F:/视频/剑来2.mp4");
        media = new Media(videoURL.toExternalForm());
        mp = new MediaPlayer(media);

        MediaView mv = new MediaView(mp);
        mv.fitWidthProperty().bind(this.prefWidthProperty());
        mv.setPreserveRatio(true);
        setTopAnchor(mv, 30.0);

        getChildren().add(mv);
    }

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
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, slider1, slider2);
        hBox.setAlignment(Pos.CENTER);
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
                double totalTime = mp.getTotalDuration().toSeconds();
                slider2.setMax(totalTime);
                slider2.setMin(0);
                slider2.setValue(0);
                ImageView iv = new ImageView((Image) media.getMetadata().get("image"));
                setTopAnchor(iv, 30.0);
                getChildren().add(iv);
            }
        });
    }

}
