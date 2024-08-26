package mensal.roupas.service;

import mensal.roupas.entity.Cliente;
import mensal.roupas.entity.Funcionario;
import mensal.roupas.entity.Produto;
import mensal.roupas.entity.Venda;
import mensal.roupas.repository.ClienteRepository;
import mensal.roupas.repository.FuncionarioRepository;
import mensal.roupas.repository.ProdutoRepository;
import mensal.roupas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoService produtoService;

    public Venda save(Long idCliente, Long idFuncionario, List<Long> idProduto, Venda venda) {

        // vendo se existe ou n
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        Cliente clientes = cliente.get();

        // vendo se existe ou n
        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
        if (funcionario.isEmpty()) {
            throw new IllegalArgumentException("Funcionário não encontrado");
        }
        Funcionario funcionarios = funcionario.get();

        List<Produto> produtos = produtoRepository.findAllById(idProduto);
        if (produtos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum produto encontrado");
        }


        venda.setCliente(clientes);
        venda.setFuncionario(funcionarios);
        venda.setProduto(produtos);

        double valorTotal = finalValue(produtos);
        venda.setValor_total(valorTotal);

        return vendaRepository.save(venda);
    }


    public double finalValue(List<Produto> produtos) {

        double valor_total = 0;

        for (Produto p : produtos) {
            Produto produtoEncontrado = this.produtoService.findById(p.getIdProduto());
            valor_total += produtoEncontrado.getValor();
        }

        return valor_total;
    }

    public List<Venda> listAll() {
        return vendaRepository.findAll();
    }

    public String delete(long idVenda) {
        this.vendaRepository.deleteById(idVenda);
        return "Deletado com sucesso";
    }

    public String update(Venda venda, long idVenda) {
        venda.setIdVenda(idVenda);
        this.vendaRepository.save(venda);
        return venda.getIdVenda() + "Adicionado com sucesso!";
    }

    public Optional<Venda> findById(long idVenda) {
        return vendaRepository.findById(idVenda);
    }

    public List<Venda> findByEnderecoContaining(String nome) {
        return vendaRepository.findByEnderecoContaining(nome);
    }

    public List<Produto> ProdutosById(Long idVenda) {
        return vendaRepository.findProdutosByVendaId(idVenda);
    }


}