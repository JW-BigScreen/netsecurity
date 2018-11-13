package com.uestc.netsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.entry.Student;
import com.uestc.netsecurity.service.IStudentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/app/student")
public class StudentController {

    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    IStudentService iStudentService;

    @ApiOperation(value="查询对象", notes="")
    @RequestMapping(value={"get"}, method= RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String",
                    name = "Authorization", value = "token", required = false)
                    })
    public ResultObject list(){

        QueryWrapper<Student> queryWrapper =new QueryWrapper<>();
        queryWrapper.lambda().eq(Student::getCity, "伦敦")
                .and(e -> e.eq(Student::getName, "Ronnie"));
        Student student = iStudentService.getOne(queryWrapper);
        ResultObject resultObject = new ResultObject();
        resultObject.setData(student);
        return resultObject;
    }


    @ApiOperation(value="保存对象", notes="")
    @RequestMapping(value={"save"}, method= RequestMethod.GET)
    @ResponseBody
    public String save() {
        //insert
        Student student = new Student();
        student.setName("zhangsan");
        student.setCity("54");
        student.setAge(23);
        boolean res = iStudentService.save(student);

        return res ? "success" : "fail";
    }


}

