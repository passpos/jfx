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
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import jfx.core.app.ContentBox;

/**
 * 适合播放短音频（数秒、数十秒长度）
 * @author realpai <paiap@outlook.com>
 */
public class AudioClipApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Media - AudioClip";
    private AudioClip audio;

    @Override
    public void index() {
        baseDemo();
        controlDemo();
    }

    public void baseDemo() {
        URL audioURL = this.getClass().getResource("audio/平沙落雁-陈熙珵.mp3");
        audio = new AudioClip(audioURL.toExternalForm());
        // 立体声/混响
        audio.setPan(1);
        // 优先级（同时播放多个音频时的优先级）
        // audio.setPriority(55);
        // 播放速率
        // audio.setRate(0.125);
        // 回放次数
        audio.setCycleCount(2);
    }

    /**
     * AudioClip 不能动态控制音量
     */
    public void controlDemo() {
        Button b1 = new Button("播放");
        Button b2 = new Button("停止");
        Button b3 = new Button("左声道");
        Button b4 = new Button("平衡");
        Button b5 = new Button("右声道");
        Slider slider = new Slider();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, slider);
        hBox.setSpacing(10);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                audio.play();
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                audio.stop();
            }
        });
        /*
         * 剪辑的相对左和右音量级别。有效范围为-1.0到1.0，其中-1.0为左声道提供最
         * 大音量，而禁用右声道，0.0为两个声道提供最大音量，1.0为右声道提供最大
         * 音量，并禁用左声道。超出此范围的值在内部被钳制。
         */
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                audio.setBalance(-1);
            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                audio.setBalance(0);
            }
        });
        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                audio.setBalance(1);
            }
        });
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                audio.setVolume(t1.doubleValue() / 100);
            }
        });
        getChildren().add(hBox);
    }
}
