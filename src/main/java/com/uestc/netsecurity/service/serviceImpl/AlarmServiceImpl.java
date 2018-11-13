package com.uestc.netsecurity.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uestc.netsecurity.entry.Alarm;
import com.uestc.netsecurity.mapper.AlarmMapper;
import com.uestc.netsecurity.service.IAlarmService;
import org.springframework.stereotype.Service;

/**
 * @author lqh
 * @since 2018-05-25
 */
@Service
public class AlarmServiceImpl extends ServiceImpl<AlarmMapper, Alarm>
        implements IAlarmService {


}

