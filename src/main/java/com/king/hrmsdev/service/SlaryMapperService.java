package com.king.hrmsdev.service;




import com.king.hrmsdev.entity.EmployReword;
import com.king.hrmsdev.entity.Employees;
import com.king.hrmsdev.entity.Position;
import com.king.hrmsdev.entity.Salary;
import com.king.hrmsdev.mapper.SlaryMapper;
import com.king.hrmsdev.pojo.SalaryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * creat By SlaryMapperService 0n 2019/9/21
 */
@Service
public class SlaryMapperService {

    @Autowired
    private SlaryMapperService slaryMapperService;

    @Resource
    private SlaryMapper slaryMapper;

    public  void deleteSalary(int job_id){
        slaryMapper.deleteSalary(job_id);
    }
    public   List<SalaryList>  QuaryAllSalary(){
        List<SalaryList> lists=slaryMapper.QuaryAllSalary();
        for (SalaryList salaryList:lists){
            Integer integer=salaryList.getJob_id();
            String department=slaryMapper.QuaryDepartmentByJobID(integer);
            salaryList.setCheckMoney(BigDecimal.valueOf(check_moeny(integer)));
            salaryList.setDepartment(department);
            if(salaryList.getSex().equals("1")){
                salaryList.setSex("男");

            }else
            {    salaryList.setSex("女");
            }
        }

        return  lists;
    }
    public  void updateSalary(Salary salary){
        slaryMapper.updateSalary(salary);
    }
    public List<SalaryList> QuarySalary(Employees employees){
        List<SalaryList> salaryLists =slaryMapper.QuarySalary(employees);
        List<SalaryList> salaryLists1=new ArrayList<>();
            for (int i=0;i<salaryLists.size();i++) {
                if (salaryLists.get(i) != null) {
                    String sex;
                    int id = salaryLists.get(i).getJob_id();

                    String ename = salaryLists.get(i).getEname();
                    if(salaryLists.get(i).getSex().equals("1")){
                       sex="男";

                    }else
                    {   sex="女";
                    }
                    String department = slaryMapper.QuaryDepartmentByJobID(id);//部门
                    String position = salaryLists.get(i).getPosition();//职位
                    BigDecimal baseMoney = salaryLists.get(i).getBaseMoney();//基本工资
                    BigDecimal finalMoney = salaryLists.get(i).getFinalMoney();//最终薪资
                    System.out.println(ename);
                    System.out.println(id);
                    System.out.println(sex);
                    System.out.println(department);
                    System.out.println(position);
                    System.out.println(baseMoney);
                    System.out.println(finalMoney);

                    BigDecimal checkMoney = finalMoney.subtract(baseMoney);//考勤奖金
                    System.out.println(checkMoney);
                    SalaryList salaryList = new SalaryList(id, ename, sex, department, position, baseMoney, checkMoney, finalMoney);
                    System.out.println(salaryLists);
                    salaryLists1.add(salaryList);
                }
            }
           return  salaryLists1;
   }
//  //  public int[]  QuaryIdByName(String ename){
//     int[] id=  slaryMapper.QuaryIdByName(ename);
//     return  id;
//    }
    public Salary QuaryReword(Employees employees){
        Salary salary =slaryMapper.QuaryReword(employees);

        return  salary;
    }
    //通过员工查询考勤信息
    public EmployReword QuaryRewordByID(int job_id){
        EmployReword er=slaryMapper.QuaryRewordByID(job_id);
        return  er;
    }
    //查询员工的职位和基本工资
    public Position QuaryPositionByJobID(int job_id){
        Position position =slaryMapper.QuaryPositionByJobID(job_id);
        return  position;
    }
    //查询员工的部门
    public String QuaryDepartmentByJobID(int job_id){
    String department=slaryMapper.QuaryDepartmentByJobID(job_id);
    return  department;
    }
    //查询员工的基本信息
    public  Employees QuaryALL(int job_id){
        Employees baseMessage=slaryMapper.QuaryALL(job_id);
        return  baseMessage;
    }
    //计算员工考勤奖金
    public int check_moeny(int  job_id){
        int overtime;//统计当月加班时间
        int late;//统计迟到次数
        int leaveearly;//统计早退次数
        int leave;//统计请假次数
        int absenteeism;//统计旷工次数
        overtime =slaryMapper.QuaryRewordByID(job_id).getOvertime();
        late=slaryMapper.QuaryRewordByID(job_id).getLate();
        leave=slaryMapper.QuaryRewordByID(job_id).getLeave();
        leaveearly=slaryMapper.QuaryRewordByID(job_id).getLeaveearly();
        absenteeism=slaryMapper.QuaryRewordByID(job_id).getAbsenteeism();
        int money=overtime*22-late*10-leaveearly*20-leave*50-absenteeism*100;
        return  money;
    }
    //计算员工当前工资
    public BigDecimal getSlary(int job_id){
        BigDecimal baseMoney;//职位基础工资
        BigDecimal totalMoney;//总工资
        baseMoney= slaryMapper.QuaryPositionByJobID(job_id).getSalary();
        totalMoney=baseMoney.add(BigDecimal.valueOf(check_moeny(job_id)));
        return  totalMoney;
    }
    //提交员工薪水纪录
    public  void AddSalary(int job_id,Date date){
        //获取月薪
        BigDecimal bigDecimal= slaryMapperService.getSlary(job_id);
        //添加Salary对象
        Salary salary=new Salary(job_id,bigDecimal,date);
        //存进数据库
        slaryMapper.AddSalary(salary);
   }
       //查询薪水纪录
       public List<SalaryList> getSalaryList(Employees employees){
            List<SalaryList> salaryLists=new ArrayList<>();
            SalaryList salaryList =new SalaryList();

    return  salaryLists;
   }
}

