package Dao.CrudSistemaReserva;

import Dao.InterfaceSistemaReserva.FuncionarioDao;
import Entities.Espaco;
import Entities.Funcionario;
import Utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudFuncionario implements FuncionarioDao {
    static Scanner scanner = new Scanner(System.in);
    @Override
    public Funcionario buscarPorNome() throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();

        try {
            List<Funcionario> funcionarios = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT p FROM Funcionario p WHERE p.nome_funcionario LIKE :nome", Funcionario.class);
            System.out.println("INSIRIA O NOME DO FUNCIONARIO OU INICIAL: ");
            query.setParameter("nome","%"+scanner.next().toUpperCase() +"%");
            List<Funcionario> results = query.getResultList();
            for (Funcionario funcionario : results) {
                System.out.printf("ID: %d, NOME FUNCIONARIO: %s, CARGO: %s%n",funcionario.getId(),funcionario.getNome_funcionario(),funcionario.getCargo());
                funcionarios.add(funcionario);
            }
            return null;
        } catch (NoResultException e) {
            System.out.println("NENHUM FUNCIONARIO ENCONTRADO!!" );
            throw e;
        } finally {
            entityManager.close();
        }    }

    @Override
    public List<Funcionario> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        List<Funcionario> funcionarios = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM Funcionario", Funcionario.class);
        List<Funcionario> results = query.getResultList();
        for (Funcionario funcionario : results) {
            System.out.printf("ID: %d, NOME FUNCIONARIO: %s, CARGO: %s%n",funcionario.getId(),funcionario.getNome_funcionario(),funcionario.getCargo());
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

    @Override
    public void excluir() throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            buscarTodos();
            System.out.println("ID QUE VOCÊ DESEJA EXCLUIR:");
            Funcionario funcionario = entityManager.find(Funcionario.class,scanner.nextInt());
            entityManager.getTransaction().begin();
            entityManager.remove(funcionario);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("ALGUNS ERROS ACONTECERAM!!!");
        }finally {
            entityManager.close();
        }
    }
}