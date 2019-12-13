package com.example.demo.validate.service;

import com.example.demo.validate.dto.UserDTO;
import com.example.demo.validate.model.ResultVO;

/**
 * @author yao
 * @date 2019/12/13
 */

public interface UserService {

    ResultVO registryUser(UserDTO userDTO);
}
