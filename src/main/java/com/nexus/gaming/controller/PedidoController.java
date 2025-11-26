package com.nexus.gaming.controller;

import com.nexus.gaming.model.Pedido;
import com.nexus.gaming.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

// Repositórios
interface PedidoRepository extends JpaRepository<Pedido, Long> {}
interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {}

// DTOs para receber o JSON complexo do Frontend
record ItemRequest(Long idProduto, Integer quantidade, BigDecimal precoUnitario) {}
record PedidoRequest(Long idCliente, BigDecimal total, List<ItemRequest> itens) {}

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemRepository;

    @PostMapping
    @Transactional // Garante que salva tudo ou nada
    public Pedido criarPedido(@RequestBody PedidoRequest dados) {
        // 1. Criar e Salvar o Pedido
        Pedido pedido = new Pedido();
        pedido.setIdCliente(dados.idCliente());
        pedido.setValorTotal(dados.total());
        pedido.setStatusPedido("Confirmado");

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // 2. Converter e Salvar os Itens
        List<ItemPedido> itens = dados.itens().stream().map(itemReq -> {
            ItemPedido item = new ItemPedido();
            item.setPedido(pedidoSalvo);
            item.setIdProduto(itemReq.idProduto());
            item.setQuantidade(itemReq.quantidade());
            item.setPrecoUnitario(itemReq.precoUnitario());
            return item;
        }).collect(Collectors.toList());

        itemRepository.saveAll(itens);

        pedidoSalvo.setItens(itens);
        return pedidoSalvo;
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}