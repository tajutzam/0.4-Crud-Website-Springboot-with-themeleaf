package zam.dev.demomvc.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// class ini akan dijadikan class entity , atau representasi dari table pada database
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 8 )
    private long id;

    
    @Column(nullable = false , length = 64)
    private String name;

    @Column(length = 225)
    private String description;

    @Column(nullable = false)
    private double prize;

    @Column(nullable = false)
    private long quantity;

    // soon
    // @ManyToOne(fetch=FetchType.LAZY)
    // @JoinColumn(name="categories_id")
    // private Category categories;

}
