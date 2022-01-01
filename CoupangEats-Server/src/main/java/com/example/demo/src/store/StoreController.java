package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.GetAllStoreRes;
import com.example.demo.src.store.model.GetCategoryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private final StoreProvider storeProvider;

    public StoreController(StoreProvider storeProvider) {
        this.storeProvider = storeProvider;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetAllStoreRes>> getStores() {
        try {
            List<GetAllStoreRes> getAllStoreRes = storeProvider.getStores();
            return new BaseResponse<>(getAllStoreRes);
        } catch (BaseException exception) {
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{storeID}")
    public BaseResponse<GetAllStoreRes> getStore(@PathVariable("storeID") int storeID) {
        try {
            GetAllStoreRes getAllStoreRes = storeProvider.getStore(storeID);
            return new BaseResponse<>(getAllStoreRes);
        } catch (BaseException exception) {
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /* 카테고리 전체 조회 API */
    @ResponseBody
    @GetMapping("/category")
    public BaseResponse<List<GetCategoryRes>> getCategories() {
        try {
            List<GetCategoryRes> getCategoryRes = storeProvider.getCategories();
            return new BaseResponse<>(getCategoryRes);
        } catch (BaseException exception) {
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /* 특정 카테고리 조회 API */
    @ResponseBody
    @GetMapping("/category/{categoryID}")
    public BaseResponse<GetCategoryRes> getCategory(@PathVariable("categoryID") int categoryID) {
        try {
            GetCategoryRes getCategoryRes = storeProvider.getCategory(categoryID);
            return new BaseResponse<>(getCategoryRes);
        } catch (BaseException exception) {
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
