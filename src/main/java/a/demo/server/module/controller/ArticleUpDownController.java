package a.demo.server.module.controller;


import a.demo.server.base.BaseController;
import a.demo.server.module.entity.ArticleUpDown;
import a.demo.server.module.service.impl.ArticleUpDownServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Master Spark
 * @since 2021-09-26
 */
@RestController
@RequestMapping("/module/articleUpDown")
public class ArticleUpDownController extends BaseController<ArticleUpDown, ArticleUpDownServiceImpl> {

}

