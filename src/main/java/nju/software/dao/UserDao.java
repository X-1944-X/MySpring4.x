package nju.software.dao;

import nju.software.domain.User;
import nju.software.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

//通过Spring注解定义一个Dao Bean
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL = "SELECT count(*) FROM t_user "
            +" WHERE user_name = ? and password= ? ";
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET "
            +" last_visit=?,last_ip=?,credits=? WHERE user_id = ? ";


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName,String password){

        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,password},Integer.class);
    }

    public User findUserByUserName(final String userName){
        String sqlStr = "SELECT *"
                + " FROM t_user WHERE user_name = ? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userName},
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(resultSet.getInt("credits"));
                        user.setLastVisit(resultSet.getDate("last_visit"));
                        user.setLastIp(resultSet.getString("last_ip"));
                    }
                });
        return user;
    }

    public void updateLoginInfo(User user){
        if (DBUtil.execute(UPDATE_LOGIN_INFO_SQL,user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()))
            System.out.println("update user success");
//        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),
//                user.getLastIp(),user.getCredits(),user.getUserId()});
    }

}
