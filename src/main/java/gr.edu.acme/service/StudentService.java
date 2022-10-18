package gr.edu.acme.service;

import gr.edu.acme.model.Enrollment;
import gr.edu.acme.model.Student;
import gr.edu.acme.repository.CrudRepository;
import gr.edu.acme.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class StudentService implements CrudRepository<Student, Integer> {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private static StudentRepository studentRepository ;

    public StudentService(StudentRepository studentRepository) {

    }


    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findById(Integer integer) {
        return null;
    }

    @Override
    public Student create(Student student) throws SQLException {
        logger.info("Creating student");
        studentRepository.create(student);
        for (Enrollment enrollment : student.getEnrollments()) {
//            createOrUpdateEnrollment(student, enrollment);
        }
        return student;
    }

    @Override
    public List<Student> createAll(Student... students) {

        List<Student> savedStudents = studentRepository.createAll(students);
//         To do enrollments method
        logger.info("Created all students");
        return savedStudents;
    }

    @Override
    public boolean update(Student student) {
        if (student.getId() != 0 && student.getId() > 0) {
            boolean isUpdated = studentRepository.update(student);
//            To do enrollments method
            return isUpdated;
        } return false;
    }

    @Override
    public boolean delete (Student student) {
//       To do enrollments method
        logger.info("Deleted student");
        return studentRepository.delete(student);
    }
}
