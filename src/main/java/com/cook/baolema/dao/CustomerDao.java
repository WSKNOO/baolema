package com.cook.baolema.dao;

import com.cook.baolema.pojo.Customer;
import com.cook.baolema.pojo.Manager;
import com.cook.baolema.respdata.RankAndNumber;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerDao {
    @Select("select * from tb_customer")
    List<Customer> selectAll();

    @Select("select * from tb_customer where customerID=#{id}")
    Customer selectByID(Integer id);

    @Insert("insert into tb_customer values (null,#{customer},#{gender},#{profilePhoto},#{phoneNumber},#{password},#{accumulatedAmount},#{createdTime},#{rank})")
    int save(Customer c);

    @Update("update tb_customer set customer=#{customer},gender=#{gender},profilePhoto=#{profilePhoto},phoneNumber=#{phoneNumber},password=#{password},accumulatedAmount=#{accumulatedAmount},createdTime=#{createdTime} where customerID=#{customerID}")
    int update(Customer newCustomer);

    @Delete("delete from tb_customer where customerID=#{id}")
    int deleteByID(Integer id);

    @Select("select * from tb_customer where phoneNumber=#{phoneNumber} and password=#{password}")
    Customer selectByPhoneAndPwd(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    /**
     * 按手机号查询
     */
    @Select("select * from tb_customer where phoneNumber=#{phoneNumber}")
    Customer selectByPhoneNumber(String phoneNumber);

    @Update("update tb_customer set password=#{password} where customerID=#{customerID}")
    int updatePwd(String password, Integer customerID);

    /*
     * 根据用户id更新会员等级
     */

    @Update("update tb_customer set rank=#{rank} where customerID=#{customerID}")
    int updateRankByCustomerID(Integer customerID, Integer rank);

    /**
     * 根据更新用户累计金额
     */

    @Update("update tb_customer set accumulatedAmount = accumulatedAmount + #{amount} where customerID=#{customerID}")
    int updateAccumulatedAmountByCustomerID(Integer customerID, Float amount);

    @Select("select rank,count(*) as number from tb_customer group by rank order by rank")
     List<RankAndNumber> selectRank();

    @Select("select * from tb_customer where rank=#{rank}")
    List<Customer> selectByRank(int rank);
}
