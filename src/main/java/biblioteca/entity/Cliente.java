package biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotBlank(message = "Nome é obrigatório.")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)+$", message = "Nome deve conter pelo menos duas palavras.")
    private String nomeCliente;

    @Min(value = 0, message = "Idade não pode ser negativa.")
    private int idadeCliente;

    @NotBlank(message = "Endereço é obrigatório.")
    private String enderecoCliente;

    @Email(message = "Email inválido.")
    private String emailCliente;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato XXX.XXX.XXX-XX.")
    private String cpfCliente;

    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato XXXXX-XXX.")
    private String cepCliente;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve seguir o padrão (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefoneCliente;
}
