package moderna.agendamento.repository;

import moderna.agendamento.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findByTelefone(String telefone);

    Contato deleteByTelefone(String telefone);

}
