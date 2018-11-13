package com.uestc.netsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.entry.Attack;
import com.uestc.netsecurity.service.IAttackService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/attack")
public class AttackController {

    private static Logger logger = LoggerFactory.getLogger(AttackController.class);

    @Autowired
    IAttackService iAttackService;

    @ApiOperation(value="获取拓扑图弹窗信息内容", notes="获取拓扑图弹窗信息内容")
    @RequestMapping(value={"attackList"}, method= RequestMethod.GET)
    public ResultObject devicesInfo(){

        QueryWrapper<Attack> sqlwrapper = new QueryWrapper<>();
        sqlwrapper.select("*");
        sqlwrapper.groupBy("district_id");
        sqlwrapper.groupBy("type");
        sqlwrapper.groupBy("status");
        List<Attack> attackList = iAttackService.list(sqlwrapper);

        List<Map<String,Object>> dataList= new ArrayList();
        for(Attack attack:attackList){

            HashMap<String,Object> map = new HashMap<>();

            map.put("id",attack.getDistrictId());
            map.put("type",attack.getType());
            map.put("status",attack.getStatus());

            QueryWrapper<Attack> wrapper = new QueryWrapper<>();
            wrapper.select("description");
            wrapper.eq("district_id",attack.getDistrictId());
            wrapper.eq("type",attack.getType());
            wrapper.eq("status",attack.getStatus());
            List<Attack> list = iAttackService.list(wrapper);

            map.put("desc",list);
            dataList.add(map);

        }
        ResultObject resultObject =new ResultObject();
        resultObject.setData(dataList);

        return resultObject;
    }

}

