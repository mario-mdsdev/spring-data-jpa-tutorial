package com.mdsdev.spring.data.jpa.tutorial.repository;

import com.mdsdev.spring.data.jpa.tutorial.entity.Course;
import com.mdsdev.spring.data.jpa.tutorial.entity.Student;
import com.mdsdev.spring.data.jpa.tutorial.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CourseRepositoryTest {

    private final CourseRepository repository;

    @Test
    void printCourses() {
        final List<Course> courses = repository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    void saveCourseWithTeacher() {
        final Teacher teacher = Teacher.builder()
                .firstName("Priyanka")
                .lastName("Singh")
                .build();

        final Course course = Course.builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();

        repository.save(course);
    }

    @Test
    void findAllPagination() {
        final Pageable firstPageWithThreeRecords = PageRequest.of(0 , 3);
        final Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        final List<Course> courses = repository.findAll(firstPageWithThreeRecords).getContent();
        final long totalElements = repository.findAll(firstPageWithThreeRecords).getTotalElements();
        final int totalPages = repository.findAll(firstPageWithThreeRecords).getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);
    }

    @Test
    void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));

        Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2,
                Sort.by("title").descending().and(Sort.by("credit")));

        final List<Course> courses = repository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    void printFindByTitleContaining() {
        final Pageable firstPageTenRecords = PageRequest.of(0, 10);
        final List<Course> courses = repository.findByTitleContaining("J", firstPageTenRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    void saveCourseWithStudentAndTeacher() {
        final Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        final Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .build();

        final Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();
        course.addStudent(student);

        repository.save(course);
    }

}
