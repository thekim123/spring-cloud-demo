package com.thekim12.firstservice;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/first-service")
@RestController
@Slf4j
public class FirstServiceController {

    Environment env;

    @Autowired
    public FirstServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "First Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello World in First Service.";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        // ServletReq 에서 가져오는 방법
        log.info("Server port = {}", request.getServerPort());

        // 환경변수에 등록되어진 정보 중에서 랜덤하게 할당되어진 로컬 서버 포트를 통해서 가져옴
        return String.format("Hi, there. This is a message from First Server on Port %s"
                    , env.getProperty("local.server.port"));
    }

}


