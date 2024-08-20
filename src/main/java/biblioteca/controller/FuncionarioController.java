package biblioteca.controller;

import biblioteca.entity.Funcionario;
import biblioteca.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Funcionario funcionario) {
        try {
            String mensagem = this.funcionarioService.save(funcionario);
            return new ResponseEntity<String>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Deu esse erro ao salvar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Funcionario>> listAll() {
        try {
            List<Funcionario> lista = this.funcionarioService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/findById/{idFuncionario}")
    public ResponseEntity<Funcionario> findById(@PathVariable long idFuncionario) {
        try {
            Funcionario funcionario = this.funcionarioService.findById(idFuncionario);
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/delete/{idFuncionario}")
    public ResponseEntity<String> delete(@PathVariable long idFuncionario) {
        try {
            String mensagem = this.funcionarioService.delete(idFuncionario);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Deu erro ao deletar", HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/update/{idFuncionario}")
    public ResponseEntity<String> update(@RequestBody Funcionario funcionario, @PathVariable long idFuncionario) {
        try {
            String mensagem = this.funcionarioService.update(funcionario, idFuncionario);
            return new ResponseEntity<String>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Deu esse erro ao salvar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}




