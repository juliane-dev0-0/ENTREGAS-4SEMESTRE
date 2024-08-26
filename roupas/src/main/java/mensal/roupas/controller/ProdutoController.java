package mensal.roupas.controller;

import mensal.roupas.entity.Produto;
import mensal.roupas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Produto produto) {
        try {
            String mensagem = this.produtoService.save(produto);
            return new ResponseEntity<String>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Deu esse erro ao salvar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Produto>> listAll() {
        try {
            List<Produto> lista = this.produtoService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/findById/{idProduto}")
    public ResponseEntity<Produto> findById(@PathVariable long idProduto) {
        try {
            Produto produto = this.produtoService.findById(idProduto);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/delete/{idProduto}")
    public ResponseEntity<String> delete(@PathVariable long idProduto) {
        try {
            String mensagem = this.produtoService.delete(idProduto);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Deu erro ao deletar", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{idProduto}")
    public ResponseEntity<String> update(@RequestBody Produto produto, @PathVariable long idProduto) {
        try {
            String mensagem = this.produtoService.update(produto, idProduto);
            return new ResponseEntity<String>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Deu esse erro ao salvar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nomeAlfabetico")
    public ResponseEntity<List<Produto>> nomeASC() {
        try {
            List<Produto> p = produtoService.NomeProdutosASC();
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> findByNomeProdutoContaining(@PathVariable String nome) {
        List<Produto> produtos = produtoService.findByNomeProdutoContaining(nome);
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/over50")
    public ResponseEntity<List<Produto>> getClientesOver18YearsOld() {
        try {
            List<Produto> p = produtoService.produtoApartir50();
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}




