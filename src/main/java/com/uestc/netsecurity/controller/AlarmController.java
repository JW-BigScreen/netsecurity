package com.uestc.netsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.entry.*;
import com.uestc.netsecurity.entry.vo.AlarmVo;
import com.uestc.netsecurity.service.*;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/app/alarm")
public class AlarmController {

    private static Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Autowired
    IAlarmService iAlarmService;

    @Autowired
    IMaterialService iMaterialService;

    @Autowired
    IIndicatorService iIndicatorService;

    @Autowired
    IAreaIpService iAreaIpService;


    @Autowired
    IAreaNavService iAreaNavService;


    /*@ApiIgnore
    @ApiOperation(value="获取警报信息", notes="获取警报信息列表")
    @RequestMapping(value={"list"}, method= RequestMethod.GET)
    public ResultObject list(){
        List<Alarm> students = iAlarmService.list(null);
        ResultObject resultObject =new ResultObject();
        resultObject.setData(students);
        return resultObject;
    }*/

    @ApiOperation(value="获取安全设备数量和运维信息设备数量", notes="获取安全设备和运维信息（8个")
    @RequestMapping(value={"devicesInfo"}, method= RequestMethod.GET)
    public ResultObject devicesInfo(){

        Map data = new HashMap<String,Long>();
        data.put("virusFiles",0);

        QueryWrapper<Material> devicewrapper = new QueryWrapper<>();
        devicewrapper.select("id");
        List<Material> deviceList = iMaterialService.list(devicewrapper);
        data.put("deviceTotal",deviceList.size());

        //攻击设备
        QueryWrapper<Alarm> srcwrapper = new QueryWrapper<>();
        srcwrapper.select("id","src_address","dest_address","event_id");
        srcwrapper.groupBy("src_address");
        List<Alarm> srcList = iAlarmService.list(srcwrapper);
        data.put("srcTarget",srcList.size());

        //被攻击设备
        QueryWrapper<Alarm> deswrapper = new QueryWrapper<>();
        deswrapper.select("id","src_address","dest_address","event_id");
        deswrapper.groupBy("dest_address");
        List<Alarm> desList = iAlarmService.list(deswrapper);
        data.put("destTarget",desList.size());

        //安全事件
        QueryWrapper<Alarm> eventwrapper = new QueryWrapper<>();
        eventwrapper.select("id","src_address","dest_address","event_id");
        eventwrapper.groupBy("event_id");
        List<Alarm> eventsList = iAlarmService.list(eventwrapper);
        data.put("events",eventsList.size());

        //异常设备
        QueryWrapper<Material> abnormalDeviceWrapper = new QueryWrapper<>();
        abnormalDeviceWrapper.select("id");
        abnormalDeviceWrapper.ne("status",0).eq("status",null);
        List<Material> errorDeviceList = iMaterialService.list(abnormalDeviceWrapper);
        data.put("abnormalDevice",errorDeviceList.size());

        //异常次数
        QueryWrapper<Indicator> abnormalWrapper = new QueryWrapper<>();
        abnormalWrapper.select("id");
        abnormalWrapper.lt("value",80);
        List<Indicator> abnormalList = iIndicatorService.list(abnormalWrapper);
        data.put("abnormal",abnormalList.size());

        //服务器
        QueryWrapper<Indicator> abnormalServerWrapper = new QueryWrapper<>();
        abnormalServerWrapper.select("id");
        abnormalServerWrapper.lt("value",80);
        abnormalServerWrapper.eq("device_type","2000");//2000代表服务器
        List<Indicator> serverList = iIndicatorService.list(abnormalServerWrapper);
        data.put("serverList",serverList.size());

        //网络设备
        QueryWrapper<Indicator> netDeviceWrapper = new QueryWrapper<>();
        netDeviceWrapper.select("id");
        netDeviceWrapper.lt("value",80);
        netDeviceWrapper.eq("device_type","1000");//1000代表网络设备
        List<Indicator> netDeviceList = iIndicatorService.list(netDeviceWrapper);
        data.put("netDeviceList",netDeviceList.size());

        ResultObject resultObject =new ResultObject();
        resultObject.setData(data);

        return resultObject;
    }

    @ApiOperation(value="获取攻击信息列表", notes="获取攻击信息列表")
    @RequestMapping(value={"list"}, method= RequestMethod.GET)
    public ResultObject attackInfoList(){

        //攻击信息
        QueryWrapper<Alarm> attackwrapper = new QueryWrapper<>();
        attackwrapper.select("id","shaco_time","device_name","src_address",
                "dest_address","descript","threat_severity");
        attackwrapper.orderByDesc("collector_receipt_time");
        List<Alarm> attackList = iAlarmService.page(new Page<Alarm>(1,10),attackwrapper)
                .getRecords();

        List<AlarmVo> voList = new ArrayList<>();
        if(!attackList.isEmpty()){

            for(Alarm alarm:attackList){

                AlarmVo alarmVo = new AlarmVo();
                BeanUtils.copyProperties(alarm,alarmVo);

                QueryWrapper<AreaIp> areaIpwrapper = new QueryWrapper<>();
                areaIpwrapper.select("id","area_id");
                areaIpwrapper.eq("ip",alarm.getDestAddress());
                List<AreaIp> areaIpList = iAreaIpService.list(areaIpwrapper);

                QueryWrapper<AreaIp> areaIpwrapper1 = new QueryWrapper<>();
                areaIpwrapper1.select("id","area_id");
                areaIpwrapper1.apply("inet_aton('"+alarm.getDestAddress()+"') BETWEEN inet_aton(start_ip)\n" +
                                "AND inet_aton(end_ip)");
                List<AreaIp> areaIpList1 = iAreaIpService.list(areaIpwrapper1);

                if(!areaIpList.isEmpty()){
                    QueryWrapper<AreaNav> areaNavwrapper = new QueryWrapper<>();
                    areaNavwrapper.select("id","name");
                    areaNavwrapper.eq("id",areaIpList.get(0).getAreaId());
                    AreaNav areaNav = iAreaNavService.getOne(areaNavwrapper);
                    if(areaNav!=null){
                        alarmVo.setDestZoom(areaNav.getName());
                    }
                }

                if(!areaIpList1.isEmpty()){
                    QueryWrapper<AreaNav> areaNavwrapper = new QueryWrapper<>();
                    areaNavwrapper.select("id","name");
                    areaNavwrapper.eq("id",areaIpList1.get(0).getAreaId());
                    AreaNav areaNav = iAreaNavService.getOne(areaNavwrapper);
                    if(areaNav!=null){
                        alarmVo.setDestDepartment(areaNav.getName());
                    }
                }

                voList.add(alarmVo);
            }
        }

        ResultObject resultObject =new ResultObject();
        resultObject.setData(voList);

        return resultObject;
    }

    @ApiOperation(value="获取攻击信息柱状图", notes="获取攻击信息（柱状图）")
    @RequestMapping(value={"bar"}, method= RequestMethod.GET)
    public ResultObject attackBarList(){

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime oneWeekBefore = localDateTime.minusDays(7);

        QueryWrapper<Alarm> wrapper = new QueryWrapper<>();
        wrapper.select("dest_address","count(dest_address) as value");
        wrapper.between("collector_receipt_time",oneWeekBefore,localDateTime);
        wrapper.groupBy("dest_address");
        List<Map<String,Object>> deviceList = iAlarmService.listMaps(wrapper);

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


    @ApiOperation(value="获被攻击信息饼状图", notes="获被攻击信息饼状图")
    @RequestMapping(value={"pie"}, method= RequestMethod.GET)
    public ResultObject attackPieChart(){

        //攻击信息
        Map data = new HashMap<String, BigDecimal>();

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime oneWeekBefore = localDateTime.minusDays(7);

        //病毒
        QueryWrapper<Alarm> viruswrapper = new QueryWrapper<>();
        viruswrapper.select("id");

        viruswrapper.between("collector_receipt_time",oneWeekBefore,localDateTime);
        viruswrapper.eq("attack_method","1");//virus
        List<Alarm> viruswrapperList = iAlarmService.list(viruswrapper);
        int virusSize = viruswrapperList.size();

        //sql
        QueryWrapper<Alarm> sqlwrapper = new QueryWrapper<>();
        sqlwrapper.select("id");
        sqlwrapper.between("collector_receipt_time",oneWeekBefore,localDateTime);
        sqlwrapper.eq("attack_method","2");//sql
        List<Alarm> sqlList = iAlarmService.list(sqlwrapper);
        int sqlSize = sqlList.size();

        //XSS
        QueryWrapper<Alarm> xsswrapper = new QueryWrapper<>();
        xsswrapper.select("id");
        xsswrapper.between("collector_receipt_time",oneWeekBefore,localDateTime);
        xsswrapper.eq("attack_method","3");//xss
        List<Alarm> xssList = iAlarmService.list(xsswrapper);
        int xssSize = xssList.size();

        //ddos
        QueryWrapper<Alarm> ddoswrapper = new QueryWrapper<>();
        ddoswrapper.select("id");
        ddoswrapper.between("collector_receipt_time",oneWeekBefore,localDateTime);
        ddoswrapper.eq("attack_method","4");//ddos
        List<Alarm> ddosList = iAlarmService.list(ddoswrapper);
        int ddosSize = ddosList.size();

        long total = 0;
        int counts = 0;
        if(virusSize == 0){
            data.put("virus",BigDecimal.ZERO);
        }else{
            total+=virusSize;
            counts++;
        }

        if(sqlSize == 0){
            data.put("sql",BigDecimal.ZERO);
        }else{
            total+=sqlSize;
            counts++;
        }

        if(xssSize == 0){
            data.put("xss",BigDecimal.ZERO);
        }else{
            total+=xssSize;
            counts++;
        }

        if(ddosSize == 0){
            data.put("ddos",BigDecimal.ZERO);
        }else{
            total+=ddosSize;
            counts++;
        }

        if(total !=0){
            data.put("virus",new BigDecimal(virusSize).
                    divide(BigDecimal.valueOf(total),1,BigDecimal.ROUND_HALF_DOWN));
            data.put("sql",new BigDecimal(sqlSize).
                    divide(BigDecimal.valueOf(total),1,BigDecimal.ROUND_HALF_DOWN));
            data.put("xss",new BigDecimal(xssSize).
                    divide(BigDecimal.valueOf(total),1,BigDecimal.ROUND_HALF_DOWN));
            data.put("ddos",new BigDecimal(ddosSize).
                    divide(BigDecimal.valueOf(total),1,BigDecimal.ROUND_HALF_DOWN));
        }

        ResultObject resultObject =new ResultObject();
        resultObject.setData(data);

        return resultObject;
    }

    @ApiOperation(value="获取各个生产区攻击信息", notes="获取各个生产区攻击信息")
    @RequestMapping(value={"areaInfo"}, method= RequestMethod.GET)
    public ResultObject topInfo(){

        List<Map<String,Object>> dataList= new ArrayList<>();

        List<AreaNav> areaNavList = iAreaNavService.list(null);

        ResultObject resultObject =new ResultObject();
        //resultObject.setData();

        return resultObject;
    }


}

