package br.com.gerenciamento.repository;

import br.com.gerenciamento.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT a FROM Produto a WHERE a.status = 'ATIVO' ")
    public List<Produto> findByStatusAtivo();

    @Query("SELECT i FROM Produto i WHERE i.status = 'INATIVO' ")
    public List<Produto> findByStatusInativo();

    //@Query("SELECT c FROM Produto c WHERE c.usuario_userid = '?1' ")
    //public List<Produto> findByNomeContainingIgnoreCase(String nome);

    //@Query("SELECT i FROM Produto i WHERE i.usuario_userid = '?1' ")
    //public List<Produto> acharProdutos(String usuarioonline);

    //@Query("SELECT a FROM Produto a WHERE a.login = :login")
    public List<Produto> findByLogin(@Param("login") String login);
}
