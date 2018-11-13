package com.uestc.netsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.entry.Indicator;
import com.uestc.netsecurity.service.IIndicatorService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/indicator")
public class IndicatorController {

    private static Logger logger = LoggerFactory.getLogger(IndicatorController.class);

    @Autowired
    IIndicatorService iIndicatorService;

    @ApiIgnore
    @ApiOperation(value="获取性能信息", notes="获取性能信息列表")
    @RequestMapping(value={"list"}, method= RequestMethod.GET)
    public ResultObject list(){
        List<Indicator> students = iIndicatorService.list(null);

        ResultObject resultObject =new ResultObject();
        resultObject.setData(students);

        return resultObject;
    }

    @ApiOperation(value="异常设备统计信息", notes="异常设备统计信息（柱状图）")
    @RequestMapping(value={"abnormalDevice"}, method= RequestMethod.GET)
    public ResultObject abnormalDevice(){

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime oneWeekBefore = localDateTime.minusDays(7);

        QueryWrapper<Indicator> wrapper = new QueryWrapper<>();
        wrapper.select("target_id","count(target_id) as value");
        wrapper.eq("indicator_id","1")//cpu
                .or().eq("indicator_id","2");//ram
        wrapper.gt("value",70);//大于300，这里要注意是CPU大于百分之80或者内存大于百分之80
        wrapper.between("creat_time",oneWeekBefore,localDateTime);
        wrapper.groupBy("target_id");
        List<Map<String,Object>> deviceList = iIndicatorService.listMaps(wrapper);

        if(!deviceList.isEmpty()){
            Collections.sort(deviceList, new Comparator<Map<String, Object>>() {

                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    Long map1value = (Long) o1.get("value");
                    Long map2value = (Long) o2.get("value");
                    return map1value.intValue() - map2value.intValue();
                }
            });
        }

        ResultObject resultObject =new ResultObject();
        if(deviceList.size()>=10){
            resultObject.setData(deviceList.subList(0, 10));
        }else{
            resultObject.setData(deviceList);
        }

        return resultObject;
    }

    @ApiOperation(value="异常流量统计信息", notes="异常流量统计信息（柱状图）")
    @RequestMapping(value={"abnormalflow"}, method= RequestMethod.GET)
    public ResultObject abnormalflow(){

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime oneWeekBefore = localDateTime.minusDays(7);

        QueryWrapper<Indicator> wrapper = new QueryWrapper<>();
        wrapper.select("target_id","count(target_id) as value");
        wrapper.eq("indicator_id","3");//
        wrapper.gt("value",50);//网速大于300K/s
        wrapper.between("creat_time",oneWeekBefore,localDateTime);
        wrapper.groupBy("target_id");
        List<Map<String,Object>> netList = iIndicatorService.listMaps(wrapper);

        ResultObject resultObject =new ResultObject();
        if(!netList.isEmpty()){
            Collections.sort(netList, new Comparator<Map<String, Object>>() {

                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    Long map1value = (Long) o1.get("value");
                    Long map2value = (Long) o2.get("value");
                    return map1value.intValue()-map2value.intValue();
                }
            });
        }

        if(netList.size()>=10){
            resultObject.setData(netList.subList(0, 10));
        }else{
            resultObject.setData(netList);
        }


        return resultObject;
    }
    
}

