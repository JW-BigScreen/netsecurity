package com.uestc.netsecurity.service.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uestc.netsecurity.entry.Attack;
import com.uestc.netsecurity.mapper.AttackMapper;
import com.uestc.netsecurity.service.IAttackService;
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
public class AttackServiceImpl extends ServiceImpl<AttackMapper, Attack>
        implements IAttackService {


}

