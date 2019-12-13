package com.example.demo.validate.controller;

import com.example.demo.validate.dto.UserDTO;
import com.example.demo.validate.groups.Create;
import com.example.demo.validate.model.ResultVO;
import com.example.demo.validate.service.UserService;
import com.example.demo.validate.service.UserServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yao
 * @date 2019/12/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/registry")
    public ResultVO registryUser(@Validated(Create.class) UserDTO userDTO) {
        UserService userService = new UserServiceImpl();
        return userService.registryUser(userDTO);
    }
}
