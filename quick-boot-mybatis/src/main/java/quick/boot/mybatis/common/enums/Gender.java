package quick.boot.mybatis.common.enums;

public enum Gender implements DescEnum, CodeEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer code;

    private String desc;

    Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String desc() {
        return desc;
    }

    @Override
    public int code() {
        return code;
    }
}
