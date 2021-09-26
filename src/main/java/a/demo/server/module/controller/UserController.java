package a.demo.server.module.controller;


import a.demo.server.module.entity.User;
import a.demo.server.module.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Master Spark
 * @since 2021-09-26
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/module/user")
public class UserController {
    private final IUserService iUserService;

    @RequestMapping(method = RequestMethod.POST,value = "sign-in")
    public @ResponseBody String SignIn(@RequestBody User requestBody){
        return iUserService.SignIn(requestBody);
    }
}

