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
package jfx.scene.image;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PixelReaderApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Image - PixelReader接口";
    private PixelReader pr;

    @Override
    public void index() {
        baseDemo();
        pixelDemo();
        pixelFormatDemo();
    }

    /**
     * 一个argb像素点占32位/4字节，RGB每种颜色、包括透明度，各占1个字节；
     * 如果没有alpha通道，位深就是24位；
     *
     * Color可以直接被打印成一个16进制数字，最后两位是不透明度；
     * Color类用于将颜色封装在默认的sRGB颜色空间中。
     *
     * 每个颜色都有一个隐式alpha值1.0或构造函数中提供的显式alpha值。
     * alpha值定义颜色的透明度，可以用0.0-1.0或0-255范围内的双精度值表示。
     * alpha值为1.0或255表示颜色完全不透明；
     * alpha值为0或0.0表示颜色完全透明。
     *
     * 当使用显式alpha构造颜色或获取颜色的颜色/alpha分量时，颜色分量永远不会被
     * alpha分量预乘。
     */
    public void baseDemo() {
        String path = "img/1.jpg";
        Image img = new Image(path, 200, 200, true, true);
        pr = img.getPixelReader();

        // 获取某个像素点的argb值；
        int argb = pr.getArgb(0, 0);
        int a = (argb >> 24) & 0xff;
        int r = (argb >> 16) & 0xff;
        int g = (argb >> 8) & 0xff;
        int b = argb & 0xff;
        ol("argb = " + argb);
        ol("alpha = " + a);
        ol("red = " + r);
        ol("green = " + g);
        ol("blue = " + b);

        Color color = pr.getColor(0, 0);
        ol("Color = " + color);

        ImageView iv = new ImageView(img);
        this.getChildren().add(iv);
    }

    /**
     * 按整数读取并遍历一定区域（这里是3*3像素）中的所有像素点的ARGB值；
     */
    public void pixelDemo() {
        WritablePixelFormat<IntBuffer> inpf = PixelFormat.getIntArgbPreInstance();
        int[] in = new int[3 * 3];
        pr.getPixels(0, 0, 3, 3, inpf, in, 0, 3);
        for (int i = 0; i < in.length; i++) {
            int b = in[i] & 0xff;
            int g = in[i] >> 8 & 0xff;
            int r = in[i] >> 16 & 0xff;
            int a = in[i] >> 24 & 0xff;
            ol("A = " + a + "R = " + r + "G = " + g + "B = " + b);
        }
    }

    /**
     * 按字节读取并遍历一定区域中的所有像素点的ARGB值；
     */
    public void pixelFormatDemo() {
        WritablePixelFormat<ByteBuffer> bypf = PixelFormat.getByteBgraPreInstance();
        byte[] by = new byte[3 * 3 * 4];
        pr.getPixels(0, 0, 3, 3, bypf, by, 0, 3 * 4);
        for (int i = 0; i < by.length; i += 4) {
            int b = by[i] & 0xff;
            int g = by[i + 1] & 0xff;
            int r = by[i + 2] & 0xff;
            int a = by[i + 3] & 0xff;
            ob("A = " + a + "R = " + r + "G = " + g + "B = " + b, 200);
        }
    }
}
