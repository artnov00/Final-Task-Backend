package com.nexus.gaming.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long idItem;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    @JsonIgnore // Evita loop infinito no JSON
    private Pedido pedido;

    @Column(name = "id_produto")
    private Long idProduto;

    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
}