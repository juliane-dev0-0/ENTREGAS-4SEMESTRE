package biblioteca.controller;

import biblioteca.entity.Livro;
import biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/listAll")
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @GetMapping("/findById/{idLivro}")
    public ResponseEntity<Livro> findById(@PathVariable long idLivro) {
        Optional<Livro> livro = livroRepository.findById(idLivro);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Livro livro) {
        livroRepository.save(livro);
        return ResponseEntity.ok("Livro adicionado com sucesso!");
    }

    @PutMapping("/update/{idLivro}")
    public ResponseEntity<String> update(@PathVariable long idLivro, @RequestBody Livro livroAtualizado) {
        Optional<Livro> livro = livroRepository.findById(idLivro);
        if (livro.isPresent()) {
            livroAtualizado.setIdLivro(idLivro);
            livroRepository.save(livroAtualizado);
            return ResponseEntity.ok("Livro atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{idLivro}")
    public ResponseEntity<String> delete(@PathVariable Long idLivro) {
        Optional<Livro> livro = livroRepository.findById(idLivro);
        if (livro.isPresent()) {
            livroRepository.delete(livro.get());
            return ResponseEntity.ok("Livro "+  "deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
