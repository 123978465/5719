package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 排故流程
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-15
 */
@TableName("flowpath")
public class Flowpath implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 保障资源
     */
    private String supresources;

    /**
     * 故障现象id
     */
    private String pid;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public String getSupresources() {
        return supresources;
    }

    public void setSupresources(String supresources) {
        this.supresources = supresources;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Flowpath{" +
        ", id=" + id +
        ", name=" + name +
        ", supresources=" + supresources +
        ", pid=" + pid +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        "}";
    }
}
