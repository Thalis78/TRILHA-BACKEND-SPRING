package br.edu.ifpi.catce.sistemareserva.crud;

import br.edu.ifpi.catce.sistemareserva.entities.ReservaEquipamento;
import br.edu.ifpi.catce.sistemareserva.interfacesistema.ReservaEspacoDao;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEspaco;
import br.edu.ifpi.catce.sistemareserva.utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudReservaEspaco implements ReservaEspacoDao {

    @Override
    public String buscarPorNomeFuncionario(String nome) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        String reserva = "";
        try {
            List<ReservaEspaco> reservaEspacos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT r FROM ReservaEspaco r WHERE r.funcionario.nome_funcionario LIKE :nome", ReservaEspaco.class);
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
            List<ReservaEspaco> results = query.getResultList();
            for (ReservaEspaco reservaEspaco : results) {
                reserva += "ID DA RESERVA: " + reservaEspaco.getId_reserva() + ", ID DO FUNCIONARIO: " + reservaEspaco.getFuncionario().getId() + ", ID DO ESPAÇO: " + reservaEspaco.getEspaco().getId_espaco() + ", DATA RESERVA: " + reservaEspaco.getDt_reserva();
                reservaEspacos.add(reservaEspaco);
            }
        } catch (NoResultException e) {
            reserva = ("NENHUM ID DE FUNCIONARIO ENCONTRADO COM ESSE NOME!!" );
            throw e;
        } finally {
            entityManager.close();
        }
        reserva += "<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>";
        return reserva;
    }

    @Override
    public List<ReservaEspaco> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        TypedQuery<ReservaEspaco> query = entityManager.createQuery("SELECT r FROM ReservaEspaco r", ReservaEspaco.class);
        List<ReservaEspaco> reservaEspacos = query.getResultList();
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

}