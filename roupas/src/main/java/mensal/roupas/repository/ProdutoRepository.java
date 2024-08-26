package mensal.roupas.repository;


import mensal.roupas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByOrderByNomeProdutoAsc(); //procura os nomes de forma asc;

    List<Produto> findByNomeProdutoContaining(String nome);

    @Query("SELECT p FROM Produto p WHERE p.valor >= 50")
    List<Produto> findProdutosComValorMaiorOuIgualA50();

}
