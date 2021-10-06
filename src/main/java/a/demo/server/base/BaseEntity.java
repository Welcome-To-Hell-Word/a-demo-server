package a.demo.server.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BaseEntity {
    @JSONField(serialize = false)
    @TableField(exist = false)
    private long current=1;
    @JSONField(serialize = false)
    @TableField(exist = false)
    private long size=10;
}
