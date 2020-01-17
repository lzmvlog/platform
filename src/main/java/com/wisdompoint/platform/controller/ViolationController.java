package com.wisdompoint.platform.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.wisdompoint.platform.model.Violation;
import com.wisdompoint.platform.model.ViolationLog;
import com.wisdompoint.platform.model.dto.ViolationDto;
import com.wisdompoint.platform.service.impl.EventServiceImpl;
import com.wisdompoint.platform.service.impl.ViolationLogServiceImpl;
import com.wisdompoint.platform.service.impl.ViolationServiceImpl;
import com.wisdompoint.platform.util.date.FileUtil;
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

import javax.validation.constraints.NotNull;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

    /**
     * 处理违规记录
     */
    @Autowired
    private ViolationLogServiceImpl violationLogService;

    /**
     * 配置图片保存的位置
     * 可配置修改
     */
    @Value("${filePath}")
    String path;

    /**
     * ai 接收接口
     * TODO： AI 少推送了 摄像头的 ip
     *
     * @return
     */
    @ApiOperation(value = "接收 ai 传递来的数据")
    @PostMapping(value = "insertViolation")
    public String insertViolation(@RequestBody String data) {
        // 解析 JSON 对象  alarmContext
        JSONObject jsonObject = JSONObject.parseObject(data);
        String image = jsonObject.get("remark").toString();
        // 处理图片保存
        String imgFilePath = baseImg(image);
        // 设置违规记录参数
        Violation violation = new Violation()
                .setId(IdUtil.fastSimpleUUID())
                .setCode(jsonObject.get("alarmContent").toString())
                // ai 推送的时间 提取的事摄像头的时间 不是很准确
//                .setCreateTime(Timestamp.valueOf(jsonObject.get("alarmTime").toString()))
//                 暂时先用自定义的时间 TODO：后面需要改成 ai 推送的时间
                .setCreateTime(DateUtil.date())
//                这里
                .setImage(imgFilePath)
                .setMemberId(jsonObject.get("memberId").toString())
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
        log.info("{ 查询违规信息 第 {} 页 }", pageNum + 1);
        return violationService.findAllByStatus(pageable);
    }

    /**
     * 审核违规记录
     *
     * @param id      违规的 ID 编号
     * @param process 确认违规信息
     *                如果 process 为 1 则 确认该条信息
     *                否则该条信息有误 删除该条信息
     * @return DataResult：数据返回请求参数
     * TODO：这里应该从登陆的 session 中获取该操作作人信息
     */
    @ApiOperation(value = "审核违规记录", notes = "根据违规的 ID 编号审核违规记录")
    @ApiImplicitParam(name = "id", value = "违规的 ID 编号", dataType = "String", paramType = "query")
    @PostMapping("reviewViolationsInfo")
    public DataResult reviewViolationsInfo(@RequestParam String id, @RequestParam Integer process) {
        // 判断传入的参数是否空指针
        if (StrUtil.isBlank(id)) {
            return new DataResult(CodeEnum.REQUEST_REFUSE, "请输入全必填参数~");
        }
        log.info("{ 审核违规信息 }");
        // 这里删除当前这条信息
        if (process == 1) {
            // TODO: 这里删除当前这条信息
        }
        // 处理不当 缺少对正确信息的处理
        violationLogService.insertViolationLog(new ViolationLog()
                .setId(IdUtil.fastSimpleUUID())
                .setCreateTime(DateUtil.date())
                .setProcess("识别信息有误")
                .setReviewId(id)
//              .setProcessId()
                .setStatus(StatusEnum.NORMAL.getId()));
        // 执行处理违规
        violationService.processViolationsInfo(ProcessEnum.REVIEW.getId(), id);
        return new DataResult(CodeEnum.REQUEST_SUCCESS, "已审核");
    }

    /**
     * 处理违规记录
     *
     * @param id      违规的 ID 编号
     * @param process 处理方式
     * @return DataResult：数据返回请求参数
     * TODO：这里应该从登陆的 session 中获取该操作作人信息
     */
    @ApiOperation(value = "处理违规记录", notes = "根据违规的 ID 编号处理违规记录")
    @ApiImplicitParam(name = "id", value = "违规的 ID 编号", dataType = "String", paramType = "query")
    @PostMapping("processViolationsInfo")
    public DataResult processViolationsInfo(@RequestParam String id, @RequestParam String process) {
        // 判断传入的参数是否空指针
        if (StrUtil.isBlank(id)) {
            return new DataResult(CodeEnum.REQUEST_REFUSE, "请输入全必填参数~");
        }

        Integer processed = violationService.findViolationProcessStatus(id).getProcess();
        if (processed != 2) {
            return new DataResult(CodeEnum.REQUEST_ERROR, "未审核，请审核后再处理");
        }
        log.info("{ 处理违规信息 }");
        violationLogService.insertViolationLog(new ViolationLog()
                .setId(IdUtil.fastSimpleUUID())
                .setCreateTime(DateUtil.date())
                .setProcess(process)
//              .setProcessId()
                .setReviewId(id)
                .setStatus(StatusEnum.NORMAL.getId()));

        // 执行处理违规
        violationService.processViolationsInfo(ProcessEnum.PROCESSED.getId(), id);
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
     * @param image 读取到的图片 处理保存
     * @return 返回处理过后的 base64
     */
    public String baseImg(String image) {
        // 生成图片的路径
        String imgFilePath = null;
        //图像数据为空
        if (image != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                //Base64解码 这里调用了一个 截取字符串的方法 baseImg()
                // 将 ai 传递过来的数据 截取 生成图片
                byte[] b = decoder.decodeBuffer(image.substring(image.indexOf(",") + 1));
                for (int i = 0; i < b.length; ++i) {
                    //调整异常数据
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                // 生成图片的路径
                imgFilePath = path + FileUtil.getFileDate() + "\\" + FileUtil.getJpgDate() + ".jpeg";
                log.info(imgFilePath);
                // 输出 生成的文件 到指定的目录下
                @Cleanup OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
            } catch (Exception e) {
                log.info(" { 图片保存失败 } ");
            }
        }
        return imgFilePath;
    }

    /**
     * 删除违规记录
     *
     * @param id 违规记录的id
     */
    @ApiOperation(value = "根据违规记录 id 删除")
    @PostMapping(value = "deleteViolationLog")
    public DataResult deleteViolationLog(@RequestParam String id) {
        if (StrUtil.isBlank(id)) {
            return new DataResult(CodeEnum.REQUEST_ERROR, "违规id不能为空");
        }
        violationService.deleteViolationLog(id);
        return new DataResult(CodeEnum.REQUEST_SUCCESS, "已删除");
    }

    /**
     * 根据用户的信息 查询用户的违规信息
     *
     * @param memberId 用户的 id
     * @param pageNum  页码
     * @param pageSize 每页显示条数
     * @return
     */
    @ApiOperation(value = "根据用户的信息 查询用户的违规信息")
    @GetMapping(value = "searchMemberByMemberId")
    public Page<ViolationDto> searchMemberByMemberId(@RequestParam @NotNull(message = "用户id不能为空") String memberId,
                                                     @RequestParam Integer pageNum,
                                                     @RequestParam Integer pageSize) {
        // 注意： 这里的 pageNum 是从 0 开始的
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        return violationService.findAllByMemberId(memberId, pageable);
    }

}
