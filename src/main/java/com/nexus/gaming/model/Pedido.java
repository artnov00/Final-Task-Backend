package com.nexus.gaming.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "id_cliente")
    private Long idCliente; // Mantendo simples como no SQL, ou poderia ser @ManyToOne Cliente

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido = LocalDateTime.now();

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "status_pedido")
    private String statusPedido = "Pendente";

    // Relacionamento com Itens (Cascade: Salvar pedido salva itens)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;
}