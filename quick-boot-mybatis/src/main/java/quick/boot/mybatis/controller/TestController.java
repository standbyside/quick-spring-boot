package quick.boot.mybatis.controller;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.mybatis.entity.Student;
import quick.boot.mybatis.common.enums.Gender;
import quick.boot.mybatis.service.TestService;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/batch-insert-students")
    public Object batchInsertStudents() {
        List<Student> students = new ArrayList(1000);
        Faker faker = new Faker(Locale.CHINA);
        for (int i = 0; i < 1000; i++) {
           students.add(
             Student.builder()
                     .name(faker.name().fullName())
                     .age(faker.number().randomDigitNotZero())
                     .gender(Gender.FEMALE)
                     .build()
           );
        }
        testService.batchInsertStudents(students);
        return "batch insert students success";
    }
}
