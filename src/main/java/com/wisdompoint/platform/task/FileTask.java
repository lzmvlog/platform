package com.wisdompoint.platform.task;

import com.wisdompoint.platform.util.date.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author： ShaoJie
 * @data： 2020年01月14日 11:12
 * @Description： 文件的定时处理 按照一定的时间创建保存图片的文件夹
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
     * 注意这：里有一个问题 系统按道理来说 如果你给某个文件保存到 当前的目录下 有则保存 没有则创建相应的文件夹保存 这里测试不可行
     * 可能是哪里有问题 现采用定时创建保存图片的文件夹
     *
     * @return
     */
    @Scheduled(cron = "0 0 23 * * ?")
    // 测试时间 每 15s 执行一次
//    @Scheduled(cron = "*/15 * * * * ?")
    public void lodeFile() {
        log.info(" { 执行创建文件夹的定时任务 } ");
        // 获取 当前时间 yyyyMMdd
        String result = FileUtil.getFileDate();
        File file = new File(path + "\\" + result);
        if (!file.exists() && !file.isDirectory()) log.info(" 创建保存违规图片的文件夹   ： {} ", file.getName());
        file.mkdirs();
    }

}
