package a.demo.server.module.controller;


import a.demo.server.base.BaseController;
import a.demo.server.module.entity.UserBindRole;
import a.demo.server.module.service.impl.UserBindRoleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Master Spark
 * @since 2021-09-29
 */
@RestController
@RequestMapping("/module/userBindRole")
public class UserBindRoleController extends BaseController<UserBindRole, UserBindRoleServiceImpl> {

}

