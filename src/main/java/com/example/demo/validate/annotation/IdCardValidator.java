package com.example.demo.validate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yao
 * @date 2019/12/13
 */

public class IdCardValidator implements ConstraintValidator<IdCard, Object> {

    public static final Pattern ID_CARD_PATTERN = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");

    @Override
    public void initialize(IdCard constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return IdCardValidator.is18ByteIdCardComplex(value.toString());
    }

    /**
     * 18位身份证校验,比较严格校验
     *
     * @param idCard
     * @return
     * @author lyl
     */
    public static boolean is18ByteIdCardComplex(String idCard) {
        Matcher matcher = ID_CARD_PATTERN.matcher(idCard);
        int[] prefix = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int[] suffix = new int[]{1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        if (matcher.matches()) {
            //用来保存前17位各自乖以加权因子后的总和
            int idCardWiSum = 0;
            for (int i = 0; i < 17; i++) {
                idCardWiSum += Integer.valueOf(idCard.substring(i, i + 1)) * prefix[i];
            }
            //计算出校验码所在数组的位置
            int idCardMod = idCardWiSum % 11;
            //得到最后一位身份证号码
            String idCardLast = idCard.substring(17);

            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if (idCardMod == 2) {
                return "x".equalsIgnoreCase(idCardLast);
            } else {
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                return idCardLast.equals(suffix[idCardMod] + "");
            }
        }
        return false;
    }
}
