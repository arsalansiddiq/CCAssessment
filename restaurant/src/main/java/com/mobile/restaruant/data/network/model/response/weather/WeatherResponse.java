
package com.mobile.restaruant.data.network.model.response.weather;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class WeatherResponse {

    @Expose
    private City city;
    @Expose
    private Long cnt;
    @Expose
    private String cod;
    @Expose
    private java.util.List<com.mobile.restaruant.data.network.model.response.weather.List> list;
    @Expose
    private Double message;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getCnt() {
        return cnt;
    }

    public void setCnt(Long cnt) {
        this.cnt = cnt;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public java.util.List<com.mobile.restaruant.data.network.model.response.weather.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.mobile.restaruant.data.network.model.response.weather.List> list) {
        this.list = list;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

}
