package com.ruoyi.web.controller.warning;

import cn.hutool.core.date.DateTime;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.domain.HelmetInfo;
import com.ruoyi.web.service.IUploadHelmetImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 电科院安全帽监测控制层
 *
 * @author maj
 * @date created by 2022-06-30 14:35
 */
@CrossOrigin(origins = Constants.LOCALHOST_IP_ADDRESS, maxAge = 3600)
@RestController
@RequestMapping(value = "/robot/helmet")
public class UploadHelmetImageController extends BaseController {

    @Autowired
    private IUploadHelmetImageService uploadHelmetImageService;

    /**
     * 上传安全帽监测信息
     * @param file
     * @param request
     * @return JSON
     */
    @PostMapping(value = "/avatar")
    public AjaxResult UploadHelmetInfo(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        // 上传文件判空处理
        if (file.isEmpty() || file.getSize() == 0){
            return AjaxResult.error("上传文件为空!");
        }

        // 获取上传的图片文件链接
        String url = null;
        try {
            url = uploadHelmetImageService.uploadHelmetImage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 取得机器人上传图片参数
        HelmetInfo helmetInfo = new HelmetInfo();
        helmetInfo.setDeviceId(request.getParameter("deviceId"));
        helmetInfo.setDeviceName(request.getParameter("deviceName"));
        helmetInfo.setImageName(request.getParameter("imageName"));
        helmetInfo.setImageType(request.getParameter("imageType"));
        helmetInfo.setImageSize(Double.valueOf(request.getParameter("imageSize")));
        helmetInfo.setCreateTime(DateTime.of(request.getParameter("createTime"),"yyyy-MM-dd HH:mm:ss"));

        // 保存安全帽监测图片
        uploadHelmetImageService.saveHelmetImage(url,helmetInfo);
        return AjaxResult.success("告警信息上传成功!");
    }

    /**
     * 查询安全帽监测信息列表
     * @param helmetInfo 安全帽信息实体类
     * @return 安全帽监测信息列表
     */
    @PreAuthorize("@ss.hasPermi('robot:helmet:list')")
    @GetMapping("/list")
    public TableDataInfo list(HelmetInfo helmetInfo) {
        startPage();
        List<HelmetInfo> list = uploadHelmetImageService.selectHelmetList(helmetInfo);
        return getDataTable(list);
    }

    /**
     * 导出安全帽监测信息列表
     * @param response 前端请求响应
     * @param helmetInfo 安全帽信息实体类
     */
    @Log(title="安全帽监测", businessType=BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('robot:helmet:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HelmetInfo helmetInfo) {
        List<HelmetInfo> list = uploadHelmetImageService.selectHelmetList(helmetInfo);
        ExcelUtil<HelmetInfo> util = new ExcelUtil<>(HelmetInfo.class);
        util.exportExcel(response,list,"安全帽监测数据");
    }
}
