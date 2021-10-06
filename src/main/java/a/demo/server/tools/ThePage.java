package a.demo.server.tools;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

@Data
public class ThePage {
    private long current;
    private long size;
    private long pages;
    private long total;
    public ThePage(){}
    public ThePage(long current,long size,long pages,long total){
        this.current=current;
        this.size=size;
        this.pages=pages;
        this.total=total;
    }
    public ThePage(IPage iPage){
        this.current=iPage.getCurrent();
        this.size=iPage.getSize();
        this.pages=iPage.getPages();
        this.total=iPage.getTotal();
    }
}
