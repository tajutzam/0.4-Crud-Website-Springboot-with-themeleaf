package zam.dev.demomvc.service;

import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import zam.dev.demomvc.model.entity.Product;
import zam.dev.demomvc.model.repository.ProductRepository;

@Service
@Transactional
public class ProductService{

     // menambahkan object productreposioty , dengan memberikan autowired agar dependency injection ikut di include
       @Autowired
        private ProductRepository productRepository;


     //    return iterable yang akan bisa digunakan untuk foreach pada themeleaf
     public Iterable<Product> findAll(){
          return productRepository.findAll();
     }

     // add product , dengan parameter entity
     public Product addProduct(Product product){
          return productRepository.save(product);
     }

     // update product , dengan menggunakan method save namun spring
     public Product updateProduct(Product product){
          return productRepository.save(product);
     } 

     // delete product berdasarkan id , 
     public void delete(long id){
          productRepository.deleteById(id);
     }

     // find product berdasarkan id
     public Product findById(Long id){
          Optional<Product> optionalProduct = productRepository.findById(id);
          return optionalProduct.get();
     }
   
     // Menghitung jumlah data pada table , return adalah long
     public Long count(){
          return productRepository.count();
     }

     // find data product berdasarkan like query , 
     public List<Product> findByName(String keyword){
          return productRepository.findByNameContains(keyword);
     }
     // jika sudah maka kita akan mengakses pada controller melalu service
}