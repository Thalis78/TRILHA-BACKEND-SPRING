package Dao.CrudSistemaReserva;

import Dao.InterfaceSistemaReserva.EspacoDao;
import Entities.Espaco;
import Utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudEspaco implements EspacoDao {
    Scanner scanner = new Scanner(System.in);
    @Override
    public Espaco buscarPorNome() throws NoResultException, NonUniqueResultException, SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();

        try {
            List<Espaco> espacos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT p FROM Espaco p WHERE p.nome_espaco LIKE :nome", Espaco.class);
            System.out.println("INSIRIA O NOME DO ESPAÇO OU INICIAL: ");
            query.setParameter("nome","%"+scanner.next().toUpperCase() +"%");
            List<Espaco> results = query.getResultList();
            for (Espaco espaco : results) {
                System.out.printf("ID: %d, NOME ESPAÇO: %s, STATUS: %s%n",espaco.getId_espaco(),espaco.getNome_espaco(),espaco.getStatus());
                espacos.add(espaco);
            }
            return null;
        } catch (NoResultException e) {
            System.out.println("NENHUM ESPAÇO ENCONTRADO!!" );
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Espaco> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        List<Espaco> espacos = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM Espaco", Espaco.class);
        List<Espaco> results = query.getResultList();
        for (Espaco espaco : results) {
            System.out.printf("ID: %d, NOME ESPAÇO: %s, STATUS: %s%n",espaco.getId_espaco(),espaco.getNome_espaco(),espaco.getStatus());
            espacos.add(espaco);
        }
        return espacos;
    }

    @Override
    public void inserir(Espaco espaco) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(espaco);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("ERRO AO INSERIR O ESPACO!");
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
            Espaco espaco = entityManager.find(Espaco.class,scanner.nextInt());
            entityManager.getTransaction().begin();
            entityManager.remove(espaco);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("ALGUNS ERROS ACONTECERAM!!!");
        }finally {
            entityManager.close();
        }

    }
}