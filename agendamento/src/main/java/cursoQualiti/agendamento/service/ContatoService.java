package moderna.agendamento.service;

import moderna.agendamento.model.Contato;
import moderna.agendamento.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato salvarContato(Contato contato) throws Exception {
        var contatoSalvo = buscarContatoPorTelefone(contato.getTelefone());

        if (contatoSalvo.isEmpty()){
            return contatoRepository.save(contato);
        }else {
            throw new Exception("Contato existente. Favor, fornecer outro.");
        }
    }

    public Contato editarContato(Contato contato) throws Exception{
        var contatoEditado = buscarContatoPorTelefone(contato.getTelefone());

        if (contatoEditado.isEmpty()){
            throw new Exception("Contato não existe. Favor, salvá-lo antes de editar.");
        }else {
            return contatoRepository.save(contato);
        }
    }

    public Optional<Contato> buscarPorTelefone(String telefone) throws Exception{
        return Optional.ofNullable(contatoRepository.findByTelefone(telefone).orElseThrow(()-> new Exception("Contato não existe")));
    }

    public void deletarPorTelefone(String telefone) throws Exception{
        var contatoSalvo = contatoRepository.findByTelefone(telefone);

        if(contatoSalvo.isPresent()){
            contatoRepository.deleteByTelefone(telefone);
        }else {
            throw new Exception ("Contato não existe.");
        }
    }

    //não precisa fazer listarTodos e deletarTodos

    private Optional<Contato> buscarContatoPorTelefone(String telefone) {
        return contatoRepository.findByTelefone(telefone);
    }

}
