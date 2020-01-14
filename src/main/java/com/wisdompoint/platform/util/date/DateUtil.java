package com.wisdompoint.platform.util.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author： ShaoJie
 * @data： 2020年01月14日 11:56
 * @Description： 时间工具类
 */
public class DateUtil {

    /**
     * 获取年月日  yyyyMMdd
     * @return
     */
    public static String getFileDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * yyyyMMddHHmmss
     * @return
     */
    public static String getJpgDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

}
