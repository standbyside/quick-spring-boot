package quick.boot.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import quick.boot.mybatis.common.enums.Gender;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_student")
public class Student {

    @TableId
    private Long id;

    private Integer age;

    private String name;

    private Gender gender;
}
