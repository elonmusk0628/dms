package com.ruoyi.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 仪表监测图片实体类 device_info
 *
 * @author maj
 * @date 2022-07-06
 */
@Getter
@Setter
@Data
@TableName(value = "device_info")
public class DeviceInfo {

    /** 图片ID(自增) */
    private Long id;

    /** 设备编号 */
    private String deviceId;

    /** 设备名称 */
    private String deviceName;

    /** 设备类型 */
    private String deviceType;

    /** 图片名称 */
    private String imageName;

    /** 图片类型 */
    private String imageType;

    /** 图片大小 */
    private Double imageSize;

    /** 图片链接 */
    private String imageUrl;

    /** 发送时间 */
    private Date creatTime;
}
