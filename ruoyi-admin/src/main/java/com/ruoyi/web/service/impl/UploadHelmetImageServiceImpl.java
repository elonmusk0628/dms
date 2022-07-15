package com.ruoyi.web.service.impl;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.web.domain.HelmetInfo;
import com.ruoyi.web.mapper.UploadHelmetImageMapper;
import com.ruoyi.web.service.IUploadHelmetImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 安全帽监测图片 服务层实现类
 *
 * @author maj
 * @date 2022-07-01
 */
@Service
public class UploadHelmetImageServiceImpl  implements IUploadHelmetImageService {

    @Autowired
    private UploadHelmetImageMapper uploadHelmetImageMapper;


    /**
     * 上传安全帽监测图片(file类型)
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String uploadHelmetImage(MultipartFile file) throws IOException {

        // 判断上传文件是否为空
        if (file.isEmpty() || file.getSize() == 0) {
            return "上传文件为空";
        }

        if (!file.isEmpty()) {
            String avatar = null;
            try {
                avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
                System.out.println("图片上传路径:" + RuoYiConfig.getAvatarPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", avatar);
            String url = Constants.LOCALHOST_IP_ADDRESS + String.valueOf(ajax.get("imgUrl"));
            // 更新缓存安全帽监测图片
            return url;
        }
        return String.valueOf(AjaxResult.error("上传图片异常，请联系管理员!"));
    }

    /**
     * 保存安全帽监测图片(file类型)
     * @param url
     * @param helmetInfo
     */
    @Override
    public void saveHelmetImage(String url, HelmetInfo helmetInfo) {

        HelmetInfo saveImageInfo = new HelmetInfo();
        saveImageInfo.setDeviceId(helmetInfo.getDeviceId());
        saveImageInfo.setDeviceName(helmetInfo.getDeviceName());
        saveImageInfo.setImageName(helmetInfo.getImageName());
        saveImageInfo.setImageType(helmetInfo.getImageType());
        saveImageInfo.setImageSize(helmetInfo.getImageSize());
        saveImageInfo.setImageUrl(url);
        saveImageInfo.setCreateTime(helmetInfo.getCreateTime());

        // 调用mapper接口保存安全帽监测图片
        uploadHelmetImageMapper.saveHelmetImage(saveImageInfo);
    }

    /**
     * 查询安全帽监测信息列表
     * @param helmetInfo 安全帽信息实体类
     * @return 安全帽监测信息列表
     */
    @Override
    public List<HelmetInfo> selectHelmetList(HelmetInfo helmetInfo) {
        return uploadHelmetImageMapper.selectHelmetList(helmetInfo);
    }
}
