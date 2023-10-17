package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private Environment env;
    private UserService userService;

    @Autowired
    private Greeting greeting;

    @Autowired
    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        //return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper(); //ModelMapper는 Java 객체 간에 데이터를 복사하거나 매핑하는 데 사용되는 라이브러리

        /**
         * STRICT 매핑 전략은 매우 엄격한 데이터 매핑을 수행한다.
         * 이것은 소스 객체와 대상 객체 간에 필드 이름과 타입이 정확하게 일치해야 함을 의미한다.
         * 즉, 매핑 과정에서 어떤 필드라도 일치하지 않으면 예외가 발생할 수 있다.
         */
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(user, UserDto.class); // map(바꿀 객체, 바꾸고 싶은 클래스 타입)
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        //return "Create user method is called!";
        //return new ResponseEntity(HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
