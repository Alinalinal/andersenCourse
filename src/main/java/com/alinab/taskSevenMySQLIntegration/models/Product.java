package com.alinab.taskSevenMySQLIntegration.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"orders"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product implements Serializable {

    static long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int uuid;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 45, message = "Name should be between 1 and 45 characters")
    @Column(name = "name")
    String name;

    @Column(name = "is_food")
    String isFood;

    @NotEmpty
    @Column(name = "currency_code")
    String currencyCode;

    @Column(name = "purchase_price")
    BigDecimal purchasePrice;

    @Column(name = "selling_price")
    BigDecimal sellingPrice;

    @Column(name = "produced_on")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date producedOn;

    @Column(name = "shelf_life_days")
    int shelfLifeDays;

    @ManyToMany(mappedBy = "products")
    List<Order> orders;
}
