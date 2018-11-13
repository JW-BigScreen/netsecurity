package com.uestc.netsecurity.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uestc.netsecurity.entry.Material;
import com.uestc.netsecurity.mapper.MaterialMapper;
import com.uestc.netsecurity.service.IMaterialService;
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
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material>
        implements IMaterialService {


}

