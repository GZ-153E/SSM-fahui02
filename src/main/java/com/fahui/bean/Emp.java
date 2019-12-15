package com.fahui.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Emp {

//    @NotNull(message = "员工编号必须为4为数字且不能为空！")
//    @Max(9999)
//    @Min(1000)
    private Integer id;
    @Pattern(regexp="(^[a-zA-Z]{3,16}|[\\u4E00-\\u9FA5]{2,4}$)"
            ,message="员工姓名为3-16位字母或2-4为中文且不能为空！")
    private String name;
    @Pattern(regexp="(^[a-zA-Z]{2,30}|[\\u4E00-\\u9FA5]{2,20}$)"
            ,message="员工工作2-30位英文字母或2-20位中文!")
    private String job;
    @Max(9999)
    @Min(1000)
    private Integer mgr = 0;
    @NotNull(message = "员工工资不能为空！")
    @Min(0)
    private Float sal;
    @Min(0)
    private Float comm = 0f;
    @NotNull(message = "请选择部门！")
    private Integer deptno;
    private String hiredate;

    private Empss empss;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Float getSal() {
        return sal;
    }

    public void setSal(Float sal) {
        this.sal = sal;
    }

    public Float getComm() {
        return comm;
    }

    public void setComm(Float comm) {
        this.comm = comm;
    }

    public Empss getEmpss() {
        return empss;
    }

    public void setEmpss(Empss empss) {
        this.empss = empss;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                ", hiredate=" + hiredate +
                ", empss=" + empss +
                '}';
    }
}
