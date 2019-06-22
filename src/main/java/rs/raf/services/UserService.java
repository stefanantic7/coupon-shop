package rs.raf.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import rs.raf.dtos.UserDto;
import rs.raf.mappers.UserMapper;
import rs.raf.models.User;
import rs.raf.repositories.user.UserRepository;
import rs.raf.requests.LoginRequest;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public UserDto create(String firstName, String lastName, String privilegeLevel, String username, String password) {
        User user = this.userRepository.create(firstName, lastName, privilegeLevel, username, password);
        return UserMapper.instance.userToUserDto(user);
    }

    public String login(String username, String password) {
        User user = this.userRepository.find(username);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword().toCharArray());
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

    public User parseJwt(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        DecodedJWT jwt = verifier.verify(token);

        return this.userRepository.find(jwt.getClaim("userId").asInt());
    }

    public UserDto find(int id) {
        return UserMapper.instance.userToUserDto(this.userRepository.find(id));
    }

    public UserDto find(String username) {
        return UserMapper.instance.userToUserDto(this.userRepository.find(username));
    }
}
