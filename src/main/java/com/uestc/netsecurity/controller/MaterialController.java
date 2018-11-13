package com.uestc.netsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.entry.Material;
import com.uestc.netsecurity.service.IMaterialService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/material")
public class MaterialController {

    private static Logger logger = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    IMaterialService iMaterialService;

    /*@ApiIgnore
    @ApiOperation(value="获取基础设备", notes="获取基础设备信息列表")
    @RequestMapping(value={"list"}, method= RequestMethod.GET)
    public ResultObject list(){
        List<Material> students = iMaterialService.list(null);

        ResultObject resultObject =new ResultObject();
        resultObject.setData(students);
        return resultObject;
    }*/

    @ApiOperation(value="获取异常设备信息列表", notes="获取异常设备信息列表")
    @RequestMapping(value={"list"}, method= RequestMethod.GET)
    public ResultObject abnormalDeviceList(){

        //攻击信息
        QueryWrapper<Material> abnormalDevicewrapper = new QueryWrapper<>();
        abnormalDevicewrapper.orderByDesc("health_value");
        List<Material> materialList = iMaterialService.page(new Page<Material>(1,10),abnormalDevicewrapper)
                .getRecords();

        ResultObject resultObject =new ResultObject();
        resultObject.setData(materialList);

        return resultObject;
    }
}

