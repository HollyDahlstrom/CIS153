/**
 * MainGUI.java
 * CIS 153 - Data Structures - DMACC
 * 4-17-26
 *
 * Program description:
 * Main driver class that starts the Cat Feeding Application GUI 
 * using SwingUtilities.
 *
 * @author Holly Dahlstrom
 * @version 1.0
 * @since 1.0
 *
 * OS: Windows 10
 * IDE: Eclipse 2024-12
 * Copyright: This is my own original work
 * based on specifications issued by our instructor
 * Academic Honesty: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or
 * unmodified, nor used generative AI as a final draft.
 * I have not given other fellow student(s) access to my program.
 */

package driver;

import javax.swing.SwingUtilities;

import view.FeedingGUI;

public class MainGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FeedingGUI gui = new FeedingGUI();
            gui.setVisible(true);
        });
    }
}