package com.uestc.netsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.entry.AreaNav;
import com.uestc.netsecurity.service.IAreaNavService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/app/areanav")
public class AreaNavController {

    private static Logger logger = LoggerFactory.getLogger(AreaNavController.class);

    @Autowired
    IAreaNavService iAreaNavService;

    @ApiOperation(value="获取区域部门信息", notes="获取区域部门信息列表")
    @RequestMapping(value={"list"}, method= RequestMethod.GET)
    public ResultObject list(){
        QueryWrapper<AreaNav> queryWrapper = new QueryWrapper();
        queryWrapper.select("name");
        queryWrapper.excludeColumns(AreaNav.class,"id","pid","remark","create_time");

        queryWrapper.eq("pid",0);
        List<AreaNav> students = iAreaNavService.list(null);
        ResultObject resultObject =new ResultObject();
        resultObject.setData(students);
        resultObject.setSuccess(Boolean.TRUE);
        resultObject.setCode(HttpServletResponse.SC_OK);

        return resultObject;
    }

}

