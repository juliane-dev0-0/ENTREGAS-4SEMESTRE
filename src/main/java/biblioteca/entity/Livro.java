package biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @NotBlank(message = "Título é obrigatório.")
    private String titulo;

    private String descricao;
    
    private int ano;

    private String autor;

    private String editora;

    private double valor;

    @Transient
    private double valorTotal;

    @Transient
    private int quantidade;
}
