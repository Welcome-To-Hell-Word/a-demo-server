package a.demo.server.module.entity;

import java.io.Serializable;

import a.demo.server.base.BaseEntity;
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
public class RoleBindPermission extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    private String roleId;

    private String permissionId;


}
