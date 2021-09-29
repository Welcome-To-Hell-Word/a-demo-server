package a.demo.server.module.service.impl;

import a.demo.server.module.entity.Role;
import a.demo.server.module.mapper.RoleMapper;
import a.demo.server.module.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
