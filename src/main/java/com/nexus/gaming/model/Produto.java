package com.nexus.gaming.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name = "imagem_url")
    private String imagemUrl;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}