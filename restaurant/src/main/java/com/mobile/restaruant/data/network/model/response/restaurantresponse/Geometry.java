
package com.mobile.restaruant.data.network.model.response.restaurantresponse;

import com.google.gson.annotations.Expose;

public class Geometry {

    @Expose
    private Location location;
    @Expose
    private Viewport viewport;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

}
