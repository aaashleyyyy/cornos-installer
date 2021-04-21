package me.zeroX150.CornosInstaller.gui;

import me.zeroX150.CornosInstaller.Config;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class InstallerScreen extends GuiBase {
    @Override
    public JPanel getContents() {
        JPanel panel = new JPanel(null);
        JLabel title = new JLabel("<html><body style=\"font-size:25px;\">Installing cornos</body></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(500 / 2 - 300 / 2, 2, 300, 40);
        JLabel location = new JLabel("<html><body style=\"text-align:center;\">Fabric installation folder</body></html>");
        location.setBounds(500 / 2 - 200 / 2, 45 + 40, 200, 20);
        location.setHorizontalAlignment(JLabel.CENTER);
        JTextField installLocation = new JTextField("Pick folder");
        installLocation.setHorizontalAlignment(0);
        installLocation.setBounds(500 / 2 - 300 / 2 - 150 / 2 - 2, 70 + 40, 300, 30);
        installLocation.setEditable(false);
        JProgressBar progress = new JProgressBar();
        progress.setVisible(false);
        progress.setBounds(500 / 2 - 400 / 2, 70 + 40 + 40 + 50, 400, 20);
        progress.setMinimum(0);
        progress.setMaximum(100);
        JButton install = new JButton("Install");
        install.setEnabled(false);
        install.setHorizontalAlignment(SwingConstants.CENTER);
        install.setBounds(500 / 2 - 200 / 2, 70 + 40 + 40, 200, 30);
        install.addActionListener(e -> {
            install.setEnabled(false);
            if (Config.installLoc == null) {
                JOptionPane.showMessageDialog(panel, "Please avoid tampering with system memory.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            progress.setVisible(true);
            File modsFolder = new File(Config.installLoc.getAbsolutePath() + "/mods");
            if (!modsFolder.exists()) {
                int result = JOptionPane.showConfirmDialog(panel, "Mods folder not found. Create one and proceed with installation?", "Warning", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    modsFolder.mkdir();
                } else return;
            }
            progress.setValue(10);
            File cornosJar = new File(modsFolder.getAbsolutePath() + "/cornos.jar");
            File fapiJar = new File(modsFolder.getAbsolutePath() + "/fabricApi.jar");
            String cornos = "https://github.com/AriliusClient/Cornos/raw/master/builds/latest.jar";
            String fabricapi = "https://media.forgecdn.net/files/3276/155/fabric-api-0.32.9%2B1.16.jar";
            progress.setValue(20);
            try {
                new Thread(() -> {
                    try {
                        FileUtils.copyURLToFile(new URL(cornos), cornosJar);
                        SwingUtilities.invokeLater(() -> progress.setValue(60));
                        FileUtils.copyURLToFile(new URL(fabricapi), fapiJar);
                        SwingUtilities.invokeLater(() -> progress.setValue(100));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }).start();
            } finally {
                progress.setValue(100);
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    SwingUtilities.invokeLater(() -> {
                        progress.setValue(0);
                        progress.setVisible(false);
                        JOptionPane.showMessageDialog(panel, "Successfully installed the 2 mods. Run your fabric installation to use the client", "Success", JOptionPane.INFORMATION_MESSAGE);
                    });
                }).start();
            }
        });
        JButton pick = new JButton("Pick location");
        pick.setBounds(500 / 2 + 150 / 2 + 2, 70 + 40, 150, 30);
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
        panel.add(progress);
        return panel;
    }
}
