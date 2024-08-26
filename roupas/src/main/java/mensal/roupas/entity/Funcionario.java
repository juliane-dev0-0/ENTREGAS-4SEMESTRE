package mensal.roupas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  idFuncionario;
    
    @NotBlank(message = "Nome é obrigatório.")
    private String nomeFuncionario;
    
    @Min(value = 0, message = "Idade não pode ser negativa.")
    private int idadeFuncionario;
    
    @NotBlank(message = "Não pode ser nulo")
    private String matricula;

}