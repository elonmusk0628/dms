package com.ruoyi.web.service;

import com.ruoyi.web.domain.HelmetInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebService;
import java.io.IOException;
import java.util.List;

/**
 * 安全帽监测图片 服务层
 *
 * @author maj
 * @date 2022-07-01
 */
@WebService
public interface IUploadHelmetImageService {

    /**
     * 上传安全帽监测图片(file类型)
     * @param file 图片文件
     * @return 图片上传结果
     */
    String uploadHelmetImage(MultipartFile file) throws IOException;


    /**
     * 保存安全帽监测图片(file类型)
     * @param url 图片链接
     * @param helmetInfo 安全帽信息实体类
     */
    void saveHelmetImage(String url, HelmetInfo helmetInfo);


    /**
     * 查询安全帽信息列表
     * @param helmetInfo 安全帽信息实体类
     * @return 安全帽监测信息列表
     */
    List<HelmetInfo> selectHelmetList(HelmetInfo helmetInfo);
}
