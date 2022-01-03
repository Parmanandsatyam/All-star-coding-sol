/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundingthebases;

/**
 *
 * @author prana_000
 */

import java.util.Scanner;
import java.io.*;

public class RoundingTheBases {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan; String[] inputData = new String[10]; String temp;
        
        try {
            scan = new Scanner(new File("inputData.txt"));
        } catch (Exception e) {
            System.out.println("No input");
            return;
        }
        
        for (int i = 0; i < 10; i++) {
            inputData[i] = scan.nextLine().replace(" ", "");
            try {
                temp = getACSLRound(inputData[i]);
            } catch (Exception e) { continue; }
            System.out.printf("%d.\t%s%n", i + 1, temp);
        }
    }
    
    private static String getACSLRound(String data) {
        String[] dataSplit = data.split(",");
        
        int base = Integer.valueOf(dataSplit[1]);
        int target = Integer.valueOf(dataSplit[2]);
        
        String strValue = new String(dataSplit[0]);
        int dec = strValue.indexOf(".") - 1;
        strValue = strValue.replace(".", "");
        
        int[] nums = new int[strValue.length()];
        
        for (int i = 0; i < strValue.length(); i++) {
            nums[i] = "0123456789ABCDEF".indexOf(
                    String.valueOf(strValue.charAt(i))
            );
        }
        
        if (new Double(nums[dec + target + 1]) >= new Double(base) / 2d)
            nums[dec + target]++;
        
        int temp;
        int prefix = -1;
        
        for (int i = dec + target; i >= 0; i--) {
            if (nums[i] >= base) {
                temp = nums[i] / base;
                nums[i] = nums[i] % base;
                if (i != 0)
                    nums[i - 1] += temp;
                else
                    prefix = temp;
            }
        }
        
        String toReturn = prefix == -1 ? "" : String.valueOf(prefix);
        
        for (int i = 0; i <= dec + target; i++) {
            toReturn += "0123456789ABCDEF".charAt(nums[i]);
            if (i == dec)
                toReturn += ".";
        }
        
        return toReturn;
    }
    
}
