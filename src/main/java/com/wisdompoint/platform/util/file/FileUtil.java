//package com.wisdompoint.platform.util.file;
//
//import com.wisdompoint.platform.util.date.DateUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//
///**
// * @author： ShaoJie
// * @data： 2020年01月14日 9:43
// * @Description： 操作文件帮助类
// */
//@Component
//@Slf4j
//public class FileUtil {
//
//    /**
//     * 配置图片保存的位置
//     * 可配置修改
//     */
//    @Value("${filePath}")
//    String path;
//
//    /**
//     * 获取当前保存图片的文件夹
//     *
//     * @return
//     */
//    public void lodeFile() {
//        // 获取 当前时间 yyyyMMdd
//        String result = DateUtil.getFileDate();
//        File file = new File(path + "\\" + result + "");
////        log.info(file.getName());
//        log.info(file.getPath());
////        return file.getName();
//    }
//
//}
