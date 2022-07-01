package com.ruoyi.web.mapper;

import com.ruoyi.web.domain.HelmetInfo;
import org.springframework.stereotype.Repository;

/**
 * 上传安全帽监测图片 数据层
 *
 * @author maj
 * @date 2022-07-01
 */
@Repository
public interface UploadHelmetImageMapper {

    /**
     * 保存安全帽监测图片
     * @param helmetInfo 安全帽监测告警图片实体类
     * @return int
     */
    int saveHelmetImage(HelmetInfo helmetInfo);


}
