package com.ruoyi.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
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
public class DeviceInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 图片ID(自增) */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号", width = 15)
    private String deviceId;

    /** 设备名称 */
    @Excel(name = "设备名称", width = 15)
    private String deviceName;

    /** 设备类型 */
    @Excel(name = "设备类型", width = 15)
    private String deviceType;

    /** 图片名称 */
    @Excel(name = "图片名称", width = 15)
    private String imageName;

    /** 图片类型 */
    @Excel(name = "图片类型", width = 15)
    private String imageType;

    /** 图片大小 */
    @Excel(name = "图片大小", width = 15, cellType = Excel.ColumnType.NUMERIC)
    private Double imageSize;

    /** 识别结果 */
    @Excel(name = "识别结果", width = 15)
    private String imageResult;

    /** 图片链接 */
    @Excel(name = "仪表图片", width = 17, cellType = Excel.ColumnType.IMAGE, height = 100)
    private String imageUrl;

    /** 发送时间 */
    @Excel(name = "发送时间", width = 17, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
