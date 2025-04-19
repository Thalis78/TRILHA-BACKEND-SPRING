package Dao.CrudSistemaReserva;

import Dao.InterfaceSistemaReserva.ReservaEquipamentoDao;
import Entities.Funcionario;
import Entities.ReservaEquipamento;
import Utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudReservaEquipamento implements ReservaEquipamentoDao {
    Scanner scanner = new Scanner(System.in);
    @Override
    public ReservaEquipamento buscarPorData() throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();

        try {
            List<ReservaEquipamento> reservaEquipamentos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT r FROM ReservaEquipamento r WHERE r.dt_reserva LIKE :dt", ReservaEquipamento.class);
            System.out.println("INSIRA UMA DATA OU ANO: ");
            query.setParameter("dt", "%" + scanner.next() + "%");
            List<ReservaEquipamento> results = query.getResultList();
            for (ReservaEquipamento reservaEquipamento : results) {
                System.out.printf("TIPO DE RESERVA: %s,DATA DA RESERVA: %s , ID RESERVA: %s, NOME FUNCIONARIO: %s, ID EQUIPAMENTO: %d%n",
                        "EQUIPAMENTO",
                        reservaEquipamento.getDt_reserva(),
                        reservaEquipamento.getId_reserva(),
                        reservaEquipamento.getFuncionario().getNome_funcionario(),
                        reservaEquipamento.getEquipamento().getId_equipamento());
                reservaEquipamentos.add(reservaEquipamento);
            }
            return null;
        } catch (NoResultException e) {
            System.out.println("NENHUM DATA ENCONTRADO!!" );
            throw e;
        } finally {
            entityManager.close();
        }    }

    @Override
    public ReservaEquipamento buscarPorNomeFuncionario() throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();

        try {
            List<ReservaEquipamento> reservaEquipamentos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT r FROM ReservaEquipamento r WHERE r.funcionario.nome_funcionario LIKE :nome", ReservaEquipamento.class);
            System.out.println("INSIRA O NOME DO FUNCIONARIO OU INICIAL: ");
            query.setParameter("nome", "%" + scanner.next().toUpperCase() + "%");
            List<ReservaEquipamento> results = query.getResultList();
            for (ReservaEquipamento reservaEquipamento : results) {
                System.out.printf("TIPO DE RESERVA: %s,DATA DA RESERVA: %s , ID RESERVA: %d, NOME FUNCIONARIO: %s, ID EQUIPAMENTO: %d%n",
                        "EQUIPAMENTO",
                        reservaEquipamento.getDt_reserva(),
                        reservaEquipamento.getId_reserva(),
                        reservaEquipamento.getFuncionario().getNome_funcionario(),
                        reservaEquipamento.getEquipamento().getId_equipamento());
                reservaEquipamentos.add(reservaEquipamento);
            }
            return null;
        } catch (NoResultException e) {
            System.out.println("NENHUM ID DE FUNCIONARIO ENCONTRADO COM ESSE NOME!!" );
            throw e;
        } finally {
            entityManager.close();
        }    }

    @Override
    public List<ReservaEquipamento> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        TypedQuery<ReservaEquipamento> query = entityManager.createQuery("SELECT r FROM ReservaEquipamento r", ReservaEquipamento.class);
        List<ReservaEquipamento> reservaEquipamentos = query.getResultList();

        for (ReservaEquipamento reservaEquipamento : reservaEquipamentos) {
            System.out.printf("TIPO DE RESERVA: %s, DATA DA RESERVA: %s, ID RESERVA: %d, NOME FUNCIONARIO: %s, ID EQUIPAMENTO: %d%n",
                    "EQUIPAMENTO",
                    reservaEquipamento.getDt_reserva(),
                    reservaEquipamento.getId_reserva(),
                    reservaEquipamento.getFuncionario().getNome_funcionario(),
                    reservaEquipamento.getEquipamento().getId_equipamento());
        }
        return reservaEquipamentos;
    }


    @Override
    public void inserir(ReservaEquipamento reservaEquipamento) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(reservaEquipamento);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("ERRO AO INSERIR A RESERVA DE EQUIPAMENTO!");
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
            ReservaEquipamento reservaEquipamento = entityManager.find(ReservaEquipamento.class,scanner.nextInt());
            entityManager.getTransaction().begin();
            entityManager.remove(reservaEquipamento);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("ALGUNS ERROS ACONTECERAM!!!");
        }finally {
            entityManager.close();
        }

    }
}