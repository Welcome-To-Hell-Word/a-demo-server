package a.demo.server.module.service.impl;

import a.demo.server.module.entity.User;
import a.demo.server.module.mapper.UserMapper;
import a.demo.server.module.service.IUserService;
import a.demo.server.tools.TheResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Master Spark
 * @since 2021-09-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public String SignIn(User requestBody) {
        String result;
        if ("admin".equals(requestBody.getUsername())&&"admin".equals(requestBody.getPassword())){
            result=new TheResult().successString();
        } else {
            result=new TheResult().failureString();
        }
        return result;
    }
}
