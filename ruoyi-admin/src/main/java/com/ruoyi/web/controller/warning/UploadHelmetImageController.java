package com.ruoyi.web.controller.warning;

import cn.hutool.core.date.DateTime;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.domain.HelmetInfo;
import com.ruoyi.web.service.IUploadHelmetImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 电科院安全帽监测控制层
 *
 * @author maj
 * @date created by 2022-06-30 14:35
 */
@CrossOrigin(origins = Constants.LOCALHOST_IP_ADDRESS, maxAge = 3600)
@RestController
@RequestMapping(value = "/warning/info")
public class UploadHelmetImageController extends BaseController {

    @Autowired
    private IUploadHelmetImageService uploadHelmetImageService;

    /**
     * 上传安全帽监测信息
     * @param file
     * @param request
     * @return JSON
     */
    @PostMapping(value = "/avatar")
    public AjaxResult UploadHelmetInfo(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        // 上传文件判空处理
        if (file.isEmpty() || file.getSize() == 0){
            return AjaxResult.error("上传文件为空!");
        }

        // 获取上传的图片文件链接
        String url = null;
        try {
            url = uploadHelmetImageService.uploadHelmetImage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 取得机器人上传图片参数
        HelmetInfo helmetInfo = new HelmetInfo();
        helmetInfo.setDeviceId(request.getParameter("deviceId"));
        helmetInfo.setDeviceName(request.getParameter("deviceName"));
        helmetInfo.setImageName(request.getParameter("imageName"));
        helmetInfo.setImageType(request.getParameter("imageType"));
        helmetInfo.setImageSize(Double.valueOf(request.getParameter("imageSize")));
        helmetInfo.setCreateTime(DateTime.of(request.getParameter("createTime"),"yyyy-MM-dd HH:mm:ss"));

        // 保存安全帽监测图片
        uploadHelmetImageService.saveHelmetImage(url,helmetInfo);
        return AjaxResult.success("告警信息上传成功!");
    }
}
