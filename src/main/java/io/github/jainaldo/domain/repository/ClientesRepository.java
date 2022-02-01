package io.github.jainaldo.domain.repository;

import io.github.jainaldo.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientesRepository {

//    private static String INSERT = "insert into cliente (nome) values (?)";
//    private static String SELECT_ALL = "select * from cliente";
//    private static String UPDATE = "update cliente set nome = ?  where id = ?";
//    private static String DELETE = "delete from cliente where id= ?";

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente) {
//        this.jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        this.entityManager.persist(cliente);
        return cliente;

    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
//        this.jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        this.entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente) {
//        deletar(cliente.getId());
        if (!this.entityManager.contains(cliente)) {
            cliente = this.entityManager.merge(cliente);
        }
        this.entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id) {
//        this.jdbcTemplate.update(DELETE, new Object[]{id});
        Cliente cliente = this.entityManager.find(Cliente.class, id);
        this.deletar(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos() {
//        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
        return this.entityManager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }


    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome) {
//        return jdbcTemplate.query(
//                SELECT_ALL.concat(" where nome like ?"),
//                new Object[]{"%" + nome + "%"},
//                getRowMapper());
        String jpql = "select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> query = this.entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

//    private RowMapper<Cliente> getRowMapper() {
//        return new RowMapper<Cliente>() {
//            @Override
//            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Integer id = rs.getInt("id");
//                String nome = rs.getString("nome");
//                return new Cliente(id, nome);
//            }
//
//            ;
//        };
//    }
}
