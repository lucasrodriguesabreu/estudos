package com.lucas.estudos.domains;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_pedido")
public class PedidoDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Date momentoPedido;

    private StatusPedido statusPedido;

}
