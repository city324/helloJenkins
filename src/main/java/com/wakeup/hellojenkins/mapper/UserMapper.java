package com.wakeup.hellojenkins.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wakeup.hellojenkins.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    Integer insertBatchSomeColumn(List<User> entityList);
}
