package com.ruoyi.web.service;

import com.ruoyi.web.domain.HelmetInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebService;
import java.io.IOException;

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
     * @param file
     * @return
     */
    String uploadHelmetImage(MultipartFile file) throws IOException;


    /**
     * 保存安全帽监测图片(file类型)
     * @param url
     * @param helmetInfo
     */
    void saveHelmetImage(String url, HelmetInfo helmetInfo);



}
