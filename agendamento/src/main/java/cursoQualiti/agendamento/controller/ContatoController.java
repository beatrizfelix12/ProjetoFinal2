package moderna.agendamento.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moderna.agendamento.model.Contato;
import moderna.agendamento.repository.ContatoRepository;
import moderna.agendamento.service.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
@AllArgsConstructor
@Slf4j

public class ContatoController {

    private ContatoRepository contatoRepository;
    private ContatoService contatoService;

    @PostMapping("/salvar")
    public ResponseEntity<Contato> salvar(@RequestBody Contato contato){

        try{
            log.info("Salvando um contato.");
            return new ResponseEntity<>(contatoService.salvarContato(contato), HttpStatus.CREATED);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao salvar um contato");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Contato> editar(@RequestBody Contato contato){

        try{
            log.info("Editando um contato");
            return new ResponseEntity<>(contatoService.editarContato(contato), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao editar um contato");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar-todos")
    public List<Contato> listarTodos(){
        return contatoRepository.findAll();
    }

    @GetMapping("buscar/{telefone}")
    public ResponseEntity<Optional<Contato>> buscarPorTelefone(@PathVariable String telefone){

        try{
            return new ResponseEntity<>(contatoService.buscarPorTelefone(telefone), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar-todos")
    public void deletarTodos(){
        contatoRepository.deleteAll();
    }

    @DeleteMapping("deletar/{telefone}")
    public void deletarPorTelefone(@PathVariable String telefone) throws Exception {
        contatoService.deletarPorTelefone(telefone);
    }
}

