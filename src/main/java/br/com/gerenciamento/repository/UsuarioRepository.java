package br.com.gerenciamento.repository;

import br.com.gerenciamento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select e from Usuario e where e.email = :email")
    public Usuario findByEmail(String email);

    @Query("SELECT i from Usuario i where i.user = :user")
    public Usuario findByLogin(String user);

    @Query("select l from Usuario l where l.user = :user and l.senha = :senha")
    public Usuario buscarLogin(String user, String senha);

}
