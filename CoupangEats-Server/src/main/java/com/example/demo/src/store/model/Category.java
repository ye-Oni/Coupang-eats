package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Category {
    private int categoryID;
    private String categoryName;
    private String imgUrl;
}

