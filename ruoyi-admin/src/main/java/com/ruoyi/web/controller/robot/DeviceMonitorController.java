package com.ruoyi.web.controller.robot;

import cn.hutool.core.date.DateTime;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.domain.DeviceInfo;
import com.ruoyi.web.service.IDeviceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        deviceInfo.setImageResult(request.getParameter("imageResult"));
        deviceInfo.setCreateTime(DateTime.of(request.getParameter("createTime"),"yyyy-MM-dd HH:mm:ss"));

        // 保存仪表监测图片
        deviceMonitorService.saveDeviceImage(url,deviceInfo);

        return AjaxResult.success("仪表监测图片上传成功!");
    }

    /**
     * 查询仪表信息列表
     * @param deviceInfo 设备信息实体类
     * @return 设备信息列表
     */
    @PreAuthorize("@ss.hasPermi('device:monitor:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceInfo deviceInfo) {
        startPage();
        List<DeviceInfo> list = deviceMonitorService.selectDeviceList(deviceInfo);
        return getDataTable(list);
    }

    /**
     * 导出仪表信息列表
     * @param response 前端请求响应
     * @param deviceInfo 设备信息实体类
     */
    @Log(title="仪表监测", businessType=BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('device:monitor:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceInfo deviceInfo){
        List<DeviceInfo> list = deviceMonitorService.selectDeviceList(deviceInfo);
        ExcelUtil<DeviceInfo> util = new ExcelUtil<>(DeviceInfo.class);
        util.exportExcel(response,list,"仪表监测数据");
    }




}
