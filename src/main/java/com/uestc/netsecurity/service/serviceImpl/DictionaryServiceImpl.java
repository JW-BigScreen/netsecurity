package com.uestc.netsecurity.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uestc.netsecurity.entry.Dictionary;
import com.uestc.netsecurity.entry.Student;
import com.uestc.netsecurity.mapper.DictionaryMapper;
import com.uestc.netsecurity.mapper.StudentMapper;
import com.uestc.netsecurity.service.IDictionaryService;
import com.uestc.netsecurity.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lqh
 * @since 2018-05-25
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary>
        implements IDictionaryService {


}

