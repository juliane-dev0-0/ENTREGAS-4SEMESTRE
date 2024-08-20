package biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.entity.Venda;
import biblioteca.repository.VendaRepository;
import biblioteca.service.VendaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;
    private VendaRepository vendaRepository;

    @PostMapping("/save")
    public ResponseEntity<Venda> save(@RequestBody Venda venda) {
        try {
            Venda savedVenda = vendaService.save(venda);
            return new ResponseEntity<>(savedVenda, HttpStatus.CREATED);
        } catch (RuntimeException e) {
        	  e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Venda>> listAll() {
        try {
            List<Venda> lista = vendaService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{idVenda}")
    public ResponseEntity<Venda> findById(@PathVariable long idVenda) {
        try {
            Venda venda = vendaService.findById(idVenda);
            return new ResponseEntity<>(venda, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{idVenda}")
    public ResponseEntity<String> delete(@PathVariable long idVenda) {
        try {
            String mensagem = vendaService.delete(idVenda);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar", HttpStatus.BAD_REQUEST);
        }
    }

    
    @PutMapping("/update/{idVenda}")
    @Transactional
    public String update(Venda venda, long idVenda) {
        Venda vendaExistente = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada com o ID: " + idVenda));

        vendaExistente.setObservacao(venda.getObservacao());
        vendaExistente.setCliente(venda.getCliente());
        vendaExistente.setFuncionario(venda.getFuncionario());
        vendaExistente.setLivro(venda.getLivro());

        vendaRepository.save(vendaExistente);
        return "Venda atualizada com sucesso!";
    }
}
