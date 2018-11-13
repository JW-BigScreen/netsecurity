package com.uestc.netsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.entry.WorkOrder;
import com.uestc.netsecurity.service.IWorkOrderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/workOrder")
public class WorkOrderController {

    private static Logger logger = LoggerFactory.getLogger(WorkOrderController.class);

    @Autowired
    IWorkOrderService iWorkOrderService;

    @ApiOperation(value="获取工单信息列表", notes="获取信息信息列表")
    @RequestMapping(value={"list"}, method= RequestMethod.GET)
    public ResultObject workOrderInfoList(){

        //攻击信息
        QueryWrapper<WorkOrder> orderwrapper = new QueryWrapper<>();
        orderwrapper.orderByDesc("create_time");
        List<WorkOrder> orderList = iWorkOrderService.page(new Page<WorkOrder>(1,10),orderwrapper)
                .getRecords();

        ResultObject resultObject =new ResultObject();
        resultObject.setData(orderList);

        return resultObject;
    }


}

