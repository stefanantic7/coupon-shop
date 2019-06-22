package rs.raf.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import rs.raf.models.User;
import rs.raf.repositories.users.UserRepository;
import rs.raf.requests.LoginRequest;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public String login(LoginRequest loginRequest) {
        User user = this.userRepository.find(loginRequest.getUsername());
        BCrypt.Result result = BCrypt.verifyer().verify(loginRequest.getPassword().toCharArray(), user.getPassword().toCharArray());
        if(!result.verified) {
            return null;
        }

        LocalDate date = LocalDate.now().plusDays(1);
        Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withIssuer("auth0")
                .withExpiresAt(new Date(timestamp.getTime()))
                .withClaim("userId", user.getId())
                .sign(algorithm);

    }
}
