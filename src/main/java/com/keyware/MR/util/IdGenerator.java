package com.keyware.MR.util;

import java.security.SecureRandom;
import java.util.UUID;
/*
 * Description: 生成id工具类
 * @Author: caizhihui
 * @Date: 2023/12/12 10:23
 */
public class IdGenerator {
    private static SecureRandom random = new SecureRandom();
    private static String[] BASE_CHARACTER = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public IdGenerator() {
    }

    public static String uuid32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String uuid36() {
        return UUID.randomUUID().toString();
    }

    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    public static String random12Str() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 12; ++i) {
            sb.append(BASE_CHARACTER[(int)(32.0D * Math.random())]);
        }

        return sb.toString();
    }

    public static String random10Str() {
        char[] ss = new char[10];

        for(int i = 0; i < 10; ++i) {
            int f = (int)(Math.random() * 3.0D);
            if (f == 0) {
                ss[i] = (char)((int)(65.0D + Math.random() * 26.0D));
            } else if (f == 1) {
                ss[i] = (char)((int)(97.0D + Math.random() * 26.0D));
            } else {
                ss[i] = (char)((int)(48.0D + Math.random() * 10.0D));
            }
        }

        String is = new String(ss);
        return is;
    }

    public static void main(String[] args) {
    }
}
