package com.skaz.dao.user;

import com.skaz.entity.User;
import com.skaz.jpa.BaseDao;
import com.skaz.utils.Maps;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author jungle
 */
@Repository
public class UserDao extends BaseDao<User> {

    public User getUserByUserName(String username){
        String sql = "select  * from sys_user where username = :userName ";
        Map<String, Object> filter = Maps.newHashMap();
        filter.put("userName",username);
        return getBySql(sql,filter);
    }
}
