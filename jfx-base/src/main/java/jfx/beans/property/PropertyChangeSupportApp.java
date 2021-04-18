package jfx.beans.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import jfx.core.app.ContentBox;
import utils.entity.demo.sample.Student;

/**
 * B51 PropertyChangeSupport
 *
 * Module java.desktop
 * Package java.beans
 *
 * java.lang.Object
 * |—java.beans.PropertyChangeSupport
 *
 * 本方法由JDK内部实现，使用时先将一个 PropertyChangeSupport 实例注入实体类；
 * - 当修改实体属性时，通过firePropertyChange("propertyName", oldValue, newValue)
 * 方法通知 PropertyChangeSupport；
 * - 当要监听属性变化时，直接在PropertyChangeSupport上添加监听器，从而取得新值与
 * 旧值；
 *
 * 该类在 java.desktop 模块中，由于java.desktop模块大小近12MB，用起来也不太方便，
 * 所以推荐使用JavaFX的javafx.base模块提供的针对各种数据类型的Property实现，该模
 * 块大小仅700多KB；
 *
 * @author realpai <paiap@outlook.com>
 */
public class PropertyChangeSupportApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Property - ChangeSupport";

    @Override
    public void index() {
        setConsole();
        baseDemo();
    }

    public void baseDemo() {
        Stu stu = new Stu("任我行", 11, 90);
        stu.pcs.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ob("oldValue：" + evt.getOldValue());
                ob("newValue：" + evt.getNewValue());
                ol(evt.getSource());
            }
        });
        stu.pcs.addPropertyChangeListener("setAge_pro", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ob("oldValue：" + evt.getOldValue());
                ob("newValue：" + evt.getNewValue());
            }
        });

        Button btn = new Button("点击改变");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stu.setAge(100);
            }
        });
        this.getChildren().add(btn);
    }

}

class Stu extends Student {

    public PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    Stu(String name, int age, double score) {
        super(name, age, score);
    }

    @Override
    public void setScore(double score) {
        double oldValue = this.getScore();
        super.setScore(score);

        // 调用下面的方法会添加一个事件（通知）；
        // 而 pcs 可以设置一个监听器，见 baseDemo() 案例；
        pcs.firePropertyChange("setScore_pro", oldValue, this.getScore());
    }

    @Override
    public void setAge(int age) {
        int oldValue = this.getAge();
        super.setAge(age);
        pcs.firePropertyChange("setAge_pro", oldValue, this.getAge());
    }

    @Override
    public void setName(String name) {
        String oldValue = this.getName();
        super.setName(name);
        pcs.firePropertyChange("setName_pro", oldValue, this.getName());
    }

}
