package com.scaler.blogapp.about;

import com.scaler.blogapp.users.dtos.UserResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/about")
public class AboutController {

    @GetMapping("")
    String about() {
        return "This is a demo application for Spring Security";
    }

    @GetMapping("/private")
    String privateAbout(@AuthenticationPrincipal UserResponseDto user) {
        var username = user.getUsername();
        return "This is private about information for logged in users only" +
                " you are viewing this as " + username;
    }
}

