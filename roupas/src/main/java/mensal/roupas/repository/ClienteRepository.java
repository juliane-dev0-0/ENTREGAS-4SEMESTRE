package mensal.roupas.repository;

import mensal.roupas.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.idadeCliente >= 18")
    List<Cliente> findOver18YearsOld();

    List<Cliente> findByNomeClienteContaining(String nome);
    //é um método automático meio q usando por exemplo find (procure/ache) nomeCliente(nome do cliente posto no
    // entity Client ) Containing (contendo) strinf nome);


}