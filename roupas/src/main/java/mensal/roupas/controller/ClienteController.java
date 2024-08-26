package mensal.roupas.controller;

import mensal.roupas.entity.Cliente;
import mensal.roupas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Cliente cliente) {
        try {
            String mensagem = this.clienteService.save(cliente);
            return new ResponseEntity<String>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Deu esse erro ao salvar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Cliente>> listAll() {
        try {
            List<Cliente> lista = this.clienteService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/findById/{idCliente}")
    public ResponseEntity<Cliente> findById(@PathVariable long idCliente) {
        try {
            Cliente cliente = this.clienteService.findById(idCliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/delete/{idCliente}")
    public ResponseEntity<String> delete(@PathVariable long idCliente) {
        try {
            String mensagem = this.clienteService.delete(idCliente);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Deu erro ao deletar", HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/update/{idCliente}")
    public ResponseEntity<String> update(@RequestBody Cliente cliente, @PathVariable long idCliente) {
        try {
            String mensagem = this.clienteService.update(cliente, idCliente);
            return new ResponseEntity<String>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Deu esse erro ao salvar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/over18")
    public ResponseEntity<List<Cliente>> getClientesOver18YearsOld() {
        try {
            List<Cliente> clientes = clienteService.getClientesOver18YearsOld();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quantidade")
    public ResponseEntity<String> getQuantosClientesCadastrados() {
        try {
            String quantidade = clienteService.quantosClientesCadastrados();
            return new ResponseEntity<>(quantidade + " clientes encontrados ^ ^", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao obter quantidade de clientes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nome/{nome}")
    public List<Cliente> findByNomeClienteContaining(@PathVariable String nome) {
        return clienteService.findByNomeClienteContaining(nome);
    }
}



