package game;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary("libgame_logic");
        JFrame mainFrame = getMainFrame();

        MyPanel gamePanel = new MyPanel();
        gamePanel.setBounds(0, 0, 1000, 800);
        mainFrame.add(gamePanel);
        // keyboard input
        mainFrame.addKeyListener(gamePanel);
        // receive input
        gamePanel.setFocusable(true);
    }

    static JFrame getMainFrame() {
        JFrame mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //  center the frame
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = 1000;
        int height = 800;
        Dimension screenSize = toolkit.getScreenSize();
        mainFrame.setBounds(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2, width, height);

        return mainFrame;
    }
}
