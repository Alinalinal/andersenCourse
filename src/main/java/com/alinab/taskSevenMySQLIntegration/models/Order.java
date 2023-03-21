package com.alinab.taskSevenMySQLIntegration.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"information"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "is_confirmed")
    boolean isConfirmed;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User orderOwner;

    @ManyToMany
    @JoinTable(name = "Order_Product", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;

    @OneToOne(mappedBy = "order")
    Information information;
}