package mensal.roupas.controller;

import jakarta.transaction.Transactional;
import mensal.roupas.entity.Produto;
import mensal.roupas.entity.Venda;
import mensal.roupas.repository.VendaRepository;
import mensal.roupas.service.FuncionarioService;
import mensal.roupas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/venda")
public class VendaController {

    @Autowired
    public VendaService vendaService;
    public VendaRepository vendaRepository;
    public FuncionarioService funcionarioService;


    @PostMapping("/save")
    public ResponseEntity<Venda> save(@RequestBody Venda venda) {
        try {
            Venda vendas = vendaService.save(
                    venda.getCliente().getIdCliente(), //obtem o id do cliente q esta associado a venda
                    venda.getFuncionario().getIdFuncionario(), //pega o id do funcionario tb
                    venda.getProduto().stream().map(Produto::getIdProduto).collect(Collectors.toList()),
                    //pega a lista de IDs dos produtos incluídos na venda. Este trecho de código utiliza
                    // streams (coleção do Java) para mapear cada produto ao seu ID e em seguida, coletar esses IDs em uma lista.
                    venda //último parâmetro para salvar a venda
            );
            return new ResponseEntity<>(vendas, HttpStatus.CREATED);
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
        try {//O Optional é como uma caixa que pode ou não ter um item dentro.
            Optional<Venda> vendaOpt = vendaService.findById(idVenda);
            // pode devolver a venda (se existir) ou pode não devolver nada (se a venda não for encontrada)
            if (vendaOpt.isPresent()) {
                return new ResponseEntity<>(vendaOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
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
    public ResponseEntity<String> update(@RequestBody Venda venda, @PathVariable long idVenda) {
        try {
            Optional<Venda> vendaOpt = vendaRepository.findById(idVenda);

            if (vendaOpt.isPresent()) {
                Venda vendaExistente = vendaOpt.get();
                vendaExistente.setEndereco(venda.getEndereco());
                vendaExistente.setCliente(venda.getCliente());
                vendaExistente.setFuncionario(venda.getFuncionario());
                vendaExistente.setProduto(venda.getProduto());

                vendaRepository.save(vendaExistente);
                return new ResponseEntity<>("Venda atualizada com sucesso!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Venda não encontrada com o ID: " + idVenda, HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Erro ao atualizar venda: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/endereco/{nome}")
    public List<Venda> findByEnderecoContaining(@PathVariable String nome) {
        return vendaService.findByEnderecoContaining(nome);
    }

    @GetMapping("/produtos/{idVenda}")
    public List<Produto> getProdutosByVenda(@PathVariable Long idVenda) {
        return vendaService.ProdutosById(idVenda);
    }


}
