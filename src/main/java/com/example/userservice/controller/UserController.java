package com.example.userservice.controller;

import com.example.userservice.controller.jpa.UserEntity;
import com.example.userservice.controller.vo.Greeting;
import com.example.userservice.controller.vo.RequestUser;
import com.example.userservice.controller.dto.UserDto;
import com.example.userservice.controller.vo.ResponseUser;
import com.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private Environment environment;

    @Autowired
    private Greeting greeting;

    @Autowired
    public UserController(UserService userService,  Environment environment) {
        this.userService = userService;
        this.environment = environment;
    }

    @GetMapping("/healthCheck")
    public String status() {
      return String.format("It's working in service"
              +", port(local.server.port)=" + environment.getProperty("local.server.port")
              +", port(server.port)=" + environment.getProperty("server.port")
              +", token secret=" + environment.getProperty("token.secret")
              +", token expiration time=" + environment.getProperty("token.expiration_time")
      );
    };

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    };

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody RequestUser user){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUserList(){
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach( f -> {
            result.add(new ModelMapper().map(f, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId){
        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
