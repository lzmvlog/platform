package com.wisdompoint.platform.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.wisdompoint.platform.model.Violation;
import com.wisdompoint.platform.model.dto.ViolationDto;
import com.wisdompoint.platform.service.impl.EventServiceImpl;
import com.wisdompoint.platform.service.impl.ViolationServiceImpl;
import com.wisdompoint.platform.util.date.DateUtil;
import com.wisdompoint.platform.util.em.CodeEnum;
import com.wisdompoint.platform.util.em.ProcessEnum;
import com.wisdompoint.platform.util.em.StatusEnum;
import com.wisdompoint.platform.util.em.result.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 11:51
 * @Description： 违规服务 控制器
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("violation")
@Api(value = "违规controller", tags = "违规记录")
@Slf4j
public class ViolationController {

    /**
     * 违规服务
     */
    @Autowired
    private ViolationServiceImpl violationService;

    /**
     * 违规事件的服务
     */
    @Autowired
    private EventServiceImpl eventService;

//    /**
//     * 文件工具类
//     */
//    private FileUtil fileUtil;

    /**
     * 配置图片保存的位置
     * 可配置修改
     */
    @Value("${filePath}")
    String path;

    /**
     * ai 接收接口
     *
     * @return
     */
    @ApiOperation(value = "接收 ai 传递来的数据")
    @PostMapping(value = "insertViolation")
    public String insertViolation(@RequestBody String data) {
        // 解析 JSON 对象  alarmContext
        JSONObject jsonObject = JSONObject.parseObject(data);
        String image = jsonObject.get("remark").toString();
        // 生成图片的路径
        String imgFilePath = null;
        //图像数据为空
        if (image != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                //Base64解码
                byte[] b = decoder.decodeBuffer(baseImg(image));
                for (int i = 0; i < b.length; ++i) {
                    //调整异常数据
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                // 生成图片的路径
                imgFilePath = path + DateUtil.getFileDate() + "\\" + DateUtil.getJpgDate() + "jpeg";
                log.info(imgFilePath);
                // 输出 生成的文件 到指定的目录下
                @Cleanup OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
            } catch (Exception e) {
                log.info(" { 图片保存失败 } ");
            }
        }
        // 设置违规记录参数
        Violation violation = new Violation()
                .setId(IdUtil.simpleUUID())
                .setCode(jsonObject.get("alarmContent").toString())
                // ai 推送的时间 提取的事摄像头的时间 不是很准确
                .setCreateTime(Timestamp.valueOf(jsonObject.get("alarmTime").toString()))
//                 暂时先用自定义的时间 TODO：后面需要改成 ai 推送的时间
//                .setCreateTime(DateUtil.date())
                .setImage(imgFilePath)
                .setStatus(StatusEnum.NORMAL.getId())
                .setProcess(ProcessEnum.NORMAL.getId());

        log.info("{ 新增违规信息 检测到违规信息 }");
        violationService.insertViolation(violation);
        return "success";
    }

    /**
     * 查询指定的 违规信息
     *
     * @param pageNum  页数
     * @param pageSize 每页显示数据的数量
     * @return
     */
    @ApiOperation(value = "查询违规信息")
    @GetMapping("listViolations")
    public Page<ViolationDto> listViolations(@RequestParam Integer pageNum,
                                             @RequestParam Integer pageSize) {
        // 注意： 这里的 pageNum 是从 0 开始的
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        log.info("{ 查询违规信息 }");
        return violationService.findAllByStatus(pageable);
    }

    /**
     * 处理违规记录
     *
     * @param id 违规的 ID 编号
     * @return DataResult：数据返回请求参数
     */
    @ApiOperation(value = "处理违规记录", notes = "根据违规的 ID 编号处理违规记录")
    @ApiImplicitParam(name = "id", value = "违规的 ID 编号", dataType = "String", paramType = "query")
    @PostMapping("processViolationsInfo")
    public DataResult processViolationsInfo(@RequestParam String id) {
        // 判断传入的参数是否空指针
        if (StrUtil.isBlank(id)) {
            return new DataResult(CodeEnum.REQUEST_REFUSE, "请输入全必填参数~");
        }
        log.info("{ 处理违规信息 }");
        // 执行处理违规
        violationService.processViolationsInfo(id);
        return new DataResult(CodeEnum.REQUEST_SUCCESS, "已处理");
    }

    /**
     * 查询指定的违规图片
     *
     * @param id 违规 id
     * @return
     */
    @ApiOperation(value = "查询指定的违规图片")
    @GetMapping("getImg")
    public Map<String, String> getViolationImg(@RequestParam String id,
                                               @RequestParam String code) {
        log.info("{查询违规图片 图片id：{}}", id);
        // 查询出图片的路径
        String image = violationService.getViolationImg(id).getImage();
        //图像数据为空
        /*if (image != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                //Base64解码
                byte[] b = decoder.decodeBuffer(baseImg(image));
                for (int i = 0; i < b.length; ++i) {
                    //调整异常数据
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                // 生成图片的路径
                String imgFilePath = path + DateUtil.getFileDate() + "\\" + DateUtil.getJpgDate() + "jpeg";
                log.info(imgFilePath);
                // 输出 生成的文件 到指定的目录下
                @Cleanup OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
            } catch (Exception e) {
                log.info(" { 图片保存失败 } ");
            }
        }*/
        // 查询相应的违规内容
        log.info(" { 查询违规事项 违规代码 code：{} } ", code);
        String context = eventService.getContextByCode(code).getContext();
        // 将数据封装到 map 中
        Map<String, String> img = new HashMap<>();
        img.put("context", context);
        img.put("image", image);
        return img;
    }

    /**
     * 查询用户的 违规信息
     *
     * @param memberId 用户的 id
     * @return
     */
    @ApiOperation(value = "查询用户的违规记录", notes = "根据用户的 memberId 来查询违规记录")
    @GetMapping(value = "getViolationLog")
    public List<Violation> getViolationLog(@RequestBody String memberId) {
        log.info("{ 查询用户的违规信息 用户的id：{} }", memberId);
        return violationService.getViolationLog(memberId);
    }

    /**
     * 解析图片 base64 码 ai 推送的 图片码是 base64 拿到 base64 码 转换成 图片
     * 但是 需要去除 data:image/jpeg;base64, 也就是读取出来的 前缀部分
     *
     * @param base 数据库读取到的 base64
     * @return 返回处理过后的 base64
     */
    public String baseImg(String base) {
        String substring = base.substring(base.indexOf(",") + 1);
        System.out.println(substring);
        return substring;
    }

}
