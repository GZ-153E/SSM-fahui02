package com.fahui.bean;

public class Empss{
    private String hiredate;
    private Float sal;
    private Float comm;

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

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    @Override
    public String toString() {
        return "Empss{" +
                "hiredate='" + hiredate + '\'' +
                ", sal=" + sal +
                ", comm=" + comm +
                '}';
    }
}
