package moderna.agendamento.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moderna.agendamento.model.Endereco;
import moderna.agendamento.repository.EnderecoRepository;
import moderna.agendamento.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
@AllArgsConstructor
@Slf4j

public class EnderecoController {

    private EnderecoRepository enderecoRepository;
    private EnderecoService enderecoService;

    @PostMapping("/salvar")
    public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco){

        try{
            log.info("Salvando um endereço.");
            return new ResponseEntity<>(enderecoService.salvarEndereco(endereco), HttpStatus.CREATED);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao salvar um endereço.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Endereco> editar(@RequestBody Endereco endereco){

        try{
            log.info("Editando um endereco");
            return new ResponseEntity<>(enderecoService.editarEndereco(endereco), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao editar um endereço.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar-todos")
    public List<Endereco> listarTodos(){
        return enderecoRepository.findAll();
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<Optional<Endereco>> buscarPorId(@PathVariable Long id){

        try{
            return new ResponseEntity<>(enderecoService.buscarPorId(id), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar-todos")
    public void deletarTodos(){
        enderecoRepository.deleteAll();
    }

    @DeleteMapping("deletar/{id}")
    public void deletarPorId(@PathVariable Long id) throws Exception{
        enderecoService.deletarPorId(id);
    }

}
