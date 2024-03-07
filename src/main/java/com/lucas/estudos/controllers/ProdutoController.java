package com.lucas.estudos.controllers;

import com.lucas.estudos.domains.Produto;
import com.lucas.estudos.domains.ProdutoDto;
import com.lucas.estudos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping(value = "/cadastro")
    public ResponseEntity<ProdutoDto> insert(@RequestBody ProdutoDto produtoDto){
        Produto produtoCriado = produtoService.insert(produtoDto);

        if(produtoCriado != null){
            ProdutoDto respostaDto = mapToDTO(produtoCriado);
            System.out.println("O produto foi criado com sucesso!");
            return new ResponseEntity<>(respostaDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ProdutoDto mapToDTO(Produto produto) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        return dto;
    }
}
