package ccc.xxx.user.mapper;

import ccc.xxx.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;


@Mapper
public interface UserMapper {

    Collection<User> list();

    User findById(Integer uid);
}
