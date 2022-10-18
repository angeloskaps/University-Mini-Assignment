package gr.edu.acme.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class Enrollment extends BaseModel {
    private Unit unit;
    private int mark;
    private Student student;


}
