package moderna.agendamento.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moderna.agendamento.model.Agendamento;
import moderna.agendamento.repository.AgendamentoRepository;
import moderna.agendamento.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamentos")
@AllArgsConstructor
@Slf4j

public class AgendamentoController {

    private AgendamentoRepository agendamentoRepository;
    private AgendamentoService agendamentoService;

    @PostMapping("/salvar")
    public ResponseEntity<Agendamento> salvar(@RequestBody Agendamento agendamento){

        try{
            log.info("Salvando um agendamento");
            return new ResponseEntity<>(agendamentoService.salvarAgendamento(agendamento), HttpStatus.CREATED);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao salvar um agendamento");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Agendamento> editar(@RequestBody Agendamento agendamento){

        try{
            log.info("Editando um agendamento");
            return new ResponseEntity<>(agendamentoService.editarAgendamento(agendamento), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao editar um agendamento");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar-todos")
    public List<Agendamento> listarTodos(){
        return agendamentoRepository.findAll();
    }

    @GetMapping("buscar/{data}")
    public ResponseEntity<Optional<Agendamento>> buscarPorData(@PathVariable String data){

        try{
            return new ResponseEntity<>(agendamentoService.buscarPorData(data), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar-todos")
    public void deletarTodos(){
        agendamentoRepository.deleteAll();
    }

    @DeleteMapping("deletar/{data}")
    public void deletarPorData(@PathVariable String data) throws Exception {
        agendamentoService.deletarPorData(data);
    }

}
