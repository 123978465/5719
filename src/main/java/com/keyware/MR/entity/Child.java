package com.keyware.MR.entity;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 子字典
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
@ExcelIgnoreUnannotated
@TableName("dictionary_child")
public class Child implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 父字典id
     */
    private String pid;

    /**
     * 字典项值
     */
    @ExcelProperty(value = "字典项值")
    private String name;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String describ;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Child{" +
        ", id=" + id +
        ", pid=" + pid +
        ", name=" + name +
        ", describ=" + describ +
        ", createTime=" + createTime +
        "}";
    }
}
