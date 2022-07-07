package com.ruoyi.web.controller.robot;

import cn.hutool.core.date.DateTime;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.domain.DeviceInfo;
import com.ruoyi.web.service.IDeviceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 电科院仪表监测控制层
 *
 * @author maj
 * @date created by 2022-07-06 16:55
 */
@CrossOrigin(origins = Constants.LOCALHOST_IP_ADDRESS, maxAge = 3600)
@RestController
@RequestMapping(value = "/device/monitor")
public class DeviceMonitorController extends BaseController {

    @Autowired
    private IDeviceMonitorService deviceMonitorService;

    /**
     * 上传仪表监测信息
     * @param file
     * @param request
     * @return JSON
     */
    @PostMapping(value = "/avatar")
    public AjaxResult UploadDeviceInfo(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        // 上传文件判空处理
        if (file.isEmpty() || file.getSize() == 0){
            return AjaxResult.error("上传文件为空!");
        }

        String url = null;
        try {
            url = deviceMonitorService.uploadDeviceImage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 取得机器人上传图片参数
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId(request.getParameter("deviceId"));
        deviceInfo.setDeviceName(request.getParameter("deviceName"));
        deviceInfo.setDeviceType(request.getParameter("deviceType"));
        deviceInfo.setImageName(request.getParameter("imageName"));
        deviceInfo.setImageType(request.getParameter("imageType"));
        deviceInfo.setImageSize(Double.valueOf(request.getParameter("imageSize")));
        deviceInfo.setCreatTime(DateTime.of(request.getParameter("creatTime"),"yyyy-MM-dd HH:mm:ss"));

        // 保存仪表监测图片
        deviceMonitorService.saveDeviceImage(url,deviceInfo);

        return AjaxResult.success("仪表监测图片上传成功!");
    }


}
