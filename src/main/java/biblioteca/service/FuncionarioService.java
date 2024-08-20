package biblioteca.service;

import biblioteca.entity.Funcionario;
import biblioteca.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public String save(Funcionario funcionario) {

        this.funcionarioRepository.save(funcionario);
        return funcionario.getNomeFuncionario() + " salvo com sucesso";
    }

    public List<Funcionario> listAll(){
        return this.funcionarioRepository.findAll();
    }

    public Funcionario findById(long idFuncionario) {
        Funcionario funcionario = this.funcionarioRepository.findById(idFuncionario).get();
        return funcionario;

    }

    public String delete(long idFuncionario) {
        this.funcionarioRepository.deleteById(idFuncionario);
        return "Funcionario deletado com sucesso!";
    }

    public String update(Funcionario funcionario, long idFuncionario) {
        funcionario.setIdFuncionario(idFuncionario);
        this.funcionarioRepository.save(funcionario);
        return funcionario.getNomeFuncionario() + " salvo com sucesso";
    }
}
