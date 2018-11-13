package com.uestc.netsecurity.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uestc.netsecurity.entry.Dictionary;
import com.uestc.netsecurity.entry.Indicator;
import com.uestc.netsecurity.mapper.DictionaryMapper;
import com.uestc.netsecurity.mapper.IndicatorMapper;
import com.uestc.netsecurity.service.IDictionaryService;
import com.uestc.netsecurity.service.IIndicatorService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lqh
 * @since 2018-05-25
 */
@Service
public class IndicatorServiceImpl extends ServiceImpl<IndicatorMapper, Indicator>
        implements IIndicatorService {


}

