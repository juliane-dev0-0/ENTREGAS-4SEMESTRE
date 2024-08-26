package mensal.roupas.service;


import mensal.roupas.entity.Funcionario;
import mensal.roupas.repository.FuncionarioRepository;
import mensal.roupas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    private VendaRepository vendaRepository;

    public String save(Funcionario funcionario) {
        this.funcionarioRepository.save(funcionario);
        return funcionario.getNomeFuncionario() + "Funcionario adicionado com sucesso!";
    }

    public List<Funcionario> listAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(long idFuncionario) {
        Funcionario funcionario = this.funcionarioRepository.findById(idFuncionario).get();
        return funcionario;
    }

    public String delete(long idFuncionario) {
        this.funcionarioRepository.deleteById(idFuncionario);
        return "Deletei rapaiz ";
    }

    public String update(Funcionario funcionario, long idFuncionario) {
        funcionario.setIdFuncionario(idFuncionario);
        this.funcionarioRepository.save(funcionario);
        return funcionario.getNomeFuncionario() + "FUNCIONARIO UPDATIZADO COM SUCESSO FIH ^ ^"; //preciso mudar dps
    }

    public List<Funcionario> findByNomeFuncionarioContaining(String nome) {
        return funcionarioRepository.findByNomeFuncionarioContaining(nome);
    }

    public List<Funcionario> findByMatriculaContaining(String matricula) {
        return funcionarioRepository.findByMatriculaContaining(matricula);
    }

    public List<Funcionario> findByIdadeOrderByNome(int idade) {
        return funcionarioRepository.findByIdade(idade);
    }


}
