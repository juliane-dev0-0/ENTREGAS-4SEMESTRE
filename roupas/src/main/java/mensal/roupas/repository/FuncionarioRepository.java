package mensal.roupas.repository;

import mensal.roupas.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByNomeFuncionarioContaining(String nome);

    List<Funcionario> findByMatriculaContaining(String matricula);

    @Query("SELECT f FROM Funcionario f WHERE f.idadeFuncionario = :idade ORDER BY f.nomeFuncionario ASC")
    List<Funcionario> findByIdade(@Param("idade") int idade);


}
