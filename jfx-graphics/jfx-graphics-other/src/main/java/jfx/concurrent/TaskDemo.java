/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.concurrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 * 泛型 Number 指任务结束返回的数据的类型；
 * @author realpai <paiap@outlook.com>
 */
public class TaskDemo extends Task<Number> {

    /**
     * step-1
     * @return
     * @throws Exception
     */
    @Override
    protected Number call() throws Exception {
//        ol("----------------------------------Task Demo- call()");
//        ol(Platform.isFxApplicationThread());
//        ol(Thread.currentThread().getName());
        double progress = copyFiles();
        return progress;
    }

    /**
     * step-2
     * 该方法运行在UI线程中，接收call()方法的返回值
     *
     * 注：不要删除方法内部对父类方法的调用；
     * @param value
     */
    @Override
    protected void updateValue(Number value) {
//        ol("----------------------------------Task Demo- updateValue()");
        super.updateValue(value);
//        ol(value.intValue());
//        ol(Platform.isFxApplicationThread());
//        ol(Thread.currentThread().getName());
    }

    @Override
    protected void updateTitle(String string) {
        super.updateTitle(string);
    }

    /**
     * 该方法运行在 非UI线程 中
     * @param string
     */
    @Override
    protected void updateMessage(String string) {
        super.updateMessage(string);
    }

    @Override
    protected void updateProgress(double workDone, double max) {
        super.updateProgress(workDone, max);
    }

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }

    public double copyFiles() {
        this.updateTitle("copyFiles");

        double max;
        double sum = 0;
        double progress = 0;

        try {
            FileOutputStream fos;
            try (FileInputStream fis = new FileInputStream(new File("D:/Down/21017.mp4"))) {
                max = fis.available();
                fos = new FileOutputStream(new File("D:/Down/210171.mp4"));
                byte[] bt = new byte[10000];

                // 每次读取到的字节长度
                int i;

                // 读取并写入
                while ((i = fis.read(bt, 0, bt.length)) != -1) {
                    /*
                     * 在循环中，取消的任务会被再次执行，这当然会出错，所以要判
                     * 断任务状态，如果任务已取消，就应该跳出循环；
                     */
                    if (this.isCancelled()) {
                        break;
                    }

                    fos.write(bt, 0, i);
                    sum += i;

                    this.updateProgress(sum, max);

                    progress = sum / max;
                    if (progress < 0.5) {
                        updateMessage("请耐心等待！");
                    } else if (progress < 0.8) {
                        updateMessage("马上就好！");
                    } else if (progress < 1.0) {
                        updateMessage("即将完成！");
                    } else if (progress >= 1.0) {
                        updateMessage("完成！");
                    }
                    Thread.sleep(10);
                }
            }
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaskDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(TaskDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return progress;
    }

}
