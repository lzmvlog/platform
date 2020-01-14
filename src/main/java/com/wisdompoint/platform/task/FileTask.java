package com.wisdompoint.platform.task;

import com.wisdompoint.platform.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author： ShaoJie
 * @data： 2020年01月14日 11:12
 * @Description： 文件的定时处理
 */
@Component
@Slf4j
public class FileTask {

    /**
     * 配置图片保存的位置
     * 可配置修改
     */
    @Value("${filePath}")
    String path;

    /**
     * 创建保存图片的文件夹
     * 每天早上 23点执行
     *
     * @return
     */
    @Scheduled(cron = "0 0 23 * * ?")
//    @Scheduled(cron = "*/15 * * * * ?")
    public void lodeFile() {
        log.info(" { 执行创建文件夹的定时任务 } ");
        // 获取 当前时间 yyyyMMdd
        String result = DateUtil.getFileDate();
        File file = new File(path + "\\" + result);
        if (!file.exists() && !file.isDirectory()) log.info(" 创建保存违规图片的文件夹   ： {} ",file.getName()); file.mkdirs();
    }

}
