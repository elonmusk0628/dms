package com.ruoyi.web.mapper;

import com.ruoyi.web.domain.HelmetInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 查询安全帽信息列表
     * @param helmetInfo 安全帽信息实体类
     * @return 安全帽监测信息列表
     */
    List<HelmetInfo> selectHelmetList(HelmetInfo helmetInfo);


}
