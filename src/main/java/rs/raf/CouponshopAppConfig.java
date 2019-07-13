package rs.raf;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.boot.ApplicationBinder;

import javax.ws.rs.ApplicationPath;
import java.util.TimeZone;

@ApplicationPath("/api")
class CouponshopAppConfig extends ResourceConfig {

    public CouponshopAppConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC +2"));

        packages("rs.raf");
        register(new ApplicationBinder());
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

    }
}
