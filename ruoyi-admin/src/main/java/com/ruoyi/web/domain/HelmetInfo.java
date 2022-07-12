package com.ruoyi.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 * 安全帽监测告警图片实体类 helmet_info
 *
 * @author maj
 * @date 2022-05-13
 */
@Getter
@Setter
@Data
@TableName(value = "helmet_info")
public class HelmetInfo {

    /** 图片ID(自增) */
    private Long id;

    /** 设备编号 */
    private String deviceId;

    /** 设备名称 */
    private String deviceName;

    /** 图片名称 */
    private String imageName;

    /** 图片类型 */
    private String imageType;

    /** 图片大小 */
    private Double imageSize;

    /** 图片链接 */
    private String imageUrl;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
