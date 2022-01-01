package com.example.demo.src.store;

import com.example.demo.src.store.model.GetAllStoreRes;
import com.example.demo.src.store.model.GetCategoryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StoreDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetAllStoreRes> getStores() {
        double userLat = 37.47309983000;
        double userLng = 127.14539383300;

        String getStoresQuery = "select Store.storeID as storeID, Store.imgUrl as imgUrl, Store.storeName as storeName, Store.isCheetah as isCheetah, Review.rate, Store.storeLat as storeLat, Store.storeLng as storeLng,(select count(*) from Review where Review.reviewId = Store.reviewID) as reviewNum from Store left outer join Review on Store.reviewID = Review.reviewID";
        return this.jdbcTemplate.query(getStoresQuery,
                (rs, rowNum) -> new GetAllStoreRes(
                        rs.getInt("storeID"),
                        rs.getString("imgUrl"),
                        rs.getString("storeName"),
                        (int)(1000 * Math.round(calcDistance(userLat, userLng, rs.getDouble("storeLat"), rs.getDouble("storeLng"))/2)),
                        (int)(10 + Math.round(calcDistance(userLat, userLng, rs.getDouble("storeLat"), rs.getDouble("storeLng")) * 5)) + "분-" + (int)(20 + Math.round(calcDistance(userLat, userLng, rs.getDouble("storeLat"), rs.getDouble("storeLng")) * 5)) +"분",
                        "10분-20분",
                        rs.getInt("isCheetah"),
                        rs.getDouble("rate"),
                        rs.getInt("reviewNum"),
                        calcDistance(userLat, userLng, rs.getDouble("storeLat"), rs.getDouble("storeLng"))
                )
        );
    }

    public GetAllStoreRes getStore(int storeID) {
        String getStoreQuery = "select * from Store where storeID = ?";
        int getStoreParams = storeID;
        return this.jdbcTemplate.queryForObject(getStoreQuery,
                (rs, rowNum) -> new GetAllStoreRes(
                        rs.getInt("storeID"),
                        rs.getString("imgUrl"),
                        rs.getString("storeName"),
                        0,
                        "17분-27분",
                        "17분-27분",
                        rs.getInt("isCheetah"),
                        5,
                        100,
                        1.4),
                getStoreParams);
    }

    private static double calcDistance(double lat1, double lng1, double lat2, double lng2) {
        double theta = lng1 - lng2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return Math.round(dist*10) /10.0;
    }

    // convert decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // convert radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    // 전체 카테고리 조회
    public List<GetCategoryRes> getCategories() {
        String getCategoriesQuery = "select categoryID, categoryName, imgUrl from FoodCategory"; //User 테이블에 존재하는 모든 회원들의 정보를 조회하는 쿼리
        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("categoryID"),
                        rs.getString("categoryName"),
                        rs.getString("imgUrl"))
        ); // 복수개의 회원정보들을 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보)의 결과 반환(동적쿼리가 아니므로 Parmas부분이 없음)
    }

    // 특정 카테고리 조회
    public GetCategoryRes getCategory(int categoryID) {
        String getCategoryQuery = "select categoryID, categoryName, imgUrl from FoodCategory where categoryID = ?"; // 해당 userIdx를 만족하는 유저를 조회하는 쿼리문
        int getCategoryParams = categoryID;
        return this.jdbcTemplate.queryForObject(getCategoryQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("categoryID"),
                        rs.getString("categoryName"),
                        rs.getString("imgUrl")), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
                getCategoryParams); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }
}
