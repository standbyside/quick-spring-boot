package quick.boot.validation.common;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import quick.boot.validation.common.anno.IsMobile;

public class MobileValidator implements ConstraintValidator<IsMobile, String> {

  private static Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");
  private boolean require = false;

  @Override
  public void initialize(IsMobile annotation) {
    require = annotation.required();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext context) {
    if (StringUtils.isEmpty(s)) {
      return !require;
    }
    return MOBILE_PATTERN.matcher(s).matches();
  }
}