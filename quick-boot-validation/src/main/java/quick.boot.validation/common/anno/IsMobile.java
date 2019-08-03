package quick.boot.validation.common.anno;

import quick.boot.validation.common.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
    validatedBy = {MobileValidator.class}
)
public @interface IsMobile {

  boolean required() default true;

  String message() default "手机号格式不正确";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

