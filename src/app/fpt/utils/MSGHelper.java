/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.fpt.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MSGHelper {
    public static void alert(Component parentComponent , String message){
        JOptionPane.showMessageDialog(parentComponent, message, "Thông Báo Gấp ! "
                , JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean confirm(Component  parentComponent , String message) {
        int result = JOptionPane.showConfirmDialog(parentComponent, message, "Cần Sự Xác Nhận Của Đồng Chí !"
                , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    
    public static String promot(Component  parentComponent , String message) {
        return JOptionPane.showInputDialog(parentComponent, message, "Thứ Chúng Tôi Cần Là Thông Tin , Mời Bạn Nhập !", JOptionPane.INFORMATION_MESSAGE);
    }
}
