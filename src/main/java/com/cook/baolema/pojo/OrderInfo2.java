package com.cook.baolema.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo2 {
    private Integer orderID;
    private Integer customerID;
    private Integer chefID;
    private Short status;
    private String statusMessage;
    private String userRatings;
    private Float totalAmount;
    private Date createdTime;
}
