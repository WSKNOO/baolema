package com.cook.baolema.dao;

import com.cook.baolema.pojo.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderInfoDao {
    @Select("select * from tb_order")
    List<OrderInfo> selectAll();

    @Select("select * from tb_order where status=#{stat} order by createdTime limit #{n}")
    List<OrderInfo> selectLimit(short stat, Integer n);

    @Select("select * from tb_order where orderID=#{id}")
    OrderInfo selectByID(Integer id);

    //insert into tb_order values (null,5,0,null,73.5,CURRENT_DATE,null,'1324',5)
    //insert into tb_order(orderID,customerID,`status`,userRatings,totalAmount,createdTime,chefID,uuid,grade) values (null,5,0,null,73.5,CURRENT_DATE,null,'132d4',5)
    @Insert("insert into tb_order(orderID,customerID,status,userRatings,totalAmount,createdTime,chefID,uuid,grade) values (null,#{customerID},#{status},null,#{totalAmount},#{createdTime},#{chefID},#{uuid},#{grade})")
    int save(OrderInfo newOrderInfo);

    @Update("update tb_order set customerID=#{customerID},status=#{status},userRatings=#{userRatings},totalAmount=#{totalAmount},createdTime=#{createdTime},chefID=#{chefID},grade=#{grade} where orderID=#{orderID}")
    int update(OrderInfo newOrderInfo);

    //修改订单评分
    @Update("update tb_order set grade=#{grade} where orderID=#{orderID}")
    int updateGrade(Integer orderID, Integer grade);

    @Delete("delete from tb_order where orderID=#{id}")
    int deleteByID(Integer id);


    @Select("select orderID from tb_order WHERE customerID=#{customerID} and status=0")
    Integer checkOrderID(Integer customerID);

    //修改订单状态
    @Update("update tb_order set status=#{status} where orderID=#{orderID}")
    int updateStatusByOrderID(Integer orderID, short status);

    @Select("select * from tb_order where customerID=#{customerID}")
    List<OrderInfo> selectHistoryOrder(Integer customerID);

    @Select("select orderID from tb_order where uuid=#{uuid}")
    Integer checkOrderIDByuuid(String uuid);

}
