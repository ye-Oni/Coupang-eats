package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.src.store.model.GetAllStoreRes;
import com.example.demo.src.store.model.GetCategoryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class StoreProvider {
    private final StoreDao storeDao;

    @Autowired
    public StoreProvider(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public List<GetAllStoreRes> getStores() throws BaseException {
        try {
            List<GetAllStoreRes> getAllStoreRes = storeDao.getStores();

            return getAllStoreRes;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetAllStoreRes getStore(int storeID) throws BaseException {
        try {
            GetAllStoreRes getAllStoreRes = storeDao.getStore(storeID);
            return getAllStoreRes;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 전체 카테고리 조회
    public List<GetCategoryRes> getCategories() throws BaseException {
        try {
            List<GetCategoryRes> getCategoryRes = storeDao.getCategories();
            return getCategoryRes;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 특정 카테고리 조회
    public GetCategoryRes getCategory(int categoryID) throws BaseException {
        try {
            GetCategoryRes getCategoryRes = storeDao.getCategory(categoryID);
            return getCategoryRes;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
