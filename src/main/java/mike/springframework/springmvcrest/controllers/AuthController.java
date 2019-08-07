package mike.springframework.springmvcrest.controllers;

import mike.springframework.springmvcrest.domain.JwtUser;
import mike.springframework.springmvcrest.security.JwtGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(AuthController.BASE_URL)
public class AuthController {

    public static final String BASE_URL = "/generate";

    private final JwtGenerator jwtGenerator;
    // Constructor
    public AuthController(JwtGenerator jwtGenerator){
        this.jwtGenerator = jwtGenerator;
    }
    // Route
    @RequestMapping(path = "/token", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> tokener(@RequestBody JwtUser jwtUser) throws GeneralSecurityException, ParseException {

        System.out.println(jwtUser);

        Map<String, String> map = new HashMap<String, String>();
        map.put("token", jwtGenerator.generate(jwtUser));
        return map;
    }

}
