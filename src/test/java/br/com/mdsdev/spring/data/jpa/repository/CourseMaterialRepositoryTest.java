package br.com.mdsdev.spring.data.jpa.repository;

import br.com.mdsdev.spring.data.jpa.entity.Course;
import br.com.mdsdev.spring.data.jpa.entity.CourseMaterial;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CourseMaterialRepositoryTest {

    private final CourseMaterialRepository repository;

    @Test
    void saveCourseMaterial() {
        final Course course = Course.builder()
                .title("DSA") /* Data Structure Algorithm */
                .credit(6)
                .build();

        final CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }

    @Test
    void printAllCourseMaterials() {
        final List<CourseMaterial> courseMaterials = repository.findAll();
        System.out.println("courseMaterialList = " + courseMaterials);
    }

}
