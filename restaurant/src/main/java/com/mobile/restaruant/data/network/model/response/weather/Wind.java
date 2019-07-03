
package com.mobile.restaruant.data.network.model.response.weather;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Wind {

    @Expose
    private Double deg;
    @Expose
    private Double speed;

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

}
