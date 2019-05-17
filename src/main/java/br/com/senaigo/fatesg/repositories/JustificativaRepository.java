package br.com.senaigo.fatesg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senaigo.fatesg.entity.Justificativa;

@Repository
public interface JustificativaRepository extends JpaRepository<Justificativa, Long>{

}
