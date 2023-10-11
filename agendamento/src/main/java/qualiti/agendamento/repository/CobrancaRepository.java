package moderna.agendamento.repository;

import moderna.agendamento.model.Cobranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long>  {

    Optional<Cobranca> findByDataVencimento(String dataVencimento);

}
