package com.ruoyi.web.mapper;

import com.ruoyi.web.domain.DeviceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 上传仪表监测图片 数据层
 *
 * @author maj
 * @date 2022-07-07
 */
@Repository
public interface DeviceMonitorMapper {

    /**
     * 保存仪表监测图片
     * @param deviceInfo 仪表监测告警图片实体类
     * @return int
     */
    int saveDeviceImage(DeviceInfo deviceInfo);

    /**
     * 查询仪表信息列表
     * @param deviceInfo 设备信息
     * @return 设备信息列表
     */
    List<DeviceInfo> selectDeviceList(DeviceInfo deviceInfo);
}
