package me.aaashleyyyy.CornosInstaller;

import com.github.weisj.darklaf.LafManager;
import me.aaashleyyyy.CornosInstaller.gui.HomeScreen;

public class Main {
    public static MainGUI mainGUI;

    public static void main(String[] args) {
        /* THEME */
        LafManager.setTheme(new com.github.weisj.darklaf.theme.OneDarkTheme());
        LafManager.install();
        /* GUI */
        mainGUI = new MainGUI(new HomeScreen().getContents());

    }
}
