package com.musiccrawl.util;

/**
 * Created by Administrator on 2017/10/19.
 */
public class XiamiDecodeUtil {

    public static String decode(String code) {

        int heigth = Integer.valueOf(code.substring(0, 1));
        String s3 = code.substring(1);
        int width = s3.length() / heigth;
        int remainder = s3.length() % heigth;
        String[] urlSeparate = new String[heigth];

        for (int i = 0; i < urlSeparate.length; i++) {
            if (remainder > 0) {
                urlSeparate[i] = s3.substring(0, width + 1);
                remainder--;
                s3 = s3.substring(width + 1);
            } else {
                urlSeparate[i] = s3.substring(0, width);
                s3 = s3.substring(width);
            }

        }
        String location = "";
        for (int i = 0; i < urlSeparate[0].length(); i++) {
            for (int j = 0; j < urlSeparate.length; j++) {
                if (urlSeparate[j].length() < urlSeparate[0].length() && i == urlSeparate[0].length() - 1) {
                    continue;
                } else {
                    location += urlSeparate[j].substring(i, i + 1);
                }
            }
        }
        return location;
    }


}
