package br.edu.ifpi.catce.sistemareserva.crud;

import br.edu.ifpi.catce.sistemareserva.interfacesistema.FuncionarioDao;
import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;
import br.edu.ifpi.catce.sistemareserva.utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudFuncionario implements FuncionarioDao {
    @Override
    public String buscarPorNome(String nome) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        String nomeFuncionario = "";
        try {
            List<Funcionario> funcionarios = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT p FROM Funcionario p WHERE p.nome_funcionario LIKE :nome", Funcionario.class);
            query.setParameter("nome","%"+nome.toUpperCase() +"%");
            List<Funcionario> results = query.getResultList();
            for (Funcionario funcionario : results) {
                nomeFuncionario += ("ID: "+ funcionario.getId() + ", NOME FUNCIONARIO:"+funcionario.getNome_funcionario()+", CARGO: " + funcionario.getCargo() + "<br>");
                funcionarios.add(funcionario);
            }
        } catch (NoResultException e) {
            nomeFuncionario = ("NENHUM ESPAÇO ENCONTRADO!!" );
            throw e;
        } finally {
            entityManager.close();
        }
        nomeFuncionario += "<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>";
        return nomeFuncionario;
    }

    @Override
    public List<Funcionario> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        List<Funcionario> funcionarios = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM Funcionario", Funcionario.class);
        List<Funcionario> results = query.getResultList();
        for (Funcionario funcionario : results) {
            funcionarios.add(funcionario);
        }
        return funcionarios;
    }

    @Override
    public void inserir(Funcionario funcionario) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(funcionario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("ERRO AO INSERIR O FUNCIONARIO!");
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

}