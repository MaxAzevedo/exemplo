package br.com.exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.exemplo.model.Cliente;

/**
 * 
 * @author Max
 * 
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	/**
	 * Buscar cliente por EMAIL
	 * @param email
	 * @return
	 */
	@Query("SELECT cliente FROM Cliente cliente where cliente.email LIKE :email") 
	Cliente buscarCliente(@Param("email") String email);
}

