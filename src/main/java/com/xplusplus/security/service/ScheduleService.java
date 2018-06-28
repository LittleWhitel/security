package com.xplusplus.security.service;

import com.xplusplus.security.domain.LateType;
import com.xplusplus.security.domain.Schedule;
import com.xplusplus.security.domain.ScheduleType;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.AttendanceGroupRepository;
//import com.xplusplus.security.repository.ScheduleLateTypeRepository;
import com.xplusplus.security.repository.LateTypeRepository;
import com.xplusplus.security.repository.ScheduleRepository;
import com.xplusplus.security.repository.ScheduleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：XudongHu
 * @description: 班次Service
 * @date:20:34 2018/5/28
 */
@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AttendanceGroupRepository attendanceGroupRepository;

    @Autowired
    private ScheduleTypeRepository scheduleTypeRepository;

    @Autowired
    private LateTypeRepository lateTypeRepository;

    /**
     * 新增班次
     */
    public Schedule save(Schedule schedule, Integer[] scheduleTypeIds, Integer[] lateTypeIds) {
        // 验证是否存在
        if (schedule == null || (schedule.getId() != null && scheduleRepository.findOne(schedule.getId()) != null)) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        // 班次类型
        for (int scheduleTypeId : scheduleTypeIds) {
            ScheduleType scheduleType = scheduleTypeRepository.findOne(scheduleTypeId);
            if (scheduleType == null) {
                EnumExceptions.ADD_FAILED_SCHEDULE_TYPE_NOT_EXIST.setMessage("新增失败， 班次类型" + scheduleTypeId + "不存在");
                throw new SecurityExceptions(EnumExceptions.ADD_FAILED_SCHEDULE_TYPE_NOT_EXIST);
            }
            schedule.getScheduleTypes().add(scheduleType);
        }

        // 迟到类型
        for (int lateTypeId : lateTypeIds) {
            LateType lateType = lateTypeRepository.findOne(lateTypeId);
            if (lateType == null) {
                EnumExceptions.ADD_FAILED_LATE_TYPE_NOT_EXIST.setMessage("新增失败， 迟到类型" + lateTypeId + "不存在");
                throw new SecurityExceptions(EnumExceptions.ADD_FAILED_LATE_TYPE_NOT_EXIST);
            }
            schedule.getLateTypes().add(lateType);
        }

        return scheduleRepository.save(schedule);
    }

    /**
     * 更新班次
     */
    public Schedule update(Schedule schedule, Integer[] scheduleTypeIds, Integer[] lateTypeIds) {
        //验证是否存在
        if (schedule == null || schedule.getId() == null
                || scheduleRepository.findOne(schedule.getId()) == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }
        //是否被考勤组使用中,使用中的班次修改需要。？
        return scheduleRepository.save(schedule);
    }

    /**
     * 删除班次
     */
    public void delete(Integer id) {

        //验证是否存在
        if (scheduleRepository.findOne(id) == null) {
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        //验证是否被考勤组使用
        Schedule schedule = new Schedule();
        schedule.setId(id);
        if (attendanceGroupRepository.findFirstBySchedule(schedule) != null) {
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_USED);
        }
        /*验证是否被班次迟到类型使用
        ScheduleLateType scheduleLateType = new ScheduleLateType();
        scheduleLateType.setId(id);
        if(scheduleLateTypeRepository.findFirstByScheduleLateType(schedule)!=null){
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_USED);
        } */
        scheduleRepository.delete(id);
    }


    /**
     * 通过id查询
     */
    public Schedule findOne(Integer id) {
        return scheduleRepository.findOne(id);
    }

    /**
     * 查询所有
     */
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<Schedule> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
        try {
            Schedule.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            //如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return scheduleRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊分页查询
     */

    public Page<Schedule> findByNameLikeByPage(String name, Integer page, Integer size,
                                               String sortFieldName, Integer asc) {

        //判断排序字段名是否存在
        try {
            Schedule.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            //如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return scheduleRepository.findByNameLike("%" + name + "%", pageable);
    }
}
