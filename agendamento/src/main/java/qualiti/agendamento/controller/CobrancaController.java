package moderna.agendamento.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moderna.agendamento.model.Cobranca;
import moderna.agendamento.repository.CobrancaRepository;
import moderna.agendamento.service.CobrancaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cobrancas")
@AllArgsConstructor
@Slf4j

public class CobrancaController {

    private CobrancaRepository cobrancaRepository;
    private CobrancaService cobrancaService;

    @PostMapping("/salvar")
    public ResponseEntity<Cobranca> salvar(@RequestBody Cobranca cobranca){

        try{
            log.info("Salvando um cobranca");
            return new ResponseEntity<>(cobrancaService.salvarCobranca(cobranca), HttpStatus.CREATED);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao salvar uma cobrança");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Cobranca> editar(@RequestBody Cobranca cobranca){

        try{
            log.info("Editando um cobranca");
            return new ResponseEntity<>(cobrancaService.editarCobranca(cobranca), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao editar uma cobrança");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar-todas")
    public List<Cobranca> listarTodas(){
        return cobrancaRepository.findAll();
    }

    @GetMapping("buscar/{dataVencimento}")
    public ResponseEntity<Optional<Cobranca>> buscarPorDataVencimento(@PathVariable String dataVencimento){

        try{
            return new ResponseEntity<>(cobrancaService.buscarPorDataVencimento(dataVencimento), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar-todas")
    public void deletarTodas(){
        cobrancaRepository.deleteAll();
    }

    @DeleteMapping("deletar/{id}")
    public void deletarPorId(@PathVariable Long id) throws Exception {
        cobrancaService.deletarPorId(id);
    }

}
