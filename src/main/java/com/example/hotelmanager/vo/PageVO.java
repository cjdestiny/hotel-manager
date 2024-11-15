package com.example.hotelmanager.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
//分页返回结果对象
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageVO<T>{
    private Integer page;
    private Integer pageSize;
    private Long total;
    private List<T> itemList;
}
