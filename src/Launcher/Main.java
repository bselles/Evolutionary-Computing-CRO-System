package Launcher;


import Controller.Controller;
import View.PrincipalWindow;

import javax.swing.*;

public  class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrincipalWindow(new Controller());
            }
        });
    }
}
