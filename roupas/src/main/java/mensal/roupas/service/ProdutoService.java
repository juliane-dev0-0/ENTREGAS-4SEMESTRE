package mensal.roupas.service;

import mensal.roupas.entity.Produto;
import mensal.roupas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public String save(Produto produto) {
        this.produtoRepository.save(produto);
        return produto.getNomeProduto() + produto.getValor() + "Salvo com sucesso";

    }

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(long idProduto) {
        Produto produto = this.produtoRepository.findById(idProduto).get();
        return produto;
    }

    public String delete(long idProduto) {
        this.produtoRepository.deleteById(idProduto);
        return "DELETADO COM SUCESSO";
    }

    public String update(Produto produto, long idProduto) {
        produto.setIdProduto(idProduto);
        this.produtoRepository.save(produto);
        return produto.getNomeProduto() + produto.getValor() + "Adicionado com sucesso!";
    }

    public List<Produto> NomeProdutosASC() {
        return produtoRepository.findAllByOrderByNomeProdutoAsc();
    }

    public List<Produto> findByNomeProdutoContaining(String nome) {
        return produtoRepository.findByNomeProdutoContaining(nome);
    }

    public List<Produto> produtoApartir50() {
        return produtoRepository.findProdutosComValorMaiorOuIgualA50();
    }

}