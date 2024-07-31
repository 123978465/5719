package com.keyware.MR.entity;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 字典表
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
@ExcelIgnoreUnannotated
@TableName("dictionary")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    /**
     * 字典名称
     */
    @ExcelProperty(value = "字典名称")
    private String name;

    /**
     * 字典项类型
     */
    @ExcelProperty(value = "字典项类型")
    private String type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "Dictionary{" +
        ", id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", describ=" + describ +
        ", createTime=" + createTime +
        "}";
    }
}
