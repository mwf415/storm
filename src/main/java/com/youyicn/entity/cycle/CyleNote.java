package com.youyicn.entity.cycle;

import java.util.Date;

public class CyleNote {
    private Integer id;

    private Integer arrturnid;

    private String sicknum;

    private Date indate;

    private String sickdes;

    private String skillname;

    private Date starttime;

    private String sickdetail;

    private String actrole;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArrturnid() {
        return arrturnid;
    }

    public void setArrturnid(Integer arrturnid) {
        this.arrturnid = arrturnid;
    }

    public String getSicknum() {
        return sicknum;
    }

    public void setSicknum(String sicknum) {
        this.sicknum = sicknum;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public String getSickdes() {
        return sickdes;
    }

    public void setSickdes(String sickdes) {
        this.sickdes = sickdes;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getSickdetail() {
        return sickdetail;
    }

    public void setSickdetail(String sickdetail) {
        this.sickdetail = sickdetail;
    }

    public String getActrole() {
        return actrole;
    }

    public void setActrole(String actrole) {
        this.actrole = actrole;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}