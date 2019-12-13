package com.example.demo.validate.dto;

import com.example.demo.validate.annotation.IdCard;
import com.example.demo.validate.groups.Create;
import com.example.demo.validate.groups.Update;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * @author yao
 * @date 2019/12/13
 */
@Getter
@Setter
@ToString
public class UserDTO {

    @NotNull(message = "身份证不能为空", groups = Create.class)
    @IdCard(message = "身份证不合法", groups = {Create.class})
    private String idCard;

    @NotBlank(message = "用户名不能为空", groups = Update.class)
    @NotNull(message = "用户名不能为空", groups = Update.class)
    private String userName;

    @NotBlank(message = "手机号不能为空", groups = Update.class)
    @NotNull(message = "手机号不能为空", groups = Update.class)
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式错误", groups = Update.class)
    @Max(value = 11, message = "手机号只能为{max}位", groups = Update.class)
    @Min(value = 11, message = "手机号只能为{min}位", groups = Update.class)
    private String mobileNo;

    private Integer sex;

    private Integer age;

    @Email(message = "邮箱格式错误", groups = Update.class)
    private String email;
}
