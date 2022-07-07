package com.ruoyi.web.mapper;

import com.ruoyi.web.domain.DeviceInfo;
import org.springframework.stereotype.Repository;

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
    int saveHelmetImage(DeviceInfo deviceInfo);




}
