package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 故障现象表
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-15
 */
@Data
@TableName("faultphenomenon")
public class Faultphenomenon implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 故障现象
     */
    private String faultPhenomenon;

    /**
     * 故障描述
     */
    private String describ;

    @ApiModelProperty(value = "分检人员")
    @TableField("SORTING_PERSONNEL")
    private String   sortingPersonnel;


    @ApiModelProperty(value = "故检人员")
    @TableField("INSPECTION_PERSONNEL")
    private String  inspectionPersonnel;
    @ApiModelProperty(value = "修理人员")
    @TableField("REPAIR_PERSONNEL")
    private String  repairPersonnel;

    @ApiModelProperty(value = "装配人员")
    @TableField("ASSEMBLY_PERSONNEL")
    private String  assemblypersonnel;


    @ApiModelProperty(value = "试验人员")
    @TableField("TEST_PERSONNEL")
    private String  test_Personnel;


    @ApiModelProperty(value = "故修检验人员")
    @TableField("REPAIR_INSPECTION_PERSONNEL")
    private String repairInspectionPersonnel;


    @ApiModelProperty(value = "分装检验人员")
    @TableField("PACKAGING_INSPECTION_PERSONNEL")
    private String  packagingInspectionPersonnel;


    @ApiModelProperty(value = "试验检验人员")
    @TableField("TEST_INSPECTION_PERSONNEL")
    private String  testInspectionPersonnel;


    @ApiModelProperty(value = "操作者")
    @TableField("OPERATORS")
    private String  operators;

    /**
     * 创建时间
     */
    private Date createTime;

}
