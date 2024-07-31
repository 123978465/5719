package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-13
 */
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称" )
    private String name;

    /**
     * 菜单url
     */
    @ApiModelProperty(value = "菜单url" )
    private String url;

    private String target;

    /**
     * 父级菜单id
     */
    @ApiModelProperty(value = "父菜单id" )
    private Integer pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Menu{" +
        ", id=" + id +
        ", name=" + name +
        ", url=" + url +
        ", target=" + target +
        ", pid=" + pid +
        "}";
    }
}
