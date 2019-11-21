package com.king.hrmsdev.service;

import com.king.hrmsdev.entity.Aleave;
import com.king.hrmsdev.entity.Echeck;
import com.king.hrmsdev.mapper.AttendanceMapper;
import com.king.hrmsdev.pojo.aleaveinfo;
import com.king.hrmsdev.pojo.echeckinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {

    @Resource
    private AttendanceMapper attendanceMapper;

    public List<aleaveinfo> Findallaleave(){
        List<aleaveinfo> aleaveinfoList = attendanceMapper.Findallaleave();
        return aleaveinfoList;
    }

    public List<aleaveinfo> Findallapproval(){
        List<aleaveinfo> Findallapprovallist = attendanceMapper.Findallapproval();
        return Findallapprovallist;
    }

    public List<aleaveinfo> Findallrefuse(){
        List<aleaveinfo> Findallrefuselist = attendanceMapper.Findallrefuse();
        return Findallrefuselist;
    }

    public List<aleaveinfo> Findalluntreated(){
        List<aleaveinfo> Findalluntreatedlist = attendanceMapper.Findalluntreated();
        return Findalluntreatedlist;
    }

    public int Approvalaleave(int aleave_id){
        int flag=1;
        try {
            attendanceMapper.Approvalaleave(aleave_id);
        } catch (Exception e) {
            flag=0;
        }
        finally {
            return flag;
        }

    }

    public int Refusealeave(int aleave_id){
        int flag=1;
        try {
            attendanceMapper.Refusealeave(aleave_id);
        } catch (Exception e) {
            flag=0;
        }
        finally {
            return flag;
        }

    }

    public int Insertaleave(Aleave aleave){

        int flag=1;
        try {
            attendanceMapper.Insertaleave(aleave);
        } catch (Exception e) {
            flag=0;
        }
        finally {
            System.out.println("service-------------------->"+flag);
            return flag;
        }

    }

    public List<aleaveinfo> AleaveFuzzyreward(Map<String,Object> map){
        Integer job_id=(Integer)map.get("job_id");
        Integer aleave_id=(Integer) map.get("aleave_id");
        String ename=(String) map.get("ename");
        Object btime=map.get("btime");
        Object etime=map.get("etime");
        if(btime==""){
            btime=null;
        }
        if(etime=="")
        {
            etime=null;
        }



        Map map1=new HashMap();

        map1.put("job_id",job_id);
        map1.put("aleave_id",aleave_id);
        map1.put("ename",ename);
        map1.put("btime",btime);
        map1.put("etime",etime);

        List list=attendanceMapper.AleaveFuzzyreward(map1);

        return list;

    }




    //考勤记录

    public List<echeckinfo> Findallecheck(){
        List<echeckinfo> echeckinfoList=attendanceMapper.Findallecheck();
        return  echeckinfoList;
    }

    public echeckinfo FindByecheck_id(int echeck_id){
        echeckinfo echeckinfo = attendanceMapper.FindByecheck_id(echeck_id);
        return echeckinfo;
    }

    public int Insertecheck(Echeck echeck){
        int flag=1;
        try {
            attendanceMapper.Insertecheck(echeck);
        } catch (Exception e) {
            flag=0;
        }
        finally {
            return flag;
        }
    }

    public int Updateecheck(Echeck echeck){
        int flag=1;
        try {
            attendanceMapper.Updateecheck(echeck);
        } catch (Exception e) {
            flag=0;
            System.out.println("service  ::  echeck++++++++"+echeck);
        }
        finally {
            return flag;
        }

    }//可能有bug

    public int Deleteecheck(int echeck_id){
        int flag=1;
        try {
            attendanceMapper.Deleteecheck(echeck_id);
        } catch (Exception e) {
            flag=0;
        }
        finally {
            return flag;
        }

    }

    public List<echeckinfo> EcheckFuzzyreward(Map<String,Object> map){
        Integer job_id=(Integer)map.get("job_id");
        Integer echeck_id=(Integer)map.get("echeck_id");
        String ename=(String) map.get("ename");
        Object opentime=map.get("opentime");
        Object closetime=map.get("closetime");

        Map map1=new HashMap();

        map1.put("job_id",job_id);
        map1.put("echeck_id",echeck_id);
        map1.put("ename",ename);
        map1.put("opentime",opentime);
        map1.put("closetime",closetime);

        List list=attendanceMapper.EcheckFuzzyreward(map1);

        return list;
    }

//    public int findstate0(Map<String,Object> map){
//
//        Date opentime=(Date)map.get("opentime");
//        Date closetime=(Date)map.get("closetime");
//        Integer reward_id=(Integer)map.get("reward_id");
//
//        HashMap map1=new HashMap();
//
//        map1.put("opentime",opentime);
//        map1.put("closetime",closetime);
//        map1.put("reward_id",reward_id);
//
//        Integer state0=attendanceMapper.findstate0(map1);
//        if (state0.equals(null)){
//            state0=0;
//        }
//        return state0;
//    }
//    public int findstate1(Map<String,Object> map){
//        Date opentime=(Date)map.get("opentime");
//        Date closetime=(Date)map.get("closetime");
//        Integer reward_id=(Integer)map.get("reward_id");
//
//        HashMap map1=new HashMap();
//
//        map1.put("opentime",opentime);
//        map1.put("closetime",closetime);
//        map1.put("reward_id",reward_id);
//        Integer state1=attendanceMapper.findstate0(map1);
//
//        if (state1.equals(null)){
//            state1=0;
//        }
//        return state1;
//    }
//    public int findstate2(Map<String,Object> map){
//        Date opentime=(Date)map.get("opentime");
//        Date closetime=(Date)map.get("closetime");
//        Integer reward_id=(Integer)map.get("reward_id");
//
//        HashMap map1=new HashMap();
//
//        map1.put("opentime",opentime);
//        map1.put("closetime",closetime);
//        map1.put("reward_id",reward_id);
//        Integer state2=attendanceMapper.findstate0(map1);
//        if (state2.equals(null)){
//            state2=0;
//        }
//        return state2;
//    }
//    public int findstate3(Map<String,Object> map){
//        Date opentime=(Date)map.get("opentime");
//        Date closetime=(Date)map.get("closetime");
//        Integer reward_id=(Integer)map.get("reward_id");
//
//        HashMap map1=new HashMap();
//
//        map1.put("opentime",opentime);
//        map1.put("closetime",closetime);
//        map1.put("reward_id",reward_id);
//        Integer state3=attendanceMapper.findstate0(map1);
//        if (state3.equals(null)){
//            state3=0;
//        }
//        return state3;
//    }
//    public int findstate4(Map<String,Object> map){
//        Date opentime=(Date)map.get("opentime");
//        Date closetime=(Date)map.get("closetime");
//        Integer reward_id=(Integer)map.get("reward_id");
//
//        HashMap map1=new HashMap();
//
//        map1.put("opentime",opentime);
//        map1.put("closetime",closetime);
//        map1.put("reward_id",reward_id);
//        Integer state4=attendanceMapper.findstate0(map1);
//        if (state4.equals(null)){
//            state4=0;
//        }
//        return state4;
//    }





}
