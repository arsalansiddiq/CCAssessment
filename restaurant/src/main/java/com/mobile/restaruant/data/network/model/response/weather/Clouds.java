
package com.mobile.restaruant.data.network.model.response.weather;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Clouds {

    @Expose
    private Long all;

    public Long getAll() {
        return all;
    }

    public void setAll(Long all) {
        this.all = all;
    }

}
