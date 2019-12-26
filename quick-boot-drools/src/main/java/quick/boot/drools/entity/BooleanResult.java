package quick.boot.drools.entity;

import lombok.Data;

@Data
public class BooleanResult {

    /**
     * true:通过校验；false：未通过校验.
     */
    private boolean postCodeResult = false;

}