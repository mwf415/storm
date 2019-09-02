package com.youyicn.entity.cycle;


import com.youyicn.entity.RspInfo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author kaifa
 * @since 2018-11-27
 */
public class CycleScoreModel extends RspInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 评分模板Id
     */
    private Integer modelId;

    /**
     * 构建人Id
     */
    private Integer userId;

    /**
     * 模板名称
     */
    private String modelName;

    /**
     * 评分项
     */
    private String scoreItem;

    /**
     * 备注
     */
    private String remark;
    /**
     * 评分项名称列表
     */
    private String[] itemNames;
    /**
     * 评分项分数列表
     */
    private String[] itemScores;

    public String[] getItemNames() {
        return itemNames;
    }

    public void setItemNames(String[] itemNames) {
        this.itemNames = itemNames;
    }

    public String[] getItemScores() {
        return itemScores;
    }

    public void setItemScores(String[] itemScores) {
        this.itemScores = itemScores;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getScoreItem() {
        return scoreItem;
    }

    public void setScoreItem(String scoreItem) {
        this.scoreItem = scoreItem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CycleScoreModel{" +
                "modelId=" + modelId +
                ", userId=" + userId +
                ", modelName='" + modelName + '\'' +
                ", scoreItem='" + scoreItem + '\'' +
                ", remark='" + remark + '\'' +
                ", itemNames=" + Arrays.toString(itemNames) +
                ", itemScores=" + Arrays.toString(itemScores) +
                '}';
    }
}
