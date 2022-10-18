package gr.edu.acme;

import gr.edu.acme.repository.StudentRepository;
import gr.edu.acme.repository.UniversityRepository;
import gr.edu.acme.service.StudentService;
import gr.edu.acme.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private static final UniversityService universityService = new UniversityService(new UniversityRepository(),
                                                                                      new StudentRepository());
    private static final StudentService studentService = new StudentService(new StudentRepository());
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        new App();

    }
    public App(){



}
}
