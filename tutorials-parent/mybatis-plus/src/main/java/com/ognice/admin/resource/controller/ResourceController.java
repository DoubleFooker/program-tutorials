package com.ognice.admin.resource.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ognice.admin.common.vo.PageVO;
import com.ognice.admin.common.vo.Result;
import com.ognice.admin.resource.entity.Resource;
import com.ognice.admin.resource.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * api接口映射表 前端控制器
 * </p>
 *
 * @author dbfk
 * @since 2019-12-16
 */
@RestController
@RequestMapping("/resource/resource")
public class ResourceController {
    @Autowired
    IResourceService resourceService;

    @GetMapping
    public Result page() {
        Page<Resource> page = resourceService.page(new Page<>(1, 10));
        List<Resource> records = page.getRecords();
        return new Result().setData(new PageVO<>(records, page.getTotal()));
    }

}
