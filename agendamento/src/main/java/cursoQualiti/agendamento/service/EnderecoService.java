package moderna.agendamento.service;

import moderna.agendamento.model.Endereco;
import moderna.agendamento.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco salvarEndereco(Endereco endereco) throws Exception {
        var enderecoSalvo = enderecoRepository.findById(endereco.getId());

        if (enderecoSalvo.isEmpty()){
            return enderecoRepository.save(endereco);
        }else {
            throw new Exception("Endereço existente");
        }
    }

    public Endereco editarEndereco(Endereco endereco) throws Exception{
        var enderecoEditado = enderecoRepository.findById(endereco.getId());

        if (enderecoEditado.isEmpty()){
            throw new Exception("Endereço não existe. Favor, salvá-lo antes de editar.");
        }else {
            return enderecoRepository.save(endereco);
        }
    }

    public Optional<Endereco> buscarPorId(Long id) throws Exception{
        return Optional.ofNullable(enderecoRepository.findById(id)
                .orElseThrow(()-> new Exception("Endereço não existe")));
    }

    public void deletarPorId(Long id) throws Exception {
        var enderecoSalvo = enderecoRepository.findById(id);
        if (enderecoSalvo.isPresent()){
            enderecoRepository.deleteById(id);
        }else {
            throw new Exception("Endereço não existe");
        }
    }



//    private Optional<Endereco> buscarEnderecoPorRua(String rua) {
//        return enderecoRepository.findByRua(rua);
//    }

}
