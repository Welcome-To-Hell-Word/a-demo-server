package a.demo.server.base;

import a.demo.server.tools.ThePage;
import a.demo.server.tools.TheResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

public class BaseController<T,S extends ServiceImpl> {
    @Autowired
    private S service;
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Object save(@RequestBody T t){
        service.save(t);
        return new TheResult().success(t);
    }
    @RequestMapping(value = "removeById/{id}",method = RequestMethod.POST)
    public Object removeById(@PathVariable("id")String id){
        return new TheResult().success(service.removeById(id));
    }
    @RequestMapping(value = "updateById",method = RequestMethod.POST)
    public Object updateById(@RequestBody T t){
        return new TheResult().success(service.updateById(t));
    }
    @RequestMapping(value = "getById/{id}",method = RequestMethod.POST)
    public Object getById(@PathVariable("id")String id){
        return new TheResult().success(service.getById(id));
    }
    @RequestMapping(value = {"page","page/{current}","page/{current}/{size}"},method = RequestMethod.POST)
    public Object page(@RequestBody T t,@PathVariable(value = "current",required = false)Long current,@PathVariable(value = "size",required = false)Long size){
        current=current==null?1:current;
        size=size==null?10:size;
        IPage<T>iPage=service.page(
                new Page(current,size),
                new QueryWrapper(t)
        );
        return new TheResult().success(
                new HashMap<String,Object>(){{
                    put("TheResult",iPage.getRecords());
                    put("ThePage",new ThePage(iPage));
                }}
        );
    }
}
