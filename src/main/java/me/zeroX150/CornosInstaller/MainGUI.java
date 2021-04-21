package me.zeroX150.CornosInstaller;

import com.github.weisj.darklaf.LafManager;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public JFrame lmao;
    public void setContent(JPanel newContent) {
        lmao.getContentPane().removeAll();
        for (Component component : newContent.getComponents()) {
            lmao.add(component);
        }
        lmao.getContentPane().revalidate();
        lmao.repaint();
    }
    public MainGUI(JPanel panel) {

        /* MAIN WINDOW */
        lmao = new JFrame("Cornos Installer");
        lmao.setResizable(false);
        lmao.setSize(500,600);
        lmao.setLocationRelativeTo(null);
        lmao.setLayout(null);
        lmao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lmao.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("conv.jpeg")));
        for (Component component : panel.getComponents()) {
            lmao.add(component);
        }
        /* SHOW */
        lmao.setVisible(true);
    }
}
