package com.cook.baolema;

import com.cook.baolema.dao.CategoryDao;
import com.cook.baolema.dao.OrderInfoDao;
import com.cook.baolema.respdata.HourAndOrderNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BaolemaApplicationTests {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Test
    void contextLoads() {
        String[] s = " h i ".split(" ");
        System.out.println(s.length);
        for(int i =0;i<s.length;i++){
            System.out.println("当前字符串："+s[i]);
        }
    }

}
