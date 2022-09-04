package com.mdsdev.spring.data.jpa.tutorial.repository;

import com.mdsdev.spring.data.jpa.tutorial.entity.Guardian;
import com.mdsdev.spring.data.jpa.tutorial.entity.Student;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class StudentRepositoryTest {

    private final StudentRepository studentRepository;

    @Test
    void shouldSaveStudent() {
        final Student student = Student.builder()
                .emailId("shabbir@gmail.com")
                .firstName("Shabbir")
                .lastName("Dawoodi")
//                .guardianName("Nikhil")
//                .guardianEmail("nikhil@gmail.com")
//                .guardianMobile("9999999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    void shouldSaveStudentWithGuardian() {
        final Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("nikhil@gmail.com")
                .mobile("9999999999")
                .build();

        final Student student = Student.builder()
                .firstName("Shivam")
                .emailId("shivam@gmail.com")
                .lastName("Kumar")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    void printAllStudents() {
        final List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    void printStudentsByFirstName() {
        final List<Student> studentList = studentRepository.findByFirstName("shivam");
        System.out.println("studentList = " + studentList);
    }

    @Test
    void printStudentsByFirstNameContaining() {
        final List<Student> studentList = studentRepository.findByFirstNameContaining("sh");
        System.out.println("studentList = " + studentList);
    }

    @Test
    void printStudentsBasedOnGuardianName() {
        final List<Student> students = studentRepository.findByGuardianName("Nikhil");
        System.out.println("students = " + students);
    }

    @Test
    void printStudentByLastNameAndFirstName() {
        final String firstName = "Shivam";
        final String lastName = "Kumar";
        final Student student = studentRepository.findByLastNameAndFirstName(lastName, firstName);
        System.out.println("student = " + student);
    }

    @Test
    void printStudentByEmailAddress() {
        final String emailId = "shabbir@gmail.com";
        final Student student = studentRepository.getStudentByEmailAddress(emailId);
        System.out.println("student = " + student);
    }

    @Test
    void printStudentFirstNameByEmailAddress() {
        final String emailId = "shivam@gmail.com";
        final String firstName = studentRepository.getStudentFirstNameByEmailAddress(emailId);
        System.out.println("student = " + firstName);
    }

    @Test
    void printStudentByEmailAddressNative() {
        final String emailId = "shivam@gmail.com";
        final Student student = studentRepository.getStudentByEmailAddressNative(emailId);
        System.out.println("student = " + student);
    }

    @Test
    void printStudentByEmailAddressNativeNamedParam() {
        final String emailId = "shivam@gmail.com";
        final Student student = studentRepository.getStudentByEmailAddressNativeNamedParam(emailId);
        System.out.println("student = " + student);
    }

    @Test
    void updateStudentNameByEmailId_TestModifyingTransactional() {
        studentRepository.updateStudentNameByEmailId("Shabbir", "shabbir@gmail.com");
    }

}
