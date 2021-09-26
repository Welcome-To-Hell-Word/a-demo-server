package a.demo.server.module.service;

import a.demo.server.module.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Master Spark
 * @since 2021-09-26
 */
public interface IUserService extends IService<User> {

    String SignIn(User requestBody);
}
