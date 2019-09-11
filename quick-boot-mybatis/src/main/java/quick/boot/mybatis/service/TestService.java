package quick.boot.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import quick.boot.mybatis.entity.Student;


@Service
public class TestService {

    @Autowired
    private StudentService studentService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void batchInsertStudents(List<Student> students) {
        studentService.saveBatch(students, 100);
        // throw new RuntimeException("aaaa");
    }
}
