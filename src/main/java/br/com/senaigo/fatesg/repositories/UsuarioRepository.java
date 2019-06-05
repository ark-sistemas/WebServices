package br.com.senaigo.fatesg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senaigo.fatesg.entity.RegistroPonto;
import br.com.senaigo.fatesg.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
