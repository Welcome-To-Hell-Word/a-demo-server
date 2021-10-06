package a.demo.server.base;

import a.demo.server.tools.ThePage;
import a.demo.server.tools.TheResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;

public class BaseController<T extends BaseEntity,S extends ServiceImpl> {
    @Resource
    private S service;
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(@RequestBody T t){
        service.save(t);
        return new TheResult().successString(t);
    }
    @RequestMapping(value = "removeById/{id}",method = RequestMethod.POST)
    public String removeById(@PathVariable("id")String id){
        return new TheResult().successString(service.removeById(id));
    }
    @RequestMapping(value = "updateById",method = RequestMethod.POST)
    public String updateById(@RequestBody T t){
        return new TheResult().successString(service.updateById(t));
    }
    @RequestMapping(value = "page",method = RequestMethod.POST)
    public String page(@RequestBody T t){
        IPage<T>iPage=service.page(
                new Page(t.getCurrent(),t.getSize()),
                new QueryWrapper(t)
        );
        return new TheResult().successString(
                new HashMap<String,Object>(){{
                    put("TheResult",iPage.getRecords());
                    put("ThePage",new ThePage(iPage));
                }}
        );
    }
}
