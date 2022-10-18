package gr.edu.acme.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class Student extends BaseModel {

    private String name;
    private int age;
    private String address;

    private List<Enrollment> enrollments;
}
