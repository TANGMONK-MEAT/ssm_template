package com.zwl.aop.pojo;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/8 21:08
 */
public class Address {
    private String province;
    private String city;
    private String street;

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


}
