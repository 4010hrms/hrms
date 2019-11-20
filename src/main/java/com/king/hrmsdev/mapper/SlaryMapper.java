package com.king.hrmsdev.mapper;

import com.king.hrmsdev.entity.EmployReword;
import com.king.hrmsdev.entity.Employees;
import com.king.hrmsdev.entity.Position;
import com.king.hrmsdev.entity.Salary;
import com.king.hrmsdev.pojo.SalaryList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * creat By SlaryMapper 0n 2019/9/21
 */
@Mapper
public interface SlaryMapper {
     EmployReword QuaryRewordByID(int job_id);//查询员工的考勤信息
    Position  QuaryPositionByJobID(int job_id);//查询员工的职位和基本工资
    String QuaryDepartmentByJobID(int job_id);//查询员工的部门
    Employees QuaryALL(int job_id);//查询员工的基本信息
    void  AddSalary(Salary salary);//向数据库添加薪水纪录
    Salary QuaryReword(Employees employees);//根据名字或者工号查询薪水
    List<SalaryList> QuarySalary(Employees employees);//查询薪水纪录
    List<SalaryList>  QuaryAllSalary();
    void deleteSalary(int job_id);//删除数据
    void  updateSalary(Salary salary);//更改数据
}
