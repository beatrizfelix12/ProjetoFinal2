package moderna.agendamento.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moderna.agendamento.model.Cliente;
import moderna.agendamento.repository.ClienteRepository;
import moderna.agendamento.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
@Slf4j

public class ClienteController {

    private ClienteRepository clienteRepository;
    private ClienteService clienteService;


    @PostMapping("/salvar")
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){

        try{
            log.info("Salvando um cliente", cliente.getNome());
            return new ResponseEntity<>(clienteService.salvarCliente(cliente), HttpStatus.CREATED);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao salvar um cliente");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Cliente> editar(@RequestBody Cliente cliente){

        try{
            log.info("Editando um cliente");
            return new ResponseEntity<>(clienteService.editarCliente(cliente), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            log.info("Erro ao editar um cliente");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar-todos")
    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    @GetMapping("buscar/{cpf}")
    public ResponseEntity<Optional<Cliente>> buscarPorCpf(@PathVariable String cpf){

        try{
            return new ResponseEntity<>(clienteService.buscarPorCpf(cpf), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar-todos")
    public void deletarTodos(){
        clienteRepository.deleteAll();
    }

    @DeleteMapping("deletar/{cpf}")
    public void deletarPorCpf(@PathVariable String cpf) throws Exception {
        clienteService.deletarPorCpf(cpf);
    }

}
