package gr.edu.acme.repository;

import gr.edu.acme.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentRepository implements CrudRepository<Student, Long> {

    private static final Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findById(Integer integer) {
        return null;
    }

    @Override
    public void create(Student student) throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlRepository.getCommand("insert.table.student.000"), new String[]{"id"})) {



            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setInt(2, student.getAge());
            logger.info("Created Students table");
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            student.setId(rs.getLong(1));

        } catch (SQLException e) {
            throw new SQLException("Could not create student", e);
        }
    }



    @Override
    public boolean update(Student student) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     DataSource.loadSQLCommands().getProperty("update.table.student.000"))) {

            logger.debug("Updating student with ID={}", student.getId());

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(1, student.getAddress());
            preparedStatement.setInt(2, student.getAge());

            preparedStatement.setLong(4, student.getId());

            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Could not update student", e);
        }
    }

    @Override
    public boolean delete(Student student) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     DataSource.loadSQLCommands().getProperty("delete.table.student.000"))) {

            preparedStatement.setLong(1, student.getId());
            int rowAffected = preparedStatement.executeUpdate();
            logger.debug("Deleted student {}", student);
            return rowAffected == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete student", e);
        }
    }
}
