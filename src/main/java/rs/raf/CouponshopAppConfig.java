package rs.raf;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.boot.ApplicationBinder;

import javax.ws.rs.ApplicationPath;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;

@ApplicationPath("/api")
class CouponshopAppConfig extends ResourceConfig {

    public CouponshopAppConfig() {

        packages("rs.raf");
        register(new ApplicationBinder());
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

    }
}
