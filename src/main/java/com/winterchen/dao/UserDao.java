package com.winterchen.dao;


//import com.winterchen.model.Log;
import com.winterchen.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


////定义一个接口，接口中的方法都是抽象的。  dao层一般是连接数据库，操作数据库的数据访问层。（只负责和数据库打交道）

public interface UserDao {


    int insert(UserDomain record);


    int delOne(int id);

    int updateOne(UserDomain user);




    //UserDomain selectOne(UserDomain userDomain);

    UserDomain selectOne(int userId);


    List<UserDomain> selectUsers(UserDomain userDomain);
    Map getPoolAndNormalTotal();
    Double getWithDrawTotal();
    List<Map> getUserTotalByDay(Map map);
    List<Map> getCompleteOrderByMonth(Map para);
    List<Map> getOrderAmountByDay();
    List<Map> getDivideAmountByDay(Map map);
    List<Map> getTeamAvg(Map map);
    List<Map> getTeamOrderTotal();

}