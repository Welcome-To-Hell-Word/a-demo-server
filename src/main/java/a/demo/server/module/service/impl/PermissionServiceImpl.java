package a.demo.server.module.service.impl;

import a.demo.server.module.entity.Permission;
import a.demo.server.module.mapper.PermissionMapper;
import a.demo.server.module.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Master Spark
 * @since 2021-09-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
