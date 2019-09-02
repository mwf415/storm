package com.youyicn.entity.chart;

public class TotalNum {

    private Integer id;

    private String week;

    private Long count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TotalNum{" +
                "id=" + id +
                ", week='" + week + '\'' +
                ", count=" + count +
                '}';
    }
}
