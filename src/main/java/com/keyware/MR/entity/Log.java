package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 日志表
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@TableName("log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名" )
    private String userName;

    /**
     * 操作描述
     */
    @ApiModelProperty(value = "操作描述" )
    private String operate;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间" )
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Log{" +
        ", id=" + id +
        ", userName=" + userName +
        ", operate=" + operate +
        ", createTime=" + createTime +
        "}";
    }
}
