package com.lucas.estudos.services;

import com.lucas.estudos.domains.Produto;
import com.lucas.estudos.domains.ProdutoDto;
import com.lucas.estudos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto insert(ProdutoDto produtoDto){
        Produto novoProduto = new Produto();
        novoProduto.setId(produtoDto.getId());
        novoProduto.setNome(produtoDto.getNome());
        novoProduto.setDescricao(produtoDto.getDescricao());
        novoProduto.setPreco(produtoDto.getPreco());
        return produtoRepository.save(novoProduto);
    }

    public List<Produto> findProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(String id) {
        return produtoRepository.findById(id);
    }

    public Produto updateProduto(String id, ProdutoDto novoProdutoDto) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(novoProdutoDto.getNome());
            produto.setDescricao(novoProdutoDto.getDescricao());
            produto.setPreco(novoProdutoDto.getPreco());

            return produtoRepository.save(produto);
        }
        return null;
    }

    public boolean deleteProduto(String id) {
        if(produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
