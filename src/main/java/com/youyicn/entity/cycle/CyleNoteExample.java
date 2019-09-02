package com.youyicn.entity.cycle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CyleNoteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CyleNoteExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andArrturnidIsNull() {
            addCriterion("arrTurnId is null");
            return (Criteria) this;
        }

        public Criteria andArrturnidIsNotNull() {
            addCriterion("arrTurnId is not null");
            return (Criteria) this;
        }

        public Criteria andArrturnidEqualTo(Integer value) {
            addCriterion("arrTurnId =", value, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andArrturnidNotEqualTo(Integer value) {
            addCriterion("arrTurnId <>", value, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andArrturnidGreaterThan(Integer value) {
            addCriterion("arrTurnId >", value, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andArrturnidGreaterThanOrEqualTo(Integer value) {
            addCriterion("arrTurnId >=", value, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andArrturnidLessThan(Integer value) {
            addCriterion("arrTurnId <", value, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andArrturnidLessThanOrEqualTo(Integer value) {
            addCriterion("arrTurnId <=", value, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andArrturnidIn(List<Integer> values) {
            addCriterion("arrTurnId in", values, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andArrturnidNotIn(List<Integer> values) {
            addCriterion("arrTurnId not in", values, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andArrturnidBetween(Integer value1, Integer value2) {
            addCriterion("arrTurnId between", value1, value2, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andArrturnidNotBetween(Integer value1, Integer value2) {
            addCriterion("arrTurnId not between", value1, value2, "arrturnid");
            return (Criteria) this;
        }

        public Criteria andSicknumIsNull() {
            addCriterion("sickNum is null");
            return (Criteria) this;
        }

        public Criteria andSicknumIsNotNull() {
            addCriterion("sickNum is not null");
            return (Criteria) this;
        }

        public Criteria andSicknumEqualTo(String value) {
            addCriterion("sickNum =", value, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumNotEqualTo(String value) {
            addCriterion("sickNum <>", value, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumGreaterThan(String value) {
            addCriterion("sickNum >", value, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumGreaterThanOrEqualTo(String value) {
            addCriterion("sickNum >=", value, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumLessThan(String value) {
            addCriterion("sickNum <", value, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumLessThanOrEqualTo(String value) {
            addCriterion("sickNum <=", value, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumLike(String value) {
            addCriterion("sickNum like", value, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumNotLike(String value) {
            addCriterion("sickNum not like", value, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumIn(List<String> values) {
            addCriterion("sickNum in", values, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumNotIn(List<String> values) {
            addCriterion("sickNum not in", values, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumBetween(String value1, String value2) {
            addCriterion("sickNum between", value1, value2, "sicknum");
            return (Criteria) this;
        }

        public Criteria andSicknumNotBetween(String value1, String value2) {
            addCriterion("sickNum not between", value1, value2, "sicknum");
            return (Criteria) this;
        }

        public Criteria andIndateIsNull() {
            addCriterion("inDate is null");
            return (Criteria) this;
        }

        public Criteria andIndateIsNotNull() {
            addCriterion("inDate is not null");
            return (Criteria) this;
        }

        public Criteria andIndateEqualTo(Date value) {
            addCriterion("inDate =", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotEqualTo(Date value) {
            addCriterion("inDate <>", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateGreaterThan(Date value) {
            addCriterion("inDate >", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateGreaterThanOrEqualTo(Date value) {
            addCriterion("inDate >=", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateLessThan(Date value) {
            addCriterion("inDate <", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateLessThanOrEqualTo(Date value) {
            addCriterion("inDate <=", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateIn(List<Date> values) {
            addCriterion("inDate in", values, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotIn(List<Date> values) {
            addCriterion("inDate not in", values, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateBetween(Date value1, Date value2) {
            addCriterion("inDate between", value1, value2, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotBetween(Date value1, Date value2) {
            addCriterion("inDate not between", value1, value2, "indate");
            return (Criteria) this;
        }

        public Criteria andSickdesIsNull() {
            addCriterion("sickDes is null");
            return (Criteria) this;
        }

        public Criteria andSickdesIsNotNull() {
            addCriterion("sickDes is not null");
            return (Criteria) this;
        }

        public Criteria andSickdesEqualTo(String value) {
            addCriterion("sickDes =", value, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesNotEqualTo(String value) {
            addCriterion("sickDes <>", value, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesGreaterThan(String value) {
            addCriterion("sickDes >", value, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesGreaterThanOrEqualTo(String value) {
            addCriterion("sickDes >=", value, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesLessThan(String value) {
            addCriterion("sickDes <", value, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesLessThanOrEqualTo(String value) {
            addCriterion("sickDes <=", value, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesLike(String value) {
            addCriterion("sickDes like", value, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesNotLike(String value) {
            addCriterion("sickDes not like", value, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesIn(List<String> values) {
            addCriterion("sickDes in", values, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesNotIn(List<String> values) {
            addCriterion("sickDes not in", values, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesBetween(String value1, String value2) {
            addCriterion("sickDes between", value1, value2, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSickdesNotBetween(String value1, String value2) {
            addCriterion("sickDes not between", value1, value2, "sickdes");
            return (Criteria) this;
        }

        public Criteria andSkillnameIsNull() {
            addCriterion("skillName is null");
            return (Criteria) this;
        }

        public Criteria andSkillnameIsNotNull() {
            addCriterion("skillName is not null");
            return (Criteria) this;
        }

        public Criteria andSkillnameEqualTo(String value) {
            addCriterion("skillName =", value, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameNotEqualTo(String value) {
            addCriterion("skillName <>", value, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameGreaterThan(String value) {
            addCriterion("skillName >", value, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameGreaterThanOrEqualTo(String value) {
            addCriterion("skillName >=", value, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameLessThan(String value) {
            addCriterion("skillName <", value, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameLessThanOrEqualTo(String value) {
            addCriterion("skillName <=", value, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameLike(String value) {
            addCriterion("skillName like", value, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameNotLike(String value) {
            addCriterion("skillName not like", value, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameIn(List<String> values) {
            addCriterion("skillName in", values, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameNotIn(List<String> values) {
            addCriterion("skillName not in", values, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameBetween(String value1, String value2) {
            addCriterion("skillName between", value1, value2, "skillname");
            return (Criteria) this;
        }

        public Criteria andSkillnameNotBetween(String value1, String value2) {
            addCriterion("skillName not between", value1, value2, "skillname");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("startTime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("startTime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterion("startTime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterion("startTime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterion("startTime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("startTime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterion("startTime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("startTime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterion("startTime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterion("startTime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterion("startTime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("startTime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andSickdetailIsNull() {
            addCriterion("sickDetail is null");
            return (Criteria) this;
        }

        public Criteria andSickdetailIsNotNull() {
            addCriterion("sickDetail is not null");
            return (Criteria) this;
        }

        public Criteria andSickdetailEqualTo(String value) {
            addCriterion("sickDetail =", value, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailNotEqualTo(String value) {
            addCriterion("sickDetail <>", value, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailGreaterThan(String value) {
            addCriterion("sickDetail >", value, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailGreaterThanOrEqualTo(String value) {
            addCriterion("sickDetail >=", value, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailLessThan(String value) {
            addCriterion("sickDetail <", value, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailLessThanOrEqualTo(String value) {
            addCriterion("sickDetail <=", value, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailLike(String value) {
            addCriterion("sickDetail like", value, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailNotLike(String value) {
            addCriterion("sickDetail not like", value, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailIn(List<String> values) {
            addCriterion("sickDetail in", values, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailNotIn(List<String> values) {
            addCriterion("sickDetail not in", values, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailBetween(String value1, String value2) {
            addCriterion("sickDetail between", value1, value2, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andSickdetailNotBetween(String value1, String value2) {
            addCriterion("sickDetail not between", value1, value2, "sickdetail");
            return (Criteria) this;
        }

        public Criteria andActroleIsNull() {
            addCriterion("actRole is null");
            return (Criteria) this;
        }

        public Criteria andActroleIsNotNull() {
            addCriterion("actRole is not null");
            return (Criteria) this;
        }

        public Criteria andActroleEqualTo(String value) {
            addCriterion("actRole =", value, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleNotEqualTo(String value) {
            addCriterion("actRole <>", value, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleGreaterThan(String value) {
            addCriterion("actRole >", value, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleGreaterThanOrEqualTo(String value) {
            addCriterion("actRole >=", value, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleLessThan(String value) {
            addCriterion("actRole <", value, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleLessThanOrEqualTo(String value) {
            addCriterion("actRole <=", value, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleLike(String value) {
            addCriterion("actRole like", value, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleNotLike(String value) {
            addCriterion("actRole not like", value, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleIn(List<String> values) {
            addCriterion("actRole in", values, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleNotIn(List<String> values) {
            addCriterion("actRole not in", values, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleBetween(String value1, String value2) {
            addCriterion("actRole between", value1, value2, "actrole");
            return (Criteria) this;
        }

        public Criteria andActroleNotBetween(String value1, String value2) {
            addCriterion("actRole not between", value1, value2, "actrole");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}