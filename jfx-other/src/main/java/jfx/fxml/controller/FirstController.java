/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.fxml.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author realpai <paiap@outlook.com>
 */
public class FirstController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button btn1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void action() {
        System.out.println("abc");
    }
}
