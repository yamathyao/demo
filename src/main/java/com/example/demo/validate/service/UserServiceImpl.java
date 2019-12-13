package com.example.demo.validate.service;

import com.example.demo.validate.dto.UserDTO;
import com.example.demo.validate.model.ResultConst;
import com.example.demo.validate.model.ResultVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yao
 * @date 2019/12/13
 */
@Slf4j
public class UserServiceImpl implements UserService {

    public ResultVO<UserDTO> getUserByName(String userName) {
        return new ResultVO<UserDTO>(ResultConst.RESULT_SUCCESS_CODE, "success", new UserDTO());
    }

    @Override
    public ResultVO registryUser(UserDTO userDTO) {
        log.info("Registry user: " + userDTO.toString());
        return new ResultVO(ResultConst.RESULT_SUCCESS_CODE, "success");
    }
}
