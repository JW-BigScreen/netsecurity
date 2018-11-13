package com.uestc.netsecurity.service.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uestc.netsecurity.entry.WorkOrder;
import com.uestc.netsecurity.mapper.WorkOrderMapper;
import com.uestc.netsecurity.service.IWorkOrderService;
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
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder>
        implements IWorkOrderService {


}

