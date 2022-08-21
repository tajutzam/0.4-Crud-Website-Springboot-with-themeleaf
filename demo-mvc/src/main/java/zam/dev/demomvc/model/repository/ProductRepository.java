package zam.dev.demomvc.model.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import zam.dev.demomvc.model.entity.Product;

// ditandakan sebagai repository
@Repository
public interface ProductRepository extends CrudRepository<Product , Long>{
    
    // query dibuat berdasarkan nama method , ini disebut derived query
    List<Product> findByNameContains(String name);
    
}
