package br.edu.ifpi.catce.sistemareserva.crud;

import br.edu.ifpi.catce.sistemareserva.interfacesistema.EspacoDao;
import br.edu.ifpi.catce.sistemareserva.entities.Espaco;
import br.edu.ifpi.catce.sistemareserva.utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudEspaco implements EspacoDao {
    @Override
    public String buscarPorNome(String nome) throws NoResultException, NonUniqueResultException, SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        String nomeEspaco = "";
        try {
            List<Espaco> espacos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT p FROM Espaco p WHERE p.nome_espaco LIKE :nome", Espaco.class);
            query.setParameter("nome","%"+nome.toUpperCase() +"%");
            List<Espaco> results = query.getResultList();
            for (Espaco espaco : results) {
                nomeEspaco += ("ID: "+ espaco.getId_espaco() + ", NOME ESPAÇO:"+espaco.getNome_espaco()+", STATUS: " + espaco.getStatus() + "<br>");
                espacos.add(espaco);
            }
        } catch (NoResultException e) {
            nomeEspaco = ("NENHUM ESPAÇO ENCONTRADO!!" );
            throw e;
        } finally {
            entityManager.close();
        }
        nomeEspaco += "<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>";
        return nomeEspaco;
    }

    @Override
    public List<Espaco> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        List<Espaco> espacos = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM Espaco", Espaco.class);
        List<Espaco> results = query.getResultList();
        for (Espaco espaco : results) {
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

}