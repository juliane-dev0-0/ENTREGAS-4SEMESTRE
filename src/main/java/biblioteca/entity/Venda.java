package biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenda;

    @NotBlank(message = "A observação é obrigatória.")
    private String observacao;

    @ManyToOne(optional = false)
    @NotNull(message = "A venda precisa ter um cliente vinculado.")
    private Cliente cliente;

    @ManyToOne
    private Funcionario funcionario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "venda_id")
    @NotNull(message = "A lista de produtos não pode estar vazia.")
    private List<Livro> livro;
    
    private double valorTotal;
}
