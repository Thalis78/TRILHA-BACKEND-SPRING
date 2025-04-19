package Dao.CrudSistemaReserva;

import Dao.InterfaceSistemaReserva.ReservaEspacoDao;
import Entities.ReservaEquipamento;
import Entities.ReservaEspaco;
import Model.ReservaEspacoModel;
import Utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudReservaEspaco implements ReservaEspacoDao {
    Scanner scanner = new Scanner(System.in);
    @Override
    public ReservaEspaco buscarPorData() throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();

        try {
            List<ReservaEspaco> reservaEspacos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT r FROM ReservaEspaco r WHERE r.dt_reserva LIKE :dt", ReservaEspaco.class);
            System.out.println("INSIRA UMA DATA OU ANO: ");
            query.setParameter("dt", "%" + scanner.next() + "%");
            List<ReservaEspaco> results = query.getResultList();
            for (ReservaEspaco reservaEspaco : results) {
                System.out.printf("TIPO DE RESERVA: %s,DATA DA RESERVA: %s , ID RESERVA: %s, NOME FUNCIONARIO: %s, ID ESPACO: %d%n",
                        "ESPACO",
                        reservaEspaco.getDt_reserva(),
                        reservaEspaco.getId_reserva(),
                        reservaEspaco.getFuncionario().getNome_funcionario(),
                        reservaEspaco.getEspaco().getId_espaco());
                reservaEspacos.add(reservaEspaco);
            }
            return null;
        } catch (NoResultException e) {
            System.out.println("NENHUMA DATA ENCONTRADO!!" );
            throw e;
        } finally {
            entityManager.close();
        }    }

    @Override
    public ReservaEspaco buscarPorNomeFuncionario() throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();

        try {
            List<ReservaEspaco> reservaEspacos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT r FROM ReservaEspaco r WHERE r.funcionario.nome_funcionario LIKE :nome", ReservaEspaco.class);
            System.out.println("INSIRA O NOME DO FUNCIONARIO OU INICIAL: ");
            query.setParameter("nome", "%" + scanner.next().toUpperCase() + "%");
            List<ReservaEspaco> results = query.getResultList();
            for (ReservaEspaco reservaEspaco : results) {
                System.out.printf("TIPO DE RESERVA: %s,DATA DA RESERVA: %s , ID RESERVA: %s, NOME FUNCIONARIO: %s, ID ESPACO: %d%n",
                        "ESPACO",
                        reservaEspaco.getDt_reserva(),
                        reservaEspaco.getId_reserva(),
                        reservaEspaco.getFuncionario().getNome_funcionario(),
                        reservaEspaco.getEspaco().getId_espaco());
                reservaEspacos.add(reservaEspaco);
            }
            return null;
        } catch (NoResultException e) {
            System.out.println("NENHUM ID DE FUNCIONARIO ENCONTRADO COM ESSE NOME!!" );
            throw e;
        } finally {
            entityManager.close();
        }    }

    @Override
    public List<ReservaEspaco> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        TypedQuery<ReservaEspaco> query = entityManager.createQuery("SELECT r FROM ReservaEspaco r", ReservaEspaco.class);
        List<ReservaEspaco> reservaEspacos = query.getResultList();

        for (ReservaEspaco reservaEspaco : reservaEspacos) {
            System.out.printf("TIPO DE RESERVA: %s,DATA DA RESERVA: %s , ID RESERVA: %s, NOME FUNCIONARIO: %s, ID ESPACO: %d%n",
                    "ESPACO",
                    reservaEspaco.getDt_reserva(),
                    reservaEspaco.getId_reserva(),
                    reservaEspaco.getFuncionario().getNome_funcionario(),
                    reservaEspaco.getEspaco().getId_espaco());
        }

        return reservaEspacos;
    }

    @Override
    public void inserir(ReservaEspaco reservaEspaco) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(reservaEspaco);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("ERRO AO INSERIR A RESERVA DE ESPAÇO!");
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
            ReservaEspaco reservaEspaco = entityManager.find(ReservaEspaco.class,scanner.nextInt());
            entityManager.getTransaction().begin();
            entityManager.remove(reservaEspaco);
            entityManager.getTransaction().commit();

        }catch (Exception e){
            System.out.println("ALGUNS ERROS ACONTECERAM!!!");
        }finally {
            entityManager.close();
        }

    }
}