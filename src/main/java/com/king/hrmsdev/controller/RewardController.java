package com.king.hrmsdev.controller;

import com.alibaba.fastjson.JSONObject;
import com.king.hrmsdev.entity.Reward;
import com.king.hrmsdev.pojo.rewardinfo;
import com.king.hrmsdev.service.RewardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RewardController {

    @Resource
    private RewardService rewardservice;

    @RequestMapping(value = "/Findallreward", method = RequestMethod.POST)
    public JSONObject rewardList(){
        List<rewardinfo> FindallList = rewardservice.Findallreward();
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "rewardList");
        result.put("allrewardinfolist",FindallList);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(result.toJSONString());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        return result;
    }

    @RequestMapping(value = "/Findalllate", method = RequestMethod.POST)
    public List<rewardinfo> Findalllate(){
        List<rewardinfo> FindalllateList = rewardservice.Findalllate();
        return FindalllateList;
    }


    @RequestMapping(value = "/Findallleaveearly", method = RequestMethod.POST)
    public List<rewardinfo> Findallleaveearly(){
        List<rewardinfo> FindallleaveearlyList = rewardservice.Findallleaveearly();
        return FindallleaveearlyList;
    }

    @RequestMapping(value = "/Findallleave", method = RequestMethod.POST)
    public List<rewardinfo> Findallleave(){
        List<rewardinfo> FindallleaveList = rewardservice.Findallleave();
        return FindallleaveList;
    }

    @RequestMapping(value = "/Findallabsenteeism", method = RequestMethod.POST)
    public List<rewardinfo> Findallabsenteeism(){
        List<rewardinfo> FindallabsenteeismList = rewardservice.Findallabsenteeism();
        return FindallabsenteeismList;
    }

    @RequestMapping(value = "/FindByID", method = RequestMethod.POST)
    public rewardinfo FindByID(@RequestParam("reward_id") int reward_id){

        rewardinfo FindByIDinfo = rewardservice.FindByID(reward_id);
        return FindByIDinfo;
    }

    @RequestMapping(value = "/Insertreward", method = RequestMethod.POST)
    public int Insertreward(@RequestParam(value = "reward_id",required=false,defaultValue="") Integer reward_id,
                            @RequestParam("job_id") int job_id,
                            @RequestParam("checktime") String checktime,
                            @RequestParam("overtime") int overtime,
                            @RequestParam("late") int late,
                            @RequestParam("leaveearly") int leaveearly,
                            @RequestParam("aleave") int aleave,
                            @RequestParam("absenteeism") int absenteeism) {
        Reward reward = new Reward();

        String str = checktime;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = null;
        if (d != null) {
            date = new Date(d.getTime());
        }
        if (reward_id != null) {

            reward.setReward_id(reward_id);
        }
        reward.setJob_id(job_id);
        reward.setChecktime(date);
        reward.setOvertime(overtime);
        reward.setLate(late);
        reward.setLeaveearly(leaveearly);
        reward.setAleave(aleave);
        reward.setAbsenteeism(absenteeism);

        System.out.println("reward:" + reward);

        int flag = rewardservice.Insertreward(reward);
        return flag;

    }

    @RequestMapping(value = "/Updatereward", method = RequestMethod.POST)
    public int Updatereward(@RequestParam("reward_id") Integer reward_id,
                            @RequestParam("job_id") int job_id,
                            @RequestParam("checktime") String checktime,
                            @RequestParam("overtime") int overtime,
                            @RequestParam("late") int late,
                            @RequestParam("leaveearly") int leaveearly,
                            @RequestParam("aleave") int aleave,
                            @RequestParam("absenteeism") int absenteeism){
        Reward reward=new Reward();

        String str = checktime;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = null;
        if (d != null) {
            date = new Date(d.getTime());
        }
        if (reward_id != null) {

            reward.setReward_id(reward_id);
        }

        reward.setReward_id(reward_id);
        reward.setJob_id(job_id);
        reward.setChecktime(date);
        reward.setOvertime(overtime);
        reward.setLate(late);
        reward.setLeaveearly(leaveearly);
        reward.setAleave(aleave);
        reward.setAbsenteeism(absenteeism);

        int flag=rewardservice.Updatereward(reward);
        return flag;


    }


    @RequestMapping(value = "/deletereward", method = RequestMethod.POST)
    public int deletereward(@RequestParam("reward_id") int reward_id){

        int flag=rewardservice.deletereward(reward_id);
        return flag;

    }

    @RequestMapping(value = "/Findworkhard", method = RequestMethod.POST)
    public List<rewardinfo> Findworkhard(){
        List<rewardinfo> FindworkhardList = rewardservice.Findworkhard();
        return FindworkhardList;
    }


    @RequestMapping(value = "/RewardFuzzyreward", method = RequestMethod.POST)
    public List<rewardinfo> RewardFuzzyreward(@RequestParam(value = "job_id",required=false,defaultValue="") Integer job_id,
                                              @RequestParam(value = "reward_id",required=false,defaultValue="") Integer reward_id,
                                              @RequestParam(value = "ename",required=false) String ename,
                                              @RequestParam(value = "beginDate",required=false) String beginDate,
                                              @RequestParam(value = "endDate",required=false) String endDate
    ){


        if (beginDate==""){
            beginDate=null;
        }
        if (endDate==""){
            endDate=null;
        }



        Map map=new HashMap();
        map.put("job_id",job_id);
        map.put("reward_id",reward_id);
        map.put("ename",ename);
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);

        List list=rewardservice.RewardFuzzyreward(map);

        return list;

    }

//    @RequestMapping(value = "/reward/{job_id}", method = RequestMethod.GET)
//    public Reward reward(@PathVariable("job_id") int job_id){
//        Reward reward = rewardservice.FindByID(job_id);
//        return reward;
//    }

}
