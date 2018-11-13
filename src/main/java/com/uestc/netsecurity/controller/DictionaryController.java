package com.uestc.netsecurity.controller;

import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.entry.Dictionary;
import com.uestc.netsecurity.service.IDictionaryService;
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
@RequestMapping("/app/dictionary")
public class DictionaryController {

    private static Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    IDictionaryService iDictionaryService;

    @ApiOperation(value="获取字典信息", notes="获取字典信息列表")
    @RequestMapping(value={"list"}, method= RequestMethod.GET)
    public ResultObject list(){
        List<Dictionary> students = iDictionaryService.list(null);
        ResultObject resultObject =new ResultObject();
        resultObject.setData(students);
        return resultObject;
    }

}

