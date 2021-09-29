package a.demo.server.module.controller;


import a.demo.server.module.service.IArticleService;
import a.demo.server.tools.TheResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Master Spark
 * @since 2021-09-26
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/module/article")
public class ArticleController {
    private final IArticleService iArticleService;

    @RequestMapping(method = RequestMethod.GET,value = "GetById/{id}")
    public String GetById(@PathVariable("id")String id){
        String result=id;
        return new TheResult().successString(result);
    }
}

