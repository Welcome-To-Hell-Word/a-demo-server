package a.demo.server.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Master Spark
 * @since 2021-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleBindPermission implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "role_id", type = IdType.ID_WORKER)
    private String roleId;

    private String permissionId;


}
