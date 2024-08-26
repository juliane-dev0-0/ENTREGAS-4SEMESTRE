package mensal.roupas.entity;

import jakarta.persistence.*;
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
    private Long  idVenda;
    private String endereco;
    private double valor_total;

    //Somente um cliente para várias vendas morô
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    //apenas um funcionario para varias vendas tb
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;


    // Uma venda pode ter vários produtos e um produto pode estar vinculado em
    //várias vendas
    @ManyToMany
    @JoinTable(
            name = "venda_produto",
            joinColumns     = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produto;

}