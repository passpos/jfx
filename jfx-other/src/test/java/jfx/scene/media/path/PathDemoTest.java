/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.media.path;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import jdm.samples.fx.platform.FxApp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PathDemoTest {

    public PathDemoTest() {
        if (FxApp.getRoot() == null) {
            AnchorPane ap = new AnchorPane();
            FxApp.setRoot(ap);
        }
        Application.launch(FxApp.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    private void init(Parent root) {
        if (FxApp.getRoot() == null) {
            AnchorPane ap = new AnchorPane();
            FxApp.setRoot(ap);
        } else {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(PathDemoTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
            FxApp.setRoot(root);
            FxApp.getScene().setRoot(root);
        }
    }

    /**
     * Test of imagePath method, of class PathDemo.
     */
    @Test
    public void testImagePath() {
        System.out.println("imagePath");
        PathDemo instance = new PathDemo();
        instance.imagePath();
        init(instance);

    }

    /**
     * Test of filePath method, of class PathDemo.
     * @throws java.lang.Exception
     */
    @Test
    public void testFilePath() throws Exception {
        System.out.println("filePath");
        PathDemo instance = new PathDemo();
        instance.filePath();
        init(instance);
    }

    /**
     * Test of fileInputStreamPath method, of class PathDemo.
     * @throws java.lang.Exception
     */
    @Test
    public void testFileInputStreamPath() throws Exception {
        System.out.println("fileInputStreamPath");
        PathDemo instance = new PathDemo();
        instance.fileInputStreamPath();
        init(instance);

    }

    /**
     * Test of urlPath method, of class PathDemo.
     */
    @Test
    public void testUrlPath() {
        System.out.println("urlPath");
        PathDemo instance = new PathDemo();
        instance.urlPath();
        init(instance);

    }

}
