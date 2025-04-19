package br.edu.ifpi.catce.sistemareserva.repository;

import br.edu.ifpi.catce.sistemareserva.model.EquipamentoModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, Integer> {

    @Modifying
    @Query("UPDATE EquipamentoModel e SET e.nomeEquipamento = :nome, e.quantidade_disponivel = :quant WHERE e.id_equipamento = :id")
    void updateEquipamentoModelById(@Param("id") Integer id, @Param("nome") String nome, @Param("quant") int quant);

    Page<EquipamentoModel> findByNomeEquipamento(String nomeEquipamento, Pageable pageable);

}
