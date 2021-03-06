package com.skynet.chovietship.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profile implements Parcelable {

    @Expose
    @SerializedName("ex")
    private int ex;
    @Expose
    @SerializedName("tylemo")
    private int tylemo;
    @Expose
    @SerializedName("image")
    private int image;
    @Expose
    @SerializedName("online")
    private int online;

    public String getCmnd1() {
        return cmnd1;
    }

    public void setCmnd1(String cmnd1) {
        this.cmnd1 = cmnd1;
    }

    public String getCmnd2() {
        return cmnd2;
    }

    public void setCmnd2(String cmnd2) {
        this.cmnd2 = cmnd2;
    }

    public String getShk() {
        return shk;
    }

    public void setShk(String shk) {
        this.shk = shk;
    }

    public String getGplx1() {
        return gplx1;
    }

    public void setGplx1(String gplx1) {
        this.gplx1 = gplx1;
    }

    public String getGplx2() {
        return gplx2;
    }

    public void setGplx2(String gplx2) {
        this.gplx2 = gplx2;
    }

    public String getDkx() {
        return dkx;
    }

    public void setDkx(String dkx) {
        this.dkx = dkx;
    }

    private boolean isChecked;
    @Expose
    @SerializedName("active")
    private int active;
    @Expose
    @SerializedName("number_booking")
    private int number;
    @Expose
    @SerializedName("number_noty")
    private int noty;
    @Expose
    @SerializedName("number_message")
    private int message;
    @Expose
    @SerializedName("type")
    private int type;
    @Expose
    @SerializedName("is_cart")
    private int is_cart;
    @Expose
    @SerializedName("point")
    private int point;
    @Expose
    @SerializedName("number_post")
    private int number_post;
    @Expose
    @SerializedName("register_date")
    private String register_date;
    @Expose
    @SerializedName("avatar")
    private String avatar;    @Expose
    @SerializedName("imei")
    private String imei;
    @Expose
    @SerializedName("cover")
    private String cover;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getIs_cart() {
        return is_cart;
    }

    public void setIs_cart(int is_cart) {
        this.is_cart = is_cart;
    }

    @Expose
    @SerializedName("achievement")
    private List<String> archiement;
    @Expose
    @SerializedName("type_device")
    private int type_device;
    @Expose
    @SerializedName("account_number")
    private String account_number; @Expose
    @SerializedName("bienso")
    private String bienso; @Expose
    @SerializedName("loaixe")
    private String loaixe;
    @Expose
    @SerializedName("birthday")
    private String birthday;
    @Expose
    @SerializedName("token")
    private String token;
    @Expose
    @SerializedName("type_account")
    private int type_account;

    @Expose
    @SerializedName("mutual_friend")
    private int mutual_friend;
    @Expose
    @SerializedName("is_follow")
    private int is_following;
    @Expose
    @SerializedName("is_friend")
    private int is_friend;
    @Expose
    @SerializedName("height")
    private double height;
    @Expose
    @SerializedName("weight")
    private double weight;
    @Expose
    @SerializedName("lng")
    private double lng;
    @Expose
    @SerializedName("lat")
    private double lat;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName(value = "fullname",alternate = "name")
    private String name;
    @Expose
    @SerializedName("ggid")
    private String ggid;
    @Expose
    @SerializedName("fbid")
    private String fbid;
    @Expose
    @SerializedName("rf_code")
    private String rf_code;
    @Expose
    @SerializedName("code")
    private String code;
    @Expose
    @SerializedName("department_id")
    private String department_id;
    @Expose
    @SerializedName("status")
    private String last_status;
    @Expose
    @SerializedName(value = "_id",alternate = "id")
    private String u_id;
    @Expose
    @SerializedName("account")
    private double accountWallet;
    @Expose
    @SerializedName("list_room")
    private List<String> list_room;


    @Expose
    @SerializedName("cmnd1")
    private String cmnd1;
    @Expose
    @SerializedName("cmnd2")
    private String cmnd2;    @Expose
    @SerializedName("shk")
    private String shk; @Expose
    @SerializedName("gplx1")
    private String gplx1;@Expose
    @SerializedName("gplx2")
    private String gplx2;@Expose
    @SerializedName("dkx")
    private String dkx;

    public String getBienso() {
        return bienso;
    }

    public void setBienso(String bienso) {
        this.bienso = bienso;
    }

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    public String getLast_status() {
        return last_status;
    }

    public void setLast_status(String last_status) {
        this.last_status = last_status;
    }

    public String getId() {
        return u_id;
    }

    public void setId( String u_id) {
        this.u_id = u_id;
    }

    public Profile() {
    }

    public int getIs_following() {
        return is_following;
    }

    public void setIs_following(int is_following) {
        this.is_following = is_following;
    }

    public int getIs_friend() {
        return is_friend;
    }

    public void setIs_friend(int is_friend) {
        this.is_friend = is_friend;
    }

    public int getEx() {
        return ex;
    }

    public void setEx(int ex) {
        this.ex = ex;
    }

    public int getTylemo() {
        return tylemo;
    }

    public void setTylemo(int tylemo) {
        this.tylemo = tylemo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNoty() {
        return noty;
    }

    public int getMutual_friend() {
        return mutual_friend;
    }

    public void setMutual_friend(int mutual_friend) {
        this.mutual_friend = mutual_friend;
    }

    public void setNoty(int noty) {
        this.noty = noty;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getNumber_post() {
        return number_post;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setNumber_post(int number_post) {
        this.number_post = number_post;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getArchiement() {
        return archiement;
    }

    public void setArchiement(List<String> archiement) {
        this.archiement = archiement;
    }

    public int getType_device() {
        return type_device;
    }

    public void setType_device(int type_device) {
        this.type_device = type_device;
    }

    public boolean isChecked() {
        return isChecked;
    }


    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType_account() {
        return type_account;
    }

    public void setType_account(int type_account) {
        this.type_account = type_account;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGgid() {
        return ggid;
    }

    public void setGgid(String ggid) {
        this.ggid = ggid;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getRf_code() {
        return rf_code;
    }

    public void setRf_code(String rf_code) {
        this.rf_code = rf_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public double getAccountWallet() {
        return accountWallet;
    }

    public void setAccountWallet(double accountWallet) {
        this.accountWallet = accountWallet;
    }

    public List<String> getList_room() {
        return list_room;
    }

    public void setList_room(List<String> list_room) {
        this.list_room = list_room;
    }

    public Creator<Profile> getCREATOR() {
        return CREATOR;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ex);
        dest.writeInt(this.tylemo);
        dest.writeInt(this.image);
        dest.writeInt(this.online);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeInt(this.active);
        dest.writeInt(this.number);
        dest.writeInt(this.noty);
        dest.writeInt(this.message);
        dest.writeInt(this.type);
        dest.writeInt(this.is_cart);
        dest.writeInt(this.point);
        dest.writeInt(this.number_post);
        dest.writeString(this.register_date);
        dest.writeString(this.avatar);
        dest.writeString(this.imei);
        dest.writeString(this.cover);
        dest.writeStringList(this.archiement);
        dest.writeInt(this.type_device);
        dest.writeString(this.account_number);
        dest.writeString(this.bienso);
        dest.writeString(this.loaixe);
        dest.writeString(this.birthday);
        dest.writeString(this.token);
        dest.writeInt(this.type_account);
        dest.writeInt(this.mutual_friend);
        dest.writeInt(this.is_following);
        dest.writeInt(this.is_friend);
        dest.writeDouble(this.height);
        dest.writeDouble(this.weight);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.lat);
        dest.writeString(this.password);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeString(this.email);
        dest.writeString(this.name);
        dest.writeString(this.ggid);
        dest.writeString(this.fbid);
        dest.writeString(this.rf_code);
        dest.writeString(this.code);
        dest.writeString(this.department_id);
        dest.writeString(this.last_status);
        dest.writeString(this.u_id);
        dest.writeDouble(this.accountWallet);
        dest.writeStringList(this.list_room);
        dest.writeString(this.cmnd1);
        dest.writeString(this.cmnd2);
        dest.writeString(this.shk);
        dest.writeString(this.gplx1);
        dest.writeString(this.gplx2);
        dest.writeString(this.dkx);
    }

    protected Profile(Parcel in) {
        this.ex = in.readInt();
        this.tylemo = in.readInt();
        this.image = in.readInt();
        this.online = in.readInt();
        this.isChecked = in.readByte() != 0;
        this.active = in.readInt();
        this.number = in.readInt();
        this.noty = in.readInt();
        this.message = in.readInt();
        this.type = in.readInt();
        this.is_cart = in.readInt();
        this.point = in.readInt();
        this.number_post = in.readInt();
        this.register_date = in.readString();
        this.avatar = in.readString();
        this.imei = in.readString();
        this.cover = in.readString();
        this.archiement = in.createStringArrayList();
        this.type_device = in.readInt();
        this.account_number = in.readString();
        this.bienso = in.readString();
        this.loaixe = in.readString();
        this.birthday = in.readString();
        this.token = in.readString();
        this.type_account = in.readInt();
        this.mutual_friend = in.readInt();
        this.is_following = in.readInt();
        this.is_friend = in.readInt();
        this.height = in.readDouble();
        this.weight = in.readDouble();
        this.lng = in.readDouble();
        this.lat = in.readDouble();
        this.password = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.email = in.readString();
        this.name = in.readString();
        this.ggid = in.readString();
        this.fbid = in.readString();
        this.rf_code = in.readString();
        this.code = in.readString();
        this.department_id = in.readString();
        this.last_status = in.readString();
        this.u_id = in.readString();
        this.accountWallet = in.readDouble();
        this.list_room = in.createStringArrayList();
        this.cmnd1 = in.readString();
        this.cmnd2 = in.readString();
        this.shk = in.readString();
        this.gplx1 = in.readString();
        this.gplx2 = in.readString();
        this.dkx = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
