package com.wakeup.hellojenkins.mapper;

//import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wakeup.hellojenkins.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

//    @DS("slaver")
    Integer insertBatchSomeColumn(List<User> entityList);
}
