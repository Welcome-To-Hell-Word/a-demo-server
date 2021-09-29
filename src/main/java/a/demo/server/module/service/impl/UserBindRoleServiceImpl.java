package a.demo.server.module.service.impl;

import a.demo.server.module.entity.UserBindRole;
import a.demo.server.module.mapper.UserBindRoleMapper;
import a.demo.server.module.service.IUserBindRoleService;
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
public class UserBindRoleServiceImpl extends ServiceImpl<UserBindRoleMapper, UserBindRole> implements IUserBindRoleService {

}
