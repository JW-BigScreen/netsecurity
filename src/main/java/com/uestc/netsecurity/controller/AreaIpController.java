package com.uestc.netsecurity.controller;

import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.entry.AreaIp;
import com.uestc.netsecurity.service.IAreaIpService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@RestController
@RequestMapping("/app/areaip")
public class AreaIpController {

    private static Logger logger = LoggerFactory.getLogger(AreaIpController.class);

    @Autowired
    IAreaIpService iAreaIpService;

    @ApiIgnore
    @ApiOperation(value="获取区域IP信息", notes="获取区域IP信息列表")
    @RequestMapping(value={"list"}, method= RequestMethod.GET)
    public ResultObject list(){
        List<AreaIp> students = iAreaIpService.list(null);
        ResultObject resultObject =new ResultObject();
        resultObject.setData(students);
        return resultObject;
    }

}

