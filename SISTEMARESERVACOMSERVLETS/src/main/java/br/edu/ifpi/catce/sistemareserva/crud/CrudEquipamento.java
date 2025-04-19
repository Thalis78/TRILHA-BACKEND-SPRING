package br.edu.ifpi.catce.sistemareserva.crud;

import br.edu.ifpi.catce.sistemareserva.interfacesistema.EquipamentoDao;
import br.edu.ifpi.catce.sistemareserva.entities.Equipamento;
import br.edu.ifpi.catce.sistemareserva.utils.Hibernate;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CrudEquipamento implements EquipamentoDao {

    @Override
    public String buscarPorNome(String nome) throws NoResultException, NonUniqueResultException, SQLException {
        EntityManager entityManager = Hibernate.getEntityManager();
        String nomeEquipamentos = "";
        try {
            List<Equipamento> equipamentos = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT p FROM Equipamento p WHERE p.nome_equipamento LIKE :nome", Equipamento.class);
            query.setParameter("nome","%"+nome.toUpperCase() +"%");
            List<Equipamento> results = query.getResultList();
            for (Equipamento equipamento : results) {
                nomeEquipamentos += ( "ID: "+equipamento.getId_equipamento()+" NOME EQUIPAMENTO: "+equipamento.getNome_equipamento()+", QUANTIDADE DISPONIVEL: "+equipamento.getQuantidade_disponivel()+"<br>");
                equipamentos.add(equipamento);
            }
        } catch (NoResultException e) {
            nomeEquipamentos = ("NENHUM EQUIPAMENTO ENCONTRADO!!" );
            throw e;
        } finally {
            entityManager.close();
        }
        nomeEquipamentos += "<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>";
        return nomeEquipamentos;
    }


    @Override
    public List<Equipamento> buscarTodos() {
        EntityManager entityManager = Hibernate.getEntityManager();
        List<Equipamento> equipamentos = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM Equipamento", Equipamento.class);
        List<Equipamento> results = query.getResultList();
        for (Equipamento equipamento : results) {
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


}




