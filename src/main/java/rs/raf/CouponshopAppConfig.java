package rs.raf;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.glassfish.jersey.server.ResourceConfig;
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
//        String token = "";
//        try {
//            LocalDate date = LocalDate.now().plusDays(1);
//            Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
//            Algorithm algorithm = Algorithm.HMAC256("secrett");
//            token = JWT.create()
//                    .withIssuer("auth0")
//                    .withExpiresAt(new Date(timestamp.getTime()))
//                    .withClaim("userId", 1)
//                    .sign(algorithm);
//            System.out.println(token);
//        } catch (JWTCreationException exception){
//            //Invalid Signing configuration / Couldn't convert Claims.
//            System.out.println("greska");
//        }

        packages("rs.raf");
        register(new ApplicationBinder());
    }
}
