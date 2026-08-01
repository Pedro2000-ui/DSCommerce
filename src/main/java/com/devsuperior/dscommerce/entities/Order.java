package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant     moment;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User        client;

    public Order() {}

    public Order(Long id, Instant moment, OrderStatus status) {
        this.id = id;
        this.moment = moment;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getmoment() {
        return moment;
    }

    public void setmoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
