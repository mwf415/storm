package com.youyicn.entity.cycle;

import com.youyicn.entity.RspInfo;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author kaifa
 * @since 2018-11-27
 */
public class CycleScore extends RspInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分数Id
     */
    private Integer scoreId;

    /**
     * 模板Id
     */
    private Integer modelId;

    /**
     * 评分人Id
     */
    private Integer ratersId;

    /**
     * 被评分人Id
     */
    private String targetId;

    /**
     * 分数详情
     */
    private String scoreDetail;

    /**
     * 总分数
     */
    private Double totalScore;

    /**
     * 所属科室Id
     */
    private Integer roomId;

    /**
     * 所属基地Id
     */
    private Integer baseId;

    /**
     * 备注
     */
    private String remark;


    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getRatersId() {
        return ratersId;
    }

    public void setRatersId(Integer ratersId) {
        this.ratersId = ratersId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getScoreDetail() {
        return scoreDetail;
    }

    public void setScoreDetail(String scoreDetail) {
        this.scoreDetail = scoreDetail;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CycleScore{" +
                "scoreId=" + scoreId +
                ", modelId=" + modelId +
                ", ratersId=" + ratersId +
                ", targetId='" + targetId + '\'' +
                ", scoreDetail='" + scoreDetail + '\'' +
                ", totalScore=" + totalScore +
                ", roomId=" + roomId +
                ", baseId=" + baseId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
