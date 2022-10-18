package gr.edu.acme.repository;

import gr.edu.acme.model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniversityRepository implements CrudRepository<University, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(UniversityRepository.class);

    @Override
    public University create(University university) {
        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DataSource.loadSQLCommands().getProperty("create.table.university"), new String[]{"ID"});
            preparedStatement.setString(1, university.getName());
            preparedStatement.executeUpdate();
            return university;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot Create university");
        }
    }

    @Override
    public List<University> createAll(University... universities) {
        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DataSource.loadSQLCommands().getProperty("insert.table.university.000"));

            for (University university : universities) {
                preparedStatement.setString(1, university.getName());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            logger.info("Created (size={}) universities", universities.length);
            return Arrays.asList(universities);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
     public List<University> findAll()  {
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(DataSource.loadSQLCommands().getProperty("select.table.university.000"))) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<University> universityList = new ArrayList<>();

            while (resultSet.next()) {
// to do
            }

            logger.debug("Found all universities {}", universityList);

            return universityList;
        } catch (SQLException e){
            throw new RuntimeException("Could not find all universities", e);
        }
    }

    @Override
    public University findById(Integer integer) {
        return null;
    }


    @Override
    public boolean update(University o) {
        return false;
    }

    @Override
    public boolean delete(University o) {
        return false;
    }


}
