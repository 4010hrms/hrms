package com.king.hrmsdev.controller;

import com.alibaba.fastjson.JSONObject;
import com.king.hrmsdev.entity.Aleave;
import com.king.hrmsdev.entity.Echeck;
import com.king.hrmsdev.pojo.aleaveinfo;
import com.king.hrmsdev.pojo.echeckinfo;
import com.king.hrmsdev.service.AttendanceService;
import com.king.hrmsdev.service.RewardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AttendanceController {

    @Resource
    private AttendanceService attendanceservice;

    @Resource
    private RewardService rewardService;

    @RequestMapping(value = "/Findallaleave", method = RequestMethod.POST)
    public JSONObject Findallaleave(){
        List<aleaveinfo> FindallList = attendanceservice.Findallaleave();
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "Findallaleave");
        result.put("allaleaveinfolist",FindallList);
        return result;
    }

    @RequestMapping(value = "/Findallapproval", method = RequestMethod.POST)
    public List<aleaveinfo> Findallapproval(){
        List<aleaveinfo> Findallapprovallist = attendanceservice.Findallapproval();
        return Findallapprovallist;
    }

    @RequestMapping(value = "/Findallrefuse", method = RequestMethod.POST)
    public List<aleaveinfo> Findallrefuse(){
        List<aleaveinfo> Findallrefuselist = attendanceservice.Findallrefuse();
        return Findallrefuselist;
    }


    @RequestMapping(value = "/Findalluntreated", method = RequestMethod.POST)
    public List<aleaveinfo> Findalluntreated(){
        List<aleaveinfo> Findalluntreatedlist = attendanceservice.Findalluntreated();
        return Findalluntreatedlist;
    }

    @RequestMapping(value = "/Approvalaleave", method = RequestMethod.POST)
    public int Approvalaleave(@RequestParam("aleave_id") int aleave_id){
        int flag=attendanceservice.Approvalaleave(aleave_id);
        return flag;
    }

    @RequestMapping(value = "/Refusealeave", method = RequestMethod.POST)
    public int Refusealeave(@RequestParam("aleave_id") int aleave_id){

        int flag=attendanceservice.Refusealeave(aleave_id);
        return flag;
    }

    @RequestMapping(value = "/Insertaleave", method = RequestMethod.POST)
    public int Insertaleave(@RequestParam(value = "aleave_id",required=false,defaultValue="") Integer aleave_id,
                             @RequestParam("job_id") int job_id,
                             @RequestParam("btime") @DateTimeFormat(pattern="yyyy-MM-dd") Date btime,
                             @RequestParam("etime") @DateTimeFormat(pattern="yyyy-MM-dd") Date etime,
                             @RequestParam(value = "reason",required=false) String reason,
                             @RequestParam("examine") int examine){
        Aleave aleave=new Aleave();

        if(aleave_id != null){
            aleave.setAleave_id(aleave_id);
        }
        aleave.setJob_id(job_id);
        aleave.setBtime(btime);
        aleave.setEtime(etime);

        if (reason!=null){
        aleave.setReason(reason);
        }
        aleave.setExamine(examine);
        int flag=attendanceservice.Insertaleave(aleave);
        return flag;
    }

    @RequestMapping(value = "/AleaveFuzzyreward", method = RequestMethod.POST)
    public List<aleaveinfo> AleaveFuzzyreward(@RequestParam(value = "job_id",required=false,defaultValue="") Integer job_id,
                                              @RequestParam(value = "aleave_id",required=false,defaultValue="") Integer aleave_id,
                                              @RequestParam(value = "ename",required=false) String ename,
                                              @RequestParam(value = "beginDate",required=false) String btime,
                                              @RequestParam(value = "endDate",required=false) String etime){

        Map map=new HashMap();

        map.put("job_id",job_id);
        map.put("aleave_id",aleave_id);
        map.put("ename",ename);
        map.put("btime",btime);
        map.put("etime",etime);


        List list=attendanceservice.AleaveFuzzyreward(map);
        return list;
    }





    @RequestMapping(value = "/Findallecheck", method = RequestMethod.POST)
    public JSONObject Findallecheck(){
        List<echeckinfo> echeckinfoList=attendanceservice.Findallecheck();
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "Findallecheck");
        result.put("allecheckinfoList",echeckinfoList);



        return result;
    }

    @RequestMapping(value = "/FindByecheck_id", method = RequestMethod.POST)
    public echeckinfo FindByecheck_id(@RequestParam("echeck_id") int echeck_id){
        echeckinfo echeckinfo = attendanceservice.FindByecheck_id(echeck_id);
        return echeckinfo;
    }

    @RequestMapping(value = "/Insertecheck", method = RequestMethod.POST)
    public int Insertecheck(@RequestParam(value = "echeck_id",required=false,defaultValue="") Integer echeck_id,
                             @RequestParam("job_id") Integer job_id,
                             @RequestParam(value = "opentime" , required=false) String opentime,
                             @RequestParam(value = "closetime", required=false) String  closetime,
                             @RequestParam("state") Integer state){

        Echeck echeck=new Echeck();


        echeck.setJob_id(job_id);
        echeck.setOpentime(opentime);
        echeck.setClosetime(closetime);
        echeck.setState(state);


        int flag=attendanceservice.Insertecheck(echeck);
        return flag;
    }




    @RequestMapping(value = "/Updateecheck", method = RequestMethod.POST)
    public int Updateecheck(@RequestParam("echeck_id") Integer echeck_id,
                             @RequestParam("job_id") Integer job_id,
                             @RequestParam("opentime") String opentime,
                             @RequestParam(value = "closetime",required=false) String closetime,
                             @RequestParam("state") Integer state){
        Echeck echeck=new Echeck();


        echeck.setEcheck_id(echeck_id);
        echeck.setJob_id(job_id);
        echeck.setOpentime(opentime);
        echeck.setClosetime( closetime);
        echeck.setState(state);

        System.out.println("echeck++++++++"+echeck);

        int flag=attendanceservice.Updateecheck(echeck);

        return flag;

//        if (closetime != null){
//            int worktime=(int) (closetime.getTime()-opentime.getTime())/(1000*60*60);
//            if(worktime>6)
//                echeck.setState(0);
//            if (worktime<6)
//                echeck.setState(1);
//        }
//
//        int reward_id=rewardService.selectreward_id(job_id);
//
//        Map map=new HashMap();
//        map.put("opentime",opentime);
//        map.put("closetime",closetime);
//        map.put("reward_id",reward_id);
//
//        int flag1=rewardService.Updaterewardeveryday(map);
//        int flag=attendanceservice.Updateecheck(echeck);
//
//        if (flag1==1 && flag==1){
//            return 1;
//        }
//        else return 0;
    }


    @RequestMapping(value = "/Deleteecheck", method = RequestMethod.POST)
    public int Deleteecheck(@RequestParam("echeck_id") int echeck_id){

        int flag=attendanceservice.Deleteecheck(echeck_id);
        return flag;
    }

    @RequestMapping(value = "/EcheckFuzzyreward", method = RequestMethod.POST)
    public List<echeckinfo> EcheckFuzzyreward(@RequestParam(value = "job_id",required=false,defaultValue="") Integer job_id,
                                              @RequestParam(value = "echeck_id",required=false,defaultValue="") Integer echeck_id,
                                              @RequestParam(value = "ename",required=false) String ename,
                                              @RequestParam(value = "opentime",required=false) String opentime,
                                              @RequestParam(value = "closetime",required=false) String closetime){

        Map map=new HashMap();
        if (opentime==""){
            opentime=null;
        }
        if (closetime==""){
            closetime=null;
        }

        map.put("job_id",job_id);
        map.put("echeck_id",echeck_id);
        map.put("ename",ename);
        map.put("opentime",opentime);
        map.put("closetime",closetime);

        List list=attendanceservice.EcheckFuzzyreward(map);

        return list;
    }


}
