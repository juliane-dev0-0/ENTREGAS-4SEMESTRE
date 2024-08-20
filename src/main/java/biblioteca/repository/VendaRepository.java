package biblioteca.repository;

import biblioteca.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository <Venda, Long> {
}
