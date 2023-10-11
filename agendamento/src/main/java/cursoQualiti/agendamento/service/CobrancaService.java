package moderna.agendamento.service;

import moderna.agendamento.model.Cobranca;
import moderna.agendamento.repository.CobrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CobrancaService {

    @Autowired
    private CobrancaRepository cobrancaRepository;

    public Cobranca salvarCobranca(Cobranca cobranca) throws Exception {
        var cobrancaSalva = buscarCobrancaPorId(cobranca.getId());

        if (cobrancaSalva.isEmpty()){
            return cobrancaRepository.save(cobranca);
        }else {
            throw new Exception("Cobrança existente");
        }
    }

    public Cobranca editarCobranca(Cobranca cobranca) throws Exception{
        var cobrancaEditada = buscarCobrancaPorId(cobranca.getId());

        if (cobrancaEditada.isEmpty()){
            throw new Exception("Cobrança não existe. Favor, salvá-la antes de editar.");
        }else {
            return cobrancaRepository.save(cobranca);
        }
    }

    public Optional<Cobranca> buscarPorDataVencimento(String dataVencimento) throws Exception{
        return Optional.ofNullable(cobrancaRepository.findByDataVencimento(dataVencimento).orElseThrow(()-> new Exception("Não há cobranças nessa data.")));
    }

    public void deletarPorId(Long id) throws Exception{
        var cobrancaSalva = cobrancaRepository.findById(id);

        if(cobrancaSalva.isPresent()){
            cobrancaRepository.deleteById(id);
        }else {
            throw new Exception ("Essa cobrança não existe.");
        }
    }

    private Optional<Cobranca> buscarCobrancaPorId(Long id) {
        return cobrancaRepository.findById(id);
    }

}
