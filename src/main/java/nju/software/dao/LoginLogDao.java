package nju.software.dao;

import nju.software.domain.LoginLog;
import nju.software.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;
    private final static String INSERT_LOGIN_LOG_SQL = "INSERT INTO t_login_log(user_id,ip,login_datatime) VALUES(?,?,?)";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertLoginLog(LoginLog loginLog){
        Object[] args = {loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()};
        if (DBUtil.insert(INSERT_LOGIN_LOG_SQL,false,loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate())==1)
            System.out.println("insert success");
        //为什么jdbcTemple的update操作不生效
       // jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,args);
    }


}
