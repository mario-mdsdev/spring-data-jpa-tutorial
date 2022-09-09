package br.com.mdsdev.spring.data.jpa.repository;

import br.com.mdsdev.spring.data.jpa.entity.Course;
import br.com.mdsdev.spring.data.jpa.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TeacherRepositoryTest {

    private final TeacherRepository repository;

    @Test
    void saveTeacher() {
        final Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        final Course courseJava = Course.builder()
                .title("Java")
                .credit(6)
                .build();

        final Teacher teacher = Teacher.builder()
                .firstName("Qutub")
                .lastName("Khan")
//                .courses(List.of(courseDBA, courseJava))
                .build();

        repository.save(teacher);
    }

    @Test
    void printAllTeachers() {
        final List<Teacher> teachers = repository.findAll();
        System.out.println("teachers = " + teachers);
    }

}