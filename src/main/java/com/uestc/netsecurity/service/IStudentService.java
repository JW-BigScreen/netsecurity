package com.uestc.netsecurity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uestc.netsecurity.entry.Student;
import com.uestc.netsecurity.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lqh
 * @since 2018-05-25
 */
public interface IStudentService extends IService<Student> {

    List<Student> getList();

}

