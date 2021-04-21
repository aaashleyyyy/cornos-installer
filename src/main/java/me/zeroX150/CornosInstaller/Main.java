package me.zeroX150.CornosInstaller;

import com.github.weisj.darklaf.LafManager;
import me.zeroX150.CornosInstaller.gui.HomeScreen;

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
