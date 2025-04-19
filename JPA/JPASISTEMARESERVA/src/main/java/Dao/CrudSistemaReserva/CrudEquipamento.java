package Dao.CrudSistemaReserva;

import Dao.InterfaceSistemaReserva.EquipamentoDao;
import Entities.Equipamento;
import Utils.Hibernate;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudEquipamento implements EquipamentoDao {
    static Scanner scanner = new Scanner(System.in);


    @Override
    public Equipamento buscarPorNome() throws NoResultException, NonUniqueResultException, SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();

        try {
            List<Equipamento> equipamentos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT p FROM Equipamento p WHERE p.nome_equipamento LIKE :nome", Equipamento.class);
            System.out.println("INSIRIA O NOME DO EQUIPAMENTO OU INICIAL: ");
            query.setParameter("nome","%"+scanner.next().toUpperCase() +"%");
            List<Equipamento> results = query.getResultList();
            for (Equipamento equipamento : results) {
                System.out.printf("ID: %d, NOME EQUIPAMENTO: %s, QUANTIDADE DISPONIVEL: %d%n",equipamento.getId_equipamento(),equipamento.getNome_equipamento(),equipamento.getQuantidade_disponivel());
                equipamentos.add(equipamento);
            }
            return null;
        } catch (NoResultException e) {
            System.out.println("NENHUM EQUIPAMENTO ENCONTRADO!!" );
            throw e;
        } finally {
            entityManager.close();
        }
    }


    @Override
    public List<Equipamento> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        List<Equipamento> equipamentos = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM Equipamento", Equipamento.class);
        List<Equipamento> results = query.getResultList();
        for (Equipamento equipamento : results) {
            System.out.printf("ID: %d, NOME EQUIPAMENTO: %s, QUANTIDADE DISPONIVEL: %d%n",equipamento.getId_equipamento(),equipamento.getNome_equipamento(),equipamento.getQuantidade_disponivel());
            equipamentos.add(equipamento);
        }
        return equipamentos;
    }


    @Override
    public void inserir(Equipamento equipamento) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(equipamento);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("ERRO AO INSERIR O EQUIPAMENTO!");
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
            Equipamento equipamento = entityManager.find(Equipamento.class,scanner.nextInt());
            entityManager.getTransaction().begin();
            entityManager.remove(equipamento);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("ALGUNS ERROS ACONTECERAM!!!");
        }finally {
            entityManager.close();
        }
    }

}




