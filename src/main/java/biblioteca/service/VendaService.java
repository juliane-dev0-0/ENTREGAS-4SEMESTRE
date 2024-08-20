package biblioteca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.entity.Cliente;
import biblioteca.entity.Funcionario;
import biblioteca.entity.Livro;
import biblioteca.entity.Venda;
import biblioteca.repository.ClienteRepository;
import biblioteca.repository.FuncionarioRepository;
import biblioteca.repository.LivroRepository;
import biblioteca.repository.VendaRepository;
@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Venda save(Venda venda) {
        Cliente cliente = clienteRepository.findById(venda.getCliente().getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado: ID " + venda.getCliente().getIdCliente()));
        venda.setCliente(cliente);

        if (venda.getFuncionario() != null) {
            Funcionario funcionario = funcionarioRepository.findById(venda.getFuncionario().getIdFuncionario())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado: ID " + venda.getFuncionario().getIdFuncionario()));
            venda.setFuncionario(funcionario);
        }

        List<Livro> livrosAtualizados = new ArrayList<>();
        double valorTotal = 0;  // Inicialize o valor total

        for (Livro livro : venda.getLivro()) {
            Livro livroBD = livroRepository.findById(livro.getIdLivro())
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado: ID " + livro.getIdLivro()));

            livro.setValorTotal(livroBD.getValor() * livro.getQuantidade());
            livrosAtualizados.add(livroBD);

            valorTotal += livro.getValorTotal();  // Atualize o valor total da venda
        }
        
        venda.setLivro(livrosAtualizados);
        venda.setValorTotal(valorTotal);  // Defina o valor total da venda

        if (cliente.getIdadeCliente() < 18 && valorTotal > 500.0) {
            throw new RuntimeException("Clientes menores de 18 anos não podem realizar compras acima de R$ 500,00.");
        }

        return vendaRepository.save(venda);
    }

    public List<Venda> listAll() {
        return this.vendaRepository.findAll();
    }

    public Venda findById(long idVenda) {
        // Usando orElseThrow para lidar com a ausência de dados
        return this.vendaRepository.findById(idVenda)
                .orElseThrow(() -> new IllegalArgumentException("Venda com ID " + idVenda + " não encontrada"));
    }

    public String delete(long idVenda) {
        // Verifica se a venda existe antes de deletar
        Optional<Venda> venda = this.vendaRepository.findById(idVenda);
        if (venda.isPresent()) {
            this.vendaRepository.deleteById(idVenda);
            return "Venda deletada com sucesso!";
        } else {
            throw new IllegalArgumentException("Venda com ID " + idVenda + " não encontrada");
        }
    }

    public String update(Venda venda, long idVenda) {
        // Verifica se a venda existe antes de atualizar
        if (this.vendaRepository.existsById(idVenda)) {
            venda.setIdVenda(idVenda);
            this.vendaRepository.save(venda);
            return "Venda de " + venda.getLivro() + " atualizada com sucesso!";
        } else {
            throw new IllegalArgumentException("Venda com ID " + idVenda + " não encontrada");
        }
    }
}
