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
         Optional<Cliente> clienteOpt = clienteRepository.findById(venda.getCliente().getIdCliente());
    if (clienteOpt.isPresent()) {
        Cliente cliente = clienteOpt.get();
        venda.setCliente(cliente);
    } else {
        throw new RuntimeException("Cliente não encontrado: ID " + venda.getCliente().getIdCliente());
    }


       if (venda.getFuncionario() != null) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(venda.getFuncionario().getIdFuncionario());
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            venda.setFuncionario(funcionario);
        } else {
            throw new RuntimeException("Funcionário não encontrado: ID " + venda.getFuncionario().getIdFuncionario());
        }
    }

        List<Livro> livrosAtualizados = new ArrayList<>();
        double valorTotal = 0;  

        for (Livro livro : venda.getLivro()) {
        Optional<Livro> livroOpt = livroRepository.findById(livro.getIdLivro());
    
        if (livroOpt.isPresent()) {
        Livro livroBD = livroOpt.get();
        livro.setValorTotal(livroBD.getValor() * livro.getQuantidade());
        livrosAtualizados.add(livroBD);
        valorTotal += livro.getValorTotal();
        } else {
        throw new RuntimeException("Livro não encontrado: ID " + livro.getIdLivro());
     }
}
        venda.setLivro(livrosAtualizados);
        venda.setValorTotal(valorTotal);  

        if (cliente.getIdadeCliente() < 18 && valorTotal > 500.0) {
            throw new RuntimeException("Clientes menores de 18 anos não podem realizar compras acima de R$ 500,00.");
        }

        return vendaRepository.save(venda);
    }

    public List<Venda> listAll() {
        return this.vendaRepository.findAll();
    }

  public Venda findById(long idVenda) {
    Optional<Venda> venda = this.vendaRepository.findById(idVenda);

    if (venda.isPresent()) {
        return venda.get();
    } else {
        throw new IllegalArgumentException("Venda com ID " + idVenda + " não encontrada");
    }
}


    public String delete(long idVenda) {
       
        Optional<Venda> venda = this.vendaRepository.findById(idVenda);
        if (venda.isPresent()) {
            this.vendaRepository.deleteById(idVenda);
            return "Venda deletada com sucesso!";
        } else {
            throw new IllegalArgumentException("Venda com ID " + idVenda + " não encontrada");
        }
    }

    public String update(Venda venda, long idVenda) {
       
        if (this.vendaRepository.existsById(idVenda)) {
            venda.setIdVenda(idVenda);
            this.vendaRepository.save(venda);
            return "Venda de " + venda.getLivro() + " atualizada com sucesso!";
        } else {
            throw new IllegalArgumentException("Venda com ID " + idVenda + " não encontrada");
        }
    }
}
