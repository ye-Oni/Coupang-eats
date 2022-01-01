package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor

public class GetCategoryRes {
    private int categoryID;
    private String categoryName;
    private String imgUrl;
}
