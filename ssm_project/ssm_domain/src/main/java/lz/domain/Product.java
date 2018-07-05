package lz.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    /*
    id varchar2(32) default SYS_GUID() PRIMARY KEY,
  productNum VARCHAR2(50) NOT NULL,
  productName VARCHAR2(50),
  cityName VARCHAR2(50),
  DepartureTime timestamp,
  productPrice Number,
  productDesc VARCHAR2(500),
  productStatus INT,
     */
    private String id;
    private String productNum;
    private String productName;
    private String cityName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date DepartureTime;
    /*private String DepartureTimeStr;

    public String getDepartureTimeStr() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(DepartureTime);
        return format;
    }*/

    private Double productPrice;
    private String productDesc;
    private Integer productStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(Date departureTime) {
        DepartureTime = departureTime;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
}
