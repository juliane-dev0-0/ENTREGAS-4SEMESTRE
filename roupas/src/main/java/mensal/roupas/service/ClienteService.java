package mensal.roupas.service;

import mensal.roupas.entity.Cliente;
import mensal.roupas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String save(Cliente cliente) {

        this.clienteRepository.save(cliente);
        return cliente.getNomeCliente() + " salvo com sucesso!";
    }

    public List<Cliente> listAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(long idCliente) {
        Cliente cliente = this.clienteRepository.findById(idCliente).get();
        return cliente;
    }

    public String delete(long idCliente) {
        this.clienteRepository.deleteById(idCliente);
        return "Cliente deletado com sucesso";
    }

    public String update(Cliente cliente, long idCliente) {
        cliente.setIdCliente(idCliente);
        this.clienteRepository.save(cliente);
        return (cliente.getNomeCliente() + "Cliente atualizado").toLowerCase();
    }

    public List<Cliente> getClientesOver18YearsOld() {
        return clienteRepository.findOver18YearsOld();
    }

    public String quantosClientesCadastrados() {
        long count = clienteRepository.count();
        return String.valueOf(count);
    }

    public List<Cliente> findByNomeClienteContaining(String nome) {
        return clienteRepository.findByNomeClienteContaining(nome);
    }
}