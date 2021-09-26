package a.demo.server.module.service.impl;

import a.demo.server.module.entity.Article;
import a.demo.server.module.mapper.ArticleMapper;
import a.demo.server.module.service.IArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
