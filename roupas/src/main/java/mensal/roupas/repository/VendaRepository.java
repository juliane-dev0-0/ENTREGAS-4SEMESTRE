package mensal.roupas.repository;

import mensal.roupas.entity.Produto;
import mensal.roupas.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByEnderecoContaining(String endereco);

    @Query("SELECT v.produto FROM Venda v WHERE v.idVenda = :idVenda")
    List<Produto> findProdutosByVendaId(@Param("idVenda") Long idVenda);


}
