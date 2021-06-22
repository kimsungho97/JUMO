package JUMO.project.Repository;

import JUMO.project.Entity.Price;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public interface Price_Repository {
    List<Price> findAll();

    List<Price> findByname(String name);

    List<Price> findBycode(String code);

    HashMap<String,String> allStock_name(String name);

    HashMap<String,String> allStock_code(String code);
}
