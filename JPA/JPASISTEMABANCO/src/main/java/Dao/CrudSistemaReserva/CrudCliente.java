package Dao.CrudSistemaReserva;

import Dao.InterfaceSistemaReserva.ClienteDao;
import Entities.Cliente;
import Utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudCliente implements ClienteDao {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public Cliente buscarPorNome() throws NoResultException, NonUniqueResultException, SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            System.out.println("INSIRA O NOME DO CLIENTE OU PARTE DO NOME: ");
            String nomeCliente = scanner.nextLine().toUpperCase();

            Query query = entityManager.createQuery("SELECT p FROM Cliente p WHERE UPPER(p.nome) LIKE :nome", Cliente.class);
            query.setParameter("nome", "%" + nomeCliente + "%");

            List<Cliente> resultados = query.getResultList();
            if (resultados.isEmpty()) {
                System.out.println("NENHUM CLIENTE ENCONTRADO!!");
                return null;
            } else {
                for (Cliente cliente : resultados) {
                    System.out.printf("ID DO CLIENTE: %d, NOME DO CLIENTE: %s, TELEFONE: %s%n", cliente.getId_cliente(), cliente.getNome(), cliente.getTelefone());
                }
                return resultados.get(0);
            }
        } finally {
            entityManager.close();
        }
    }


    @Override
    public List<Cliente> buscarTodos() throws NoResultException, NonUniqueResultException, SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            System.out.println("QUANTOS VOCÊ DESEJA VER??");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            Query query = entityManager.createQuery("SELECT p FROM Cliente p", Cliente.class);
            query.setMaxResults(quantidade);
            List<Cliente> resultados = query.getResultList();
            if (resultados.isEmpty()) {
                System.out.println("NENHUM CLIENTE ENCONTRADO!!");
                return new ArrayList<>();
            } else {
                for (Cliente cliente : resultados) {
                    System.out.printf("ID DO CLIENTE: %d, NOME DO CLIENTE: %s, TELEFONE: %s%n", cliente.getId_cliente(), cliente.getNome(), cliente.getTelefone());
                }
                return resultados;
            }
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void inserir(Cliente cliente) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
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
            System.out.println("INSIRA O ID DO CLIENTE QUE DESEJA EXCLUIR:");
            int id_conta = scanner.nextInt();

            entityManager.getTransaction().begin();
            Cliente cliente = entityManager.find(Cliente.class, id_conta);

            if (cliente != null) {
                entityManager.remove(cliente);
                entityManager.getTransaction().commit();
                System.out.println("CLIENTE EXCLUÍDO COM SUCESSO!");
            } else {
                System.out.println("CLIENTE NÃO ENCONTRADO!");
            }
        } finally {
            entityManager.close();
        }
    }

}
