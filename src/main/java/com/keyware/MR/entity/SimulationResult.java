package com.keyware.MR.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "故障分析结果（提供给unity）")
@Data
public class SimulationResult implements Serializable {
    @ApiModelProperty(value = "输出结果 0为异常 1 为正常")
    private String outputResult;
    @ApiModelProperty(value = "对应故障")
    private String failure;
    @ApiModelProperty(value = "故障排除方法")
    private String failureExcludeMethod;
    @ApiModelProperty(value = "故障原因")
    private String  failureCause;
    @ApiModelProperty(value = "输出结果（描述）")
    private String outputParam;

    @Override
    public String toString() {
        return "SimulationResult{" +
                "outputResult='" + outputResult + '\'' +
                ", failure='" + failure + '\'' +
                ", failureExcludeMethod='" + failureExcludeMethod + '\'' +
                ", failureCause='" + failureCause + '\'' +
                ", outputParam='" + outputParam + '\'' +
                '}';
    }

    public String getOutputResult() {
        return outputResult;
    }

    public void setOutputResult(String outputResult) {
        this.outputResult = outputResult;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public String getFailureExcludeMethod() {
        return failureExcludeMethod;
    }

    public void setFailureExcludeMethod(String failureExcludeMethod) {
        this.failureExcludeMethod = failureExcludeMethod;
    }

    public String getFailureCause() {
        return failureCause;
    }

    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }

    public String getOutputParam() {
        return outputParam;
    }

    public void setOutputParam(String outputParam) {
        this.outputParam = outputParam;
    }
}
