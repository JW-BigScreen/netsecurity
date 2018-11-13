package com.uestc.netsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.netsecurity.commons.ResultObject;
import com.uestc.netsecurity.commons.utils.EncryptionByMD5;
import com.uestc.netsecurity.entry.User;
import com.uestc.netsecurity.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/app/users")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService iUserService;

    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());


    @ApiOperation(value = "用户登录", notes = "用户输入账号密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultObject login(HttpServletRequest req,
                              HttpServletResponse res,
                              @RequestBody User user) throws ServletException{
        if (user.getUsername() == "" || user.getUsername() == null
                || user.getPassword() == "" || user.getPassword() == null)
            throw new ServletException("Please fill in username and password");

        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, user.getUsername())
                .and(e -> e.eq(User::getPassword, EncryptionByMD5.getMD5(user.getPassword())));

        User user1 = iUserService.getOne(queryWrapper);

        ResultObject resultObject = new ResultObject("登录成功",HttpServletResponse.SC_OK);
        if(user1!=null){

            // Create Twt token
            String jwtToken = Jwts.builder().setSubject(user.getUsername()).claim("roles", "member").setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

            iUserService.updateById(user1);

            res.addHeader("Authorization", "Bearer " + jwtToken);

            Map map = new HashMap<String,Object>();
            map.put("token","Bearer " + jwtToken);
            resultObject.setData(map);
            resultObject.setSuccess(Boolean.TRUE);
        }else{
            resultObject.setMsg("登录失败,账号或者密码错误");
            resultObject.setSuccess(Boolean.FALSE);
            resultObject.setCode(HttpServletResponse.SC_NOT_FOUND);
        }

        return resultObject;

    }

    @ApiIgnore
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "token",
                    value = "token", required = false)
    })
    public ResultObject getUser(@RequestHeader("token") String token) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token",token);
        //queryWrapper.select()
        User user = iUserService.getOne(queryWrapper);


        if(user!=null){
            ResultObject resultObject = new ResultObject("获取成功",HttpServletResponse.SC_OK
                    ,Boolean.TRUE,user);
            return resultObject;
        }else{
            ResultObject resultObject = new ResultObject("获取失败",HttpServletResponse.SC_NOT_FOUND,
                    Boolean.FALSE);
            return resultObject;
        }

    }

    @ApiIgnore
    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Integer id, @RequestBody User user) {
        User u = users.get(id);
        u.setUsername(user.getUsername());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiIgnore
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}