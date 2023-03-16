package com.cook.baolema.dao;

import com.cook.baolema.pojo.Category;
import com.cook.baolema.pojo.Dish;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 徐亮
 */
@Mapper
public interface CategoryDao {
    /*
    查询所有菜品类别
    * */
    @Select("select * from tb_category")
    List<Category> selectAll();

    /*
    按id查询菜品类别
     */
    @Select("select * from tb_category where categoryID=#{id}")
    Category selectByID(Integer id);

    /*
    增加or保存菜品类别
    * */
    @Insert("insert into tb_category values (null,#{category},#{description},#{createdTime})")
    int save(Category newCategory);

    /*
    * 修改菜品类别信息
    * */
    @Update("update tb_category set category=#{category},description=#{description},createdTime=#{createdTime} where categoryID=#{categoryID}")
    int update(Category newCategory);

    /*
    * 删除菜品类别
    * */
    @Delete("delete from tb_category where categoryID=#{id}")
    int deleteByID(Integer id);


    /*
    * 查询所有类别id*/

    @Select("select categoryID from tb_category")
    List<Integer> selectAllIDs();
}
