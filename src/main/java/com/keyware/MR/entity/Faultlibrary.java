package com.keyware.MR.entity;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author yaojz
 * @since 2023-03-08
 */
@ApiModel(description = "故障库")
@Data
@TableName("faultlibrary")
public class Faultlibrary implements Serializable {

    private static final long serialVersionUID = 1L;
    @ExcelIgnore
    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer ID;
    @ExcelIgnore
    @ApiModelProperty(value = "由自己添加")
    @TableField(value = "ISSELF")
    private Integer isSelf = 1;

    @ExcelIgnore
    @ApiModelProperty(value = "工序id")
    @TableField("PROCESSID")
    private Integer processId;


    @ExcelProperty("工序")
    @ApiModelProperty(value = "工序")
    @TableField(exist = false)
//    @TableField("PROCESS")
    private String process;
    @ExcelProperty("故障动画标识")
    @ApiModelProperty(value = "故障动画标识")
    @TableField("MODELANIMATION")
    private String modelAnimation;

    @ExcelProperty("工步调试数值")
    @ApiModelProperty(value = "工步调试数值")
    @TableField(exist = false)
    private String executionStepParam;

    @ExcelProperty("故障号")
    @ApiModelProperty(value = "故障号")
    @TableField("FAULTNUMBER")
    private String faultNumber;
//    @ApiModelProperty(value = "工步名")
//    @TableField("STEPNAME")
    @ExcelProperty("操作方式")
    @ApiModelProperty(value = "操作方式 1 为一步一输出 0 则相反 ")
    @TableField("OPERATION")
    private String operation;

    @ExcelIgnore
    @TableField(exist = false)
    private String stepName;

    @ExcelIgnore
    @TableField(exist = false)
    private String outputName;
    @ExcelProperty("工步")
    @ApiModelProperty(value = "工步")
    @TableField("EXECUTIONSTEP")
    private String executionStep;

    @ExcelIgnore
    @ApiModelProperty(value = "工步（为前端拼接使用）")
    @TableField("EXECUTIONSTEPFORBEFORE")
    private String executionStepForBefore;

    @ExcelProperty("工步参数单位")
    @ApiModelProperty(value = "工步参数单位")
    @TableField("EXECUTIONSTEPPARAMUNIT")
    private String executionStepParamUnit;

    @ExcelProperty("工步参数最小值")
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("EXECUTIONSTEPPARAMMIN")
    private String executionStepParamMin;
    @ExcelProperty("工步参数最大值")
    @ApiModelProperty(value = "工步参数最大值")
    @TableField("EXECUTIONSTEPPARAMMAX")
    private String executionStepParamMax;


    @ExcelProperty("输出值单位")
    @ApiModelProperty(value = "输出值单位")
    @TableField("OUTPUTUNIT")
    private String outputUnit;
    @ExcelProperty("输出")
    @ApiModelProperty(value = "输出（描述）")
    @TableField("OUTPUT")
    private String output;

    @ExcelIgnore
    @ApiModelProperty(value = "输出（为前端拼接）")
    @TableField("OUTPUTFORBEFORE")
    private String outputForBefore;
    @ExcelProperty("要求")
    @ApiModelProperty(value = "要求（正常表现）")
    @TableField("REQUIREMENT")
    private String requirement;

    @ExcelIgnore
    @ApiModelProperty(value = "输出数值")
    @TableField(exist = false)
    private String outputParam;
//    MAXOUTPUT
    @ExcelProperty("输出最大值")
    @ApiModelProperty(value = "输出最大值")
    @TableField("MAXOUTPUT")
    private String maxOutput;
    @ExcelProperty("输出最小值")
    @ApiModelProperty(value = "输出最小值")
    @TableField("MINOUTPUT")
    private String minOutput;
    @ExcelProperty("故障")
    @ApiModelProperty(value = "故障")
    @TableField("FAILURE")
    private String failure;
    @ExcelProperty("故障排除方法")
    @ApiModelProperty(value = "故障排除方法")
    @TableField("FAILUREEXCLUDEMETHOD")
    private String failureExcludeMethod;
    @ExcelProperty("故障原因")
    @ApiModelProperty(value = "故障原因")
    @TableField("FAILURECAUSE")
    private String  failureCause;

}
