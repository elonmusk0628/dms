package com.ruoyi.web.service;

import com.ruoyi.web.domain.DeviceInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebService;
import java.io.IOException;

/**
 * 仪表监测图片 服务层
 *
 * @author maj
 * @date 2022-07-07
 */
@WebService
public interface IDeviceMonitorService {

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    String uploadDeviceImage(MultipartFile file) throws IOException;

    /**
     *
     * @param url
     * @param deviceInfo
     */
    void saveDeviceImage(String url, DeviceInfo deviceInfo);
}
