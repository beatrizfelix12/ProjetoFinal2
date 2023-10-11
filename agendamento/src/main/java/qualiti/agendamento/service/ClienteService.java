package moderna.agendamento.service;

import moderna.agendamento.model.Cliente;
import moderna.agendamento.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) throws Exception {
       var clienteSalvo = buscarClientePorCpf(cliente.getCpf());

       if (clienteSalvo.isEmpty()){
           return clienteRepository.save(cliente);
       }else {
           throw new Exception("Cliente existente");
       }
    }

    public Cliente editarCliente(Cliente cliente) throws Exception{
        var clienteEditado = buscarClientePorCpf(cliente.getCpf());

        if (clienteEditado.isPresent()){
            return clienteRepository.save(cliente);
        }else {
            throw new Exception("Cliente não existe. Favor, salvá-lo antes de editar.");
        }
    }

    public Optional<Cliente> buscarPorCpf(String cpf) throws Exception{
        return Optional.ofNullable(clienteRepository.findByCpf(cpf).orElseThrow(()-> new Exception("Cliente não existe")));
    }

   public void deletarPorCpf(String cpf) throws Exception{
       var clienteSalvo = clienteRepository.findByCpf(cpf);

        if(clienteSalvo.isPresent()){
            clienteRepository.deleteByCpf(cpf);
        }else {
            throw new Exception ("Cliente não existe.");
        }
    }

    private Optional<Cliente> buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

}
