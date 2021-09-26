package a.demo.server.module.service.impl;

import a.demo.server.module.entity.ArticleComment;
import a.demo.server.module.mapper.ArticleCommentMapper;
import a.demo.server.module.service.IArticleCommentService;
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
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements IArticleCommentService {

}
