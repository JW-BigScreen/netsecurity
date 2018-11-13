package com.uestc.netsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uestc.netsecurity.entry.Alarm;
import com.uestc.netsecurity.entry.Indicator;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorMapper extends BaseMapper<Indicator> {

}
