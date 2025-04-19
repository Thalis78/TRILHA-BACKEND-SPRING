package br.edu.ifpi.catce.sistemareserva.crud;

import br.edu.ifpi.catce.sistemareserva.interfacesistema.ReservaEquipamentoDao;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEquipamento;
import br.edu.ifpi.catce.sistemareserva.utils.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudReservaEquipamento implements ReservaEquipamentoDao {
    @Override
    public String buscarPorNomeFuncionario(String nome) throws SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        String Reserva = "";
        try {
            List<ReservaEquipamento> reservaEquipamentos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT r FROM Reserva r WHERE r.funcionario.nome_funcionario LIKE :nome", ReservaEquipamento.class);
            System.out.println("INSIRA O NOME DO FUNCIONARIO OU INICIAL: ");
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
            List<ReservaEquipamento> results = query.getResultList();
            for (ReservaEquipamento reservaEquipamento : results) {
                Reserva += ("ID RESERVA: "+reservaEquipamento.getId_reserva() + ", ID FUNCIONARIO: "+ reservaEquipamento.getFuncionario().getId() +", ID EQUIPAMENTO: " + reservaEquipamento.getEquipamento().getId_equipamento()) + ", DATA RESERVA: " + reservaEquipamento.getDt_reserva();
                reservaEquipamentos.add(reservaEquipamento);
            }
        } catch (NoResultException e) {
            Reserva = ("NENHUM ID DE FUNCIONARIO ENCONTRADO COM ESSE NOME!!" );
            throw e;
        } finally {
            entityManager.close();
        }
        Reserva += "<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>";
        return Reserva;
    }

    @Override
    public List<ReservaEquipamento> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        TypedQuery<ReservaEquipamento> query = entityManager.createQuery("SELECT r FROM ReservaEquipamento r", ReservaEquipamento.class);
        List<ReservaEquipamento> reservaEquipamentos = query.getResultList();
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

}