
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell Inc
 */
public class Controller {
    View view = new View();
    JTextField txtRed = view.getTxtRed();
    JTextField txtGreen= view.getTxtGreen();
    JTextField txtBlue= view.getTxtBlue();
    JTextField txtScreen = view.getTxtScreen();
    int red,green,blue;
    JSlider jsRed = view.getJsRed();
    JSlider jsGreen = view.getJSGreen();
    JSlider jsBlue = view.getJsBlue();

    public Controller() {
    view.setVisible(true);
    view.setResizable(false);
    view.setLocationRelativeTo(null);
    addAcction();
    changeColor();
    changeColorBlue();// set text first
    changeColorGreen();
    changeColorRed();
    }
    public void changeColor(){
        txtScreen.setBackground(new Color(red, green, blue));
    }
    public void changeColorRed(){
        red = jsRed.getValue();
        txtRed.setText("R = "+red);
        changeColor();
    }
    public void changeColorGreen(){
        green = jsGreen.getValue();
        txtGreen.setText("G = "+green);
        changeColor();
    }
    public void changeColorBlue(){
        blue = jsBlue.getValue();
        txtBlue.setText("B = "+blue);
        changeColor();
    }
    public void addAcction(){// get change value
                jsRed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changeColorRed();
            }
        });
                jsGreen.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changeColorGreen();
            }
        });
                jsBlue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changeColorBlue();
            }
        });
    }
    public static void main(String[] args) {
        Controller c = new Controller();
    }
   
}
