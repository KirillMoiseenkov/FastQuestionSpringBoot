package CreationShip.demo.service;

import CreationShip.demo.models.user.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetTokenServiceImpl implements GetTokenSerivce
{

    @Autowired
    private UserService userService;

    @Override
    public String getToken(String username, String password)
    {

        String token = null;
        User user = this.userService.getUserByUsername(username);

        Map<String, Object> tokenData = new HashMap<>();

        if(username == null || password == null)
            return null;

        if(password.equals(user.getPassword()))
        {
            tokenData.put("username", user.getUsername());
            tokenData.put("role_id", user.getRole_id());
            tokenData.put("id", user.getId());

            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setClaims(tokenData);

            String key = "ifngj44i435345dnfign4954f";

            return jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
        }

        return null;
    }

}
