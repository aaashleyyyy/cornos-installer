package me.zeroX150.CornosInstaller.gui;

import jdk.nashorn.internal.scripts.JO;
import me.zeroX150.CornosInstaller.Config;

import javax.swing.*;
import java.io.File;

public class InstallerScreen extends GuiBase {
    @Override
    public JPanel getContents() {
        JPanel panel = new JPanel(null);
        JLabel title = new JLabel("<html><body style=\"font-size:25px;\">Installing cornos</body></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(500/2-300/2,2,300,40);
        JLabel location = new JLabel("<html><body style=\"text-align:center;\">Fabric installation folder</body></html>");
        location.setBounds(500/2-200/2,45+40,200,20);
        location.setHorizontalAlignment(JLabel.CENTER);
        JTextField installLocation = new JTextField("Pick folder");
        installLocation.setHorizontalAlignment(0);
        installLocation.setBounds(500/2-300/2-150/2-2,70+40,300,30);
        installLocation.setEditable(false);
        JButton install = new JButton("Install");
        install.setEnabled(false);
        install.setHorizontalAlignment(SwingConstants.CENTER);
        install.setBounds(500/2-200/2,70+40+40,200,30);
        install.addActionListener(e -> {
            if (Config.installLoc == null) {
                JOptionPane.showMessageDialog(panel,"Please avoid tampering with system memory.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            File modsFolder = new File(Config.installLoc.getAbsolutePath()+"/mods");
            if (!modsFolder.exists()) {
                int result = JOptionPane.showConfirmDialog(panel,"Mods folder not found. Create one and proceed with installation?","Warning",JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    // continue work from yesterday
                }
            }
        });
        JButton pick = new JButton("Pick location");
        pick.setBounds(500/2+150/2+2,70+40,150,30);
        pick.setHorizontalAlignment(JButton.CENTER);
        pick.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int opt = fileChooser.showOpenDialog(panel);
            if (opt == JFileChooser.APPROVE_OPTION) {
                File selected = fileChooser.getSelectedFile();
                installLocation.setText(selected.getAbsolutePath());
                Config.installLoc = selected;
                install.setEnabled(true);
            }
        });
        panel.add(title);
        panel.add(location);
        panel.add(installLocation);
        panel.add(pick);
        panel.add(install);
        return panel;
    }
}
