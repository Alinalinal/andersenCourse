package com.alinab.taskSevenMySQLIntegration.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int orderUuid;

    @Column(name = "date_of_order")
    @Temporal(TemporalType.DATE)
    Date dateOfOrder;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User orderOwner;

    @ManyToMany
    @JoinTable(name = "Order_Product", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderUuid != order.orderUuid) return false;
        return Objects.equals(dateOfOrder, order.dateOfOrder);
    }

    @Override
    public int hashCode() {
        int result = orderUuid;
        result = 31 * result + (dateOfOrder != null ? dateOfOrder.hashCode() : 0);
        return result;
    }
}