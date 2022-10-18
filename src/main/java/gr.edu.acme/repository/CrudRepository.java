package gr.edu.acme.repository;

import java.sql.SQLException;
import java.util.List;
// O = obj
public interface CrudRepository<O, Id> {
    List<O> findAll();

    O findById(Id id);

    O create(O o) throws SQLException;

    List<O> createAll(O... os);

    boolean update(O o);

    boolean delete(O o);

}
