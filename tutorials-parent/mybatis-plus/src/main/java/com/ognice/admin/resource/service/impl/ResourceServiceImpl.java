package com.ognice.admin.resource.service.impl;

import com.ognice.admin.resource.entity.Resource;
import com.ognice.admin.resource.mapper.ResourceMapper;
import com.ognice.admin.resource.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * api接口映射表 服务实现类
 * </p>
 *
 * @author dbfk
 * @since 2019-12-16
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
