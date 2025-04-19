package Dao.CrudSistemaReserva;

import Dao.InterfaceSistemaReserva.ContaDao;
import Entities.Cliente;
import Entities.Conta;
import Utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudConta implements ContaDao {
    static Scanner scanner = new Scanner(System.in);
    @Override
    public Conta buscarPorID() throws NoResultException, NonUniqueResultException, SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            System.out.println("INSIRA O ID DA CONTA:");
            int idConta = scanner.nextInt();

            Query query = entityManager.createQuery("SELECT p FROM Conta p WHERE p.id_conta = :id", Conta.class);
            query.setParameter("id", idConta);
            List<Conta> results = query.getResultList();

            if (results.isEmpty()) {
                System.out.println("NENHUMA CONTA ENCONTRADA!!");
                throw new NoResultException("Nenhuma conta encontrada para o ID fornecido.");
            }

            Conta conta = results.get(0);
            System.out.printf("ID DA CONTA: %d, ID DO CLIENTE: %d, AGENCIA: %s, CONTA: %s, TIPO DE CONTA: %s%nCONTA ENCONTRADA!!%n%n",
                    conta.getId_conta(),
                    conta.getId_cliente().getId_cliente(),
                    conta.getAgencia(),
                    conta.getConta(),
                    conta.getTipoConta());
            return conta;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Conta> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        List<Conta> contas = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT c FROM Conta c", Conta.class);
            contas = query.getResultList();
            for (Conta conta: contas) {
                System.out.printf("ID DA CONTA: %d, ID DO CLIENTE: %d, AGENCIA: %s, CONTA: %s, TIPO DE CONTA: %s%n",
                        conta.getId_conta(),
                        conta.getId_cliente().getId_cliente(),
                        conta.getAgencia(),
                        conta.getConta(),
                        conta.getTipoConta());
            }
        } finally {
            entityManager.close();
        }
        return contas;
    }


    @Override
    public void inserir(Conta conta) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(conta);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void excluir() throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            System.out.println("INSIRA O ID DA CONTA QUE DESEJA EXCLUIR:");
            int id_conta = scanner.nextInt();

            entityManager.getTransaction().begin();
            Conta conta = entityManager.find(Conta.class, id_conta);

            if (conta != null) {
                entityManager.remove(conta);
                entityManager.getTransaction().commit();
                System.out.println("CONTA EXCLUÍDA COM SUCESSO!");
            } else {
                System.out.println("CONTA NÃO ENCONTRADA!");
            }
        } finally {
            entityManager.close();
        }
    }
}
