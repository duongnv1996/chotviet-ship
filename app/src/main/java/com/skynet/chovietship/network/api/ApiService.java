package com.skynet.chovietship.network.api;


import com.google.gson.JsonObject;
import com.skynet.chovietship.models.Address;
import com.skynet.chovietship.models.AddressGeocoding;
import com.skynet.chovietship.models.Auction;
import com.skynet.chovietship.models.Cart;
import com.skynet.chovietship.models.Category;
import com.skynet.chovietship.models.ChatItem;
import com.skynet.chovietship.models.Combo;
import com.skynet.chovietship.models.FavouriteItem;
import com.skynet.chovietship.models.History;
import com.skynet.chovietship.models.HomeResponse;
import com.skynet.chovietship.models.Market;
import com.skynet.chovietship.models.Message;
import com.skynet.chovietship.models.MyPlace;
import com.skynet.chovietship.models.Nearby;
import com.skynet.chovietship.models.Notification;
import com.skynet.chovietship.models.PlaceNearby;
import com.skynet.chovietship.models.Product;
import com.skynet.chovietship.models.ProductResponse;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.models.Promotion;
import com.skynet.chovietship.models.Routes;
import com.skynet.chovietship.models.Shop;
import com.skynet.chovietship.models.ShopDetail;
import com.skynet.chovietship.models.ShopResponse;
import com.skynet.chovietship.models.Term;
import com.skynet.chovietship.models.Wallet;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by thaopt on 9/6/17.
 */

public interface ApiService {
    public static String API_ROOT = "http://hbvn.vn/api/";

    @GET("directions/json")
    Call<ApiResponse<List<Routes>>> getDirection(
            @Query("origin") String orgin
            , @Query("destination") String destination
            , @Query("key") String key);

    @GET("place/autocomplete/json")
    Call<ApiResponse<List<MyPlace>>> getAddress(@Query("input") String input
            , @Query("types") String type
            , @Query("strictbounds") boolean strictbounds
            , @Query("location") String location
            , @Query("radius") int radius
            , @Query("key") String key);

    @GET("geocode/json")
    Call<ApiResponse<List<PlaceNearby>>> getLocation(
            @Query("address") String location
            , @Query("sensor") boolean sensor
            , @Query("key") String key
    );

    @GET("place/nearbysearch/json")
    Call<ApiResponse<List<PlaceNearby>>> getNearby(
            @Query("location") String location
            , @Query("radius") int radius
            , @Query("type") String type
            , @Query("limit") int limit
            , @Query("key") String key);
    @GET("geocode/json")
    Call<ApiResponseGeoCoding<List<AddressGeocoding>>> getAddress(
            @Query("latlng") String location
            , @Query("key") String key);

    @GET("place/nearbysearch/json")
    Call<JsonObject> getNearbyJson(
            @Query("location") String location
            , @Query("radius") int radius
            , @Query("type") String type
            , @Query("limit") int limit
            , @Query("key") String key);

    @GET("get_info.php")
    Call<ApiResponse<Profile>> getProfile(@Query("id") String uid, @Query("type") int type);

    @GET("home.php")
    Call<ApiResponse<HomeResponse>> getHome(@Query("user_id") String uid);

    @GET("login.php")
    Call<ApiResponse<Profile>> login(@Query("phone") String uid, @Query("password") String password, @Query("type") int type);

    @GET("promotion.php")
    Call<ApiResponse<List<Promotion>>> getListNotification(@Query("user_id") String uid, @Query("type") int type);

    @GET("notification.php")
    Call<ApiResponse<List<Notification>>> getNotification(@Query("id") String uid, @Query("type") int type);

    @GET("auto_noty.php")
    Call<ApiResponse<List<Notification>>> getNotificationDaily();

    @GET("category.php")
    Call<ApiResponse<List<Category>>> getListCategory();

    @GET("list_friend.php")
    Call<ApiResponse<List<Shop>>> getListFriend(@Query("user_id") String uid);

    @GET("list_shop.php")
    Call<ApiResponse<ShopResponse>> getListShop(@Query("user_id") String uid, @Query("category_id") int category_id);

    @GET("get_product.php")
    Call<ApiResponse<Nearby>> getNearbyProduct(@Query("user_id") String uid, @Query("category_id") int category_id, @Query("index") int index, @Query("lat") double lat, @Query("lng") double lng, @Query("market_id") int  idMarket);

    @GET("shop_nearby.php")
    Call<ApiResponse<List<Shop>>> getListShopNearby(@Query("lat") double lat, @Query("lng") double lng);

    @GET("shop_detail.php")
    Call<ApiResponse<ShopDetail>> getDetailShop(@Query("user_id") String uid, @Query("shop_id") String shop_id);
    @FormUrlEncoded
    @POST("scan_qr.php")
    Call<ApiResponse> scanShop(@Field("user_id") String uid, @Field("shop_id") String shop_id);

    @FormUrlEncoded
    @POST("set_price.php")
    Call<ApiResponse> setPriceAuction(@Field("user_id") String uid, @Field("auction_id") int auction_id,@Field("price") double price);

    @GET("product_detail.php")
    Call<ApiResponse<Product>> getDetailProduct(@Query("user_id") String uid, @Query("product_id") int shop_id);

    @GET("promotion_detail.php")
    Call<ApiResponse<Promotion>> getDetailNotification(@Query("promotion_id") String id, @Query("type") int type, @Query("user_id") String shID);

    @GET("notification_detail.php")
    Call<ApiResponse<Notification>> getDetailNotifications(@Query("id") String id, @Query("type") int type, @Query("user_id") String shID);

    @FormUrlEncoded
    @POST("favourite_shop.php")
    Call<ApiResponse> toggleFavShop(@Field("user_id") String idUser, @Field("shop_id") int shop_id, @Field("type") int isFav);

    @FormUrlEncoded
    @POST("favourite_product.php")
    Call<ApiResponse> toggleFavProduct(@Field("user_id") String idUser, @Field("product_id") int shop_id, @Field("type") int isFav);

    @FormUrlEncoded
    @POST("feedback.php")
    Call<ApiResponse> feedback(@Field("user_id") String idUser, @Field("name") String name, @Field("phone") String phone, @Field("email") String email, @Field("content") String content);

    @FormUrlEncoded
    @POST("receive_booking.php")
    Call<ApiResponse> acceptBooking(@Field("shiper_id") String shiper_id, @Field("booking_id") int booking_id);

    @FormUrlEncoded
    @POST("forget_password.php")
    Call<ApiResponse> forgotPassword(@Field("phone") String phone, @Field("type") int type);

    @GET("verify_code.php")
    Call<ApiResponse<String>> sendCode(@Query("phone") String phone, @Query("type") int type);

    @GET("list_product.php")
    Call<ApiResponse<ProductResponse>> getListProduct(@Query("user_id") String user_id, @Query("index") int type, @Query("id_market") int id_market);

    @GET("auction.php")
    Call<ApiResponse<List<Auction>>> getListAuction(@Query("user_id") String user_id);

    @GET("list_combo.php")
    Call<ApiResponse<List<Combo>>> getListCombo(@Query("user_id") String user_id);

    @GET("city.php")
    Call<ApiResponse<List<Address>>> getListCity();
    @GET("district.php")
    Call<ApiResponse<List<Address>>> getListDistrict(@Query("city_id") int id);

    @GET("combo_detail.php")
    Call<ApiResponse<Combo>> getComboDetail(@Query("combo_id") int user_id);

    @GET("market_nearby.php")
    Call<ApiResponse<List<Market>>> getListMarket(@Query("lat") double user_id, @Query("lng") double type);

    @GET("list_favourite.php")
    Call<ApiResponse<FavouriteItem>> getListFavourite(@Query("user_id") String user_id);

    @GET("product_category.php")
    Call<ApiResponse<ProductResponse>> getListProductCategory(@Query("user_id") String user_id, @Query("index") int index, @Query("category_id") int category_id);

    @GET("search_product.php")
    Call<ApiResponse<ProductResponse>> searchProduct(@Query("user_id") String user_id, @Query("index") int index, @Query("key") String category_id);

    @GET("history_shiper.php")
    Call<ApiResponse<List<History>>> getHistoryShiper(@Query("shiper_id") String user_id);

    @GET("verify_booking.php")
    Call<ApiResponse<String>> sendCodeBooking(@Query("phone") String phone);

    @FormUrlEncoded
    @POST("booking.php")
    Call<ApiResponse> booking(@Field("user_id") String phone, @Field("payment") int typePayment);

    @FormUrlEncoded
    @POST("register_shiper.php")
    Call<ApiResponse<Profile>> signUp(@Field("phone") String phone, @Field("password") String password);

    @Multipart
    @POST("update_profile_shiper.php")
    Call<ApiResponse> uploadProfile(@Part MultipartBody.Part image, @PartMap() Map<String, okhttp3.RequestBody> partMap);

    @FormUrlEncoded
    @POST("delete_product_cart.php")
    Call<ApiResponse<Cart>> deteleItemFromCart(@Field("user_id") String user_id, @Field("product_id") int product_id);

    @GET("check_promotion.php")
    Call<ApiResponse<Cart>> addPromoToCart(@Query("user_id") String user_id, @Query("code") String code);

    @FormUrlEncoded
    @POST("edit_product_cart.php")
    Call<ApiResponse<Cart>> updateItemFromCart(@Field("user_id") String user_id, @Field("product_id") int product_id, @Field("number") int number, @Field("note") String note);

    @FormUrlEncoded
    @POST("add_cart.php")
    Call<ApiResponse<Cart>> addToCart(@Field("user_id") String idUser, @Field("product_id") int productId, @Field("number") int number, @Field("note") String note);

    @FormUrlEncoded
    @POST("update_info.php")
    Call<ApiResponse> updateInforCart(@Field("user_id") String idUser, @Field("name") String name, @Field("phone") String phone,
                                      @Field("email") String email, @Field("address") String address);

    @FormUrlEncoded
    @POST("update_time_booking.php")
    Call<ApiResponse> updateTimeShip(@Field("user_id") String idUser, @Field("time_ship") String name);

    @GET("cart_info.php")
    Call<ApiResponse<Cart>> getCart(@Query("user_id") String idUser);

    @GET("booking_detail.php")
    Call<ApiResponse<History>> getHistory(@Query("id") int idUser);

    @FormUrlEncoded
    @POST("status_booking.php")
    Call<ApiResponse> cancelBooking(@Field("id") int idHis, @Field("active") int active);

    @GET("term.php")
    Call<ApiResponse<Term>> getTerm();

    @GET("list_chat.php")
    Call<ApiResponse<List<ChatItem>>> getListChat(@Query("id") String phone, @Query("type") int type);

    @GET("privacy.php")
    Call<ApiResponse<Term>> getPrivacy();

    @Multipart
    @POST("update_profile.php")
    Call<ApiResponse> updateInfor(@PartMap() Map<String, okhttp3.RequestBody> partMap);


    @Multipart
    @POST("upload_file.php")
    Call<ApiResponse<Message>> uploadFile(@Part("user_id") RequestBody user_id, @Part("chat_id") RequestBody chat_id, @Part("type") RequestBody type, @Part MultipartBody.Part listFile);

    @Multipart
    @POST("upload_image.php")
    Call<ApiResponse<List<Message>>> uploadImages(@Part("user_id") RequestBody user_id, @Part("chat_id") RequestBody chat_id, @Part("type") RequestBody type, @Part List<MultipartBody.Part> listFile);

    @Multipart
    @POST("update_avatar.php")
    Call<ApiResponse<String>> uploadAvatar(@Part MultipartBody.Part image, @PartMap() Map<String, RequestBody> partMap);

    @FormUrlEncoded
    @POST("update_fcm.php")
    Call<ApiResponse> updateFCM(@Field("id") String u_id, @Field("token_fcm") String tokenFCM, @Field("type") int type);

    @FormUrlEncoded
    @POST("update_latlng.php")
    Call<ApiResponse> updateLatlng(@Field("shiper_id") String u_id, @Field("lat") double lat, @Field("lng") double lng);

    @GET("content_chat.php")
    Call<ApiResponse<ChatItem>> getListMessageBetween(@Query("user_id") int uiId, @Query("shop_id") int id_host, @Query("type") int type);
    @GET("list_imei.php")
    Call<ApiResponse<List<Profile>>> getListImei(@Query("tracking_id") String uiId, @Query("type") int type);

    @FormUrlEncoded
    @POST("chat.php")
    Call<ApiResponse<Message>> sendMessageTo(@Field("id_post") int id_post, @Field("user_id") int idUser,
                                             @Field("shop_id") int idShop, @Field("time") String time,
                                             @Field("content") String content, @Field("type") int typeUser,
                                             @Field("attach") int attach);

    @GET("wallet.php")
    Call<ApiResponse<Wallet>> getWallet(@Query("shiper_id") String id);
}
