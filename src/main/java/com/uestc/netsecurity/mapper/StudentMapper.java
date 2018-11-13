package com.uestc.netsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uestc.netsecurity.entry.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> getList();
}
