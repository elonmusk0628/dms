package com.ruoyi.web.controller.robot;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    /**
     *
     * @return
     */
    @PostMapping(value = "/avatar")
    public AjaxResult UploadDeviceInfo(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        // 上传文件判空处理
        if (file.isEmpty() || file.getSize() == 0){
            return AjaxResult.error("上传文件为空!");
        }


        return AjaxResult.success();


    }


}
