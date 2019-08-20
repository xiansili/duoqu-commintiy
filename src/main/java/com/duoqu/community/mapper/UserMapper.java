package com.duoqu.community.mapper;

import com.duoqu.community.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int addUser(User user);
}
