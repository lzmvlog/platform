package com.wisdompoint.platform;

import cn.hutool.core.lang.Console;
import com.wisdompoint.platform.util.em.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author： ShaoJie
 * @data： 2019年12月19日 15:13
 * @Description： 测试类
 */
@SpringBootTest
public class PlatFormTest {

    /**
     * 文件工具类
     */
//    private FileUtil fileUtil;

    @Test
    void test() {/*
        DateTime time = new DateTime(new Date());
        Console.log(time);
        Console.log(DateUtil.date());


        LocalDateTime localDateTime = LocalDateTime.now();
        Console.log(localDateTime);

        String result = localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Console.log(result);*/
//        Console.log(FileUtil.getJpgDate());
        Console.log(StatusEnum.NORMAL);

//        System.out.println(fileUtil.lodeFile());

//        fileUtil.lodeFile();
      /*  File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        //文件名称
        String uploadFileName = UUID.randomUUID().toString() + "." + ext;
        File targetFile = new File(path, uploadFileName);
        BASE64Decoder decoder = new BASE64Decoder();
        try (OutputStream out = new FileOutputStream(targetFile)) {
            byte[] b = decoder.decodeBuffer(BASE64str);
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
            return path + "/" + uploadFileName + "." + ext;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
    }

}
