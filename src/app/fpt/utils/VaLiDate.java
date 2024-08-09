/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.fpt.utils;

/**
 *
 * @author Admin
 */
public class VaLiDate {
    public static void CheckEmpty(String... string) {
        int cout = 0;
        for (int i = 0; i < string.length; i++) {
            if(string[i].trim().isEmpty()){
                cout++;
            }
        }
        if(cout!=0){
            throw new RuntimeException("Giá Trị Này Trống !");
        }
    }
    public static void CheckValueInput(String regex , String value) {
        if(!value.trim().matches(regex)){
            throw new RuntimeException("Giá Trị Này Không Hợp Lệ");
        }
    }
}
