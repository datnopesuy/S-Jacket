/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.util;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class MsgBox {
     public static void alert(Component parent, String message) {
        ImageIcon icon = new ImageIcon("src\\com\\raven\\icon\\check.png");
        JOptionPane.showMessageDialog(parent, message, "ITSHOP", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static void warring(Component parent, String message) {
        ImageIcon icon = new ImageIcon("src\\com\\raven\\icon\\remove.png");
        JOptionPane.showMessageDialog(parent, message, "ITSHOP", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirm(Component parent, String message) {
        ImageIcon icon = new ImageIcon("src\\com\\raven\\icon\\question.png");
        int result = JOptionPane.showConfirmDialog(parent, message, "ITSHOP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "ITSHOP", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void labelAlert(JLabel lbl, JTextField txtField, String message) {
        lbl.setText(message);
    }

    public static void labelAlertTextArea(JLabel lbl, JTextArea txtString, String mess) {
        lbl.setText(mess);
    }
    
}
