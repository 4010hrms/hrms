package com.king.hrmsdev.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * creat By Salary 0n 2019/9/24
 */
public class Salary {
    private  int job_id;
    private BigDecimal finasalary;
    private Date payday;

    public Salary(int job_id, BigDecimal finasalary, Date payday) {
        this.job_id = job_id;
        this.finasalary = finasalary;
        this.payday = payday;
    }
    public Salary(int job_id, BigDecimal finasalary) {
        this.job_id = job_id;
        this.finasalary = finasalary;

    }
    public Salary() {

    }
    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public BigDecimal getFinasalary() {
        return finasalary;
    }

    public void setFinasalary(BigDecimal finasalary) {
        this.finasalary = finasalary;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }
}
