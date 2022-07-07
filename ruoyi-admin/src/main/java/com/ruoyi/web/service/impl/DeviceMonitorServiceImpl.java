package com.ruoyi.web.service.impl;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.web.domain.DeviceInfo;
import com.ruoyi.web.mapper.DeviceMonitorMapper;
import com.ruoyi.web.service.IDeviceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 仪表监测图片 服务层实现类
 *
 * @author maj
 * @date 2022-07-06
 */
@Service
public class DeviceMonitorServiceImpl implements IDeviceMonitorService{

    @Autowired
    private DeviceMonitorMapper deviceMonitorMapper;


    /**
     * 上传仪表监测图片(file类型)
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String uploadDeviceImage(MultipartFile file) throws IOException {

        // 判断上传文件是否为空
        if (file.isEmpty() || file.getSize() == 0) {
            return "上传文件为空";
        }

        if (!file.isEmpty()) {
            String avatar = null;
            try {
                avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
                System.out.println("图片上传路径:" + RuoYiConfig.getAvatarPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", avatar);
            String url = Constants.LOCALHOST_IP_ADDRESS + String.valueOf(ajax.get("imgUrl"));
            // 更新缓存安全帽监测图片
            return url;
        }
        return String.valueOf(AjaxResult.error("上传图片异常，请联系管理员!"));
    }

    /**
     * 保存仪表监测图片(file类型)
     * @param url
     * @param deviceInfo
     */
    @Override
    public void saveDeviceImage(String url, DeviceInfo deviceInfo) {

        DeviceInfo saveImageInfo = new DeviceInfo();
        saveImageInfo.setDeviceId(deviceInfo.getDeviceId());
        saveImageInfo.setDeviceName(deviceInfo.getDeviceName());
        saveImageInfo.setDeviceType(deviceInfo.getDeviceType());
        saveImageInfo.setImageName(deviceInfo.getImageName());
        saveImageInfo.setImageType(deviceInfo.getImageType());
        saveImageInfo.setImageSize(deviceInfo.getImageSize());
        saveImageInfo.setImageUrl(url);
        saveImageInfo.setCreatTime(deviceInfo.getCreatTime());

        // 调用mapper接口保存仪表监测图片
        deviceMonitorMapper.saveHelmetImage(saveImageInfo);
    }
}
