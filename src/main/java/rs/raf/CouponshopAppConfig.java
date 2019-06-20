package rs.raf;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
class CouponshopAppConfig extends ResourceConfig {

    public CouponshopAppConfig() {
        packages("rs.raf");
    }
}
