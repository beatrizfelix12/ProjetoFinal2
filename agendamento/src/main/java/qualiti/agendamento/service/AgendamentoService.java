package moderna.agendamento.service;

import moderna.agendamento.model.Agendamento;
import moderna.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) throws Exception {
        var agendamentoSalvo = buscarAgendamentoPorData(agendamento.getData());

        if (agendamentoSalvo.isEmpty()){
            return agendamentoRepository.save(agendamento);
        }else {
            throw new Exception("Agendamento existente neste dia.");
        }
    }

    public Agendamento editarAgendamento(Agendamento agendamento) throws Exception{
        var agendamentoEditado = buscarAgendamentoPorData(agendamento.getData());

        if (agendamentoEditado.isEmpty()){
            throw new Exception("Agendamento inexistente. Favor, fazer o agendamento antes de editá-lo.");
        }else {
            return agendamentoRepository.save(agendamento);
        }
    }

    public Optional<Agendamento> buscarPorData(String data) throws Exception{
        return Optional.ofNullable(agendamentoRepository.findByData(data).orElseThrow(()-> new Exception("Sem agendamento.")));
    }

    public void deletarPorData(String data) throws Exception{
        var agendamentoSalvo = agendamentoRepository.findByData(data);

        if(agendamentoSalvo.isPresent()){
            agendamentoRepository.deleteByData(data);
        }else {
            throw new Exception ("Não há agendamento nesta data.");
        }
    }

    private Optional<Agendamento> buscarAgendamentoPorData(String data) {
        return agendamentoRepository.findByData(data);
    }
}
