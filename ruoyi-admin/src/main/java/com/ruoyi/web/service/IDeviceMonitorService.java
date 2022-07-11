package com.ruoyi.web.service;

import com.ruoyi.web.domain.DeviceInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebService;
import java.io.IOException;
import java.util.List;

/**
 * 仪表监测图片 服务层
 *
 * @author maj
 * @date 2022-07-07
 */
@WebService
public interface IDeviceMonitorService {

    /**
     * 上传仪表监测图片(file类型)
     * @param file 上传文件
     * @return
     * @throws IOException
     */
    String uploadDeviceImage(MultipartFile file) throws IOException;

    /**
     * 保存仪表监测图片(file类型)
     * @param url 图片链接
     * @param deviceInfo 设备信息
     */
    void saveDeviceImage(String url, DeviceInfo deviceInfo);

    /**
     * 查询仪表信息列表
     * @param deviceInfo 设备信息
     * @return 设备信息列表
     */
    List<DeviceInfo> selectDeviceList(DeviceInfo deviceInfo);
}
