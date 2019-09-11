package quick.boot.mybatis.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quick.boot.mybatis.entity.Student;
import quick.boot.mybatis.mapper.StudentMapper;

@Slf4j
@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {

}

