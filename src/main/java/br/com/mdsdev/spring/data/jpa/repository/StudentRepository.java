package br.com.mdsdev.spring.data.jpa.repository;

import br.com.mdsdev.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);
    public List<Student> findByFirstNameContaining(String name);
    public List<Student> findByLastNameNotNull();
    public List<Student> findByGuardianName(String guardianName);
    public Student findByLastNameAndFirstName(String lastName, String firstName);

    // JPQL
    @Query("select s from Student s where s.emailId = ?1")
    public Student getStudentByEmailAddress(String emailId);

    // JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String getStudentFirstNameByEmailAddress(String emailId);

    // Native SQL
    @Query(
            value = "SELECT * FROM tbl_student WHERE email_address = ?1",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNative(String emailId);

    // Native SQL Named Param
    @Query(
            value = "SELECT * FROM tbl_student WHERE email_address = :emailId",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE tbl_student SET first_name = ?1 WHERE email_address = ?2",
            nativeQuery = true
    )
    public int updateStudentNameByEmailId(String firstName, String emailId);

}
