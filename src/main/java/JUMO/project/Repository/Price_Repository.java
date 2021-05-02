package JUMO.project.Repository;

import JUMO.project.Entity.Price;

import javax.persistence.EntityManager;
import java.util.List;

public interface Price_Repository {
    List<Price> findAll();

    List<Price> findByname(String name);

    List<Price> findBycode(String code);
}
