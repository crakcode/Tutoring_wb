package tutoring.security;

import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.HttpHeaders;


import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/join")
    public String join(@RequestBody User user) {
    	try {
            userRepository.save(User.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(user.getRoles()) // 최초 가입시 USER 로 설정
//                    .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                    .build()).getId();
           return "sucess create user";
    	}catch(Exception e) {
            return "failure create user";
    	}
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User member = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        System.out.println(member.getPassword());
        return jwtTokenProvider.createToken(member.getEmail(), member.getRoles());
    }
    @PostMapping("/hello")
    public String hello(@RequestBody User user) {
        return "hellowolrd";
    }

    @GetMapping("/userinfo")
    public User sendInfo() {
    	User user=userRepository.findById((long) 1).get();
    	return user;
    }
    
    //X-AUTH-TOKEN 을 넣어야 정상적이게 작동한다. 
    @GetMapping("/user/hello")
    public String user(@RequestBody User user,@RequestHeader HttpHeaders headers){
    	System.out.println(headers.toString());
        return "www";
    }

    @GetMapping("/educator/www")
    public String wwww(@RequestBody User user){
        return "www";
    }

}