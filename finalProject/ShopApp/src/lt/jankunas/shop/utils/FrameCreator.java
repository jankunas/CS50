package lt.jankunas.shop.utils;

import java.awt.Dimension;

import javax.swing.JFrame;

public class FrameCreator {
    
    public static void createFrame(JFrame frame){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
