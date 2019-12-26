package quick.boot.drools.entity;

import lombok.Data;

@Data
public class AddressCheckResult {

    /**
     * true:通过校验；false：未通过校验.
     */
    private boolean postCodeResult = false;

}