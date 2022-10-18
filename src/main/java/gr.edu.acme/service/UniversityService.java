package gr.edu.acme.service;

import gr.edu.acme.model.University;
import gr.edu.acme.repository.CrudRepository;
import gr.edu.acme.repository.StudentRepository;
import gr.edu.acme.repository.UniversityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UniversityService implements CrudRepository<University, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final UniversityRepository universityRepository = null;

    public UniversityService(UniversityRepository universityRepository, StudentRepository studentRepository) {

    }


    @Override
    public List<University> findAll() {
        return null;
    }

    @Override
    public University findById(Integer integer) {
        return null;
    }

    @Override
    public University create(final University university) {
        University savedUniversity = universityRepository.create(university);
        logger.info("Created university");
        return savedUniversity;
    }

    @Override
    public List<University> createAll(University... universities) {
        List<University> savedUniversities = universityRepository.createAll(universities);
//      To do departments method
        logger.info("Creating all universities");
        return savedUniversities;
    }

    @Override
    public boolean update(University university) {
        if (university.getId() != 0 && university.getId() > 0) {
            boolean isUpdated = universityRepository.update(university);
//           To do departments method
            return isUpdated;
        }
        return false;
    }

    @Override
    public boolean delete(final University university) {
        //           To do departments method
        logger.info("Deleted university");
        return universityRepository.delete(university);
    }
}
