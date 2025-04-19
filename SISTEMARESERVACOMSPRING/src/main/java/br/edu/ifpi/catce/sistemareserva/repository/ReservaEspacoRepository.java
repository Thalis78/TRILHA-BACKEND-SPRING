package br.edu.ifpi.catce.sistemareserva.repository;

import br.edu.ifpi.catce.sistemareserva.model.ReservaEspacoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaEspacoRepository extends JpaRepository<ReservaEspacoModel, Integer> {
    @Modifying
    @Query("UPDATE ReservaEspacoModel r SET r.espaco.id_espaco = :espaco, r.funcionario.id= :func WHERE r.id_reserva = :id")
    void  updateReservaEspacoModelById_reserva(@Param("id") Integer id, @Param("espaco") Integer espaco, @Param("func") Integer func);

    @Query("SELECT r FROM ReservaEspacoModel r WHERE r.espaco.nomeEspaco LIKE %:nomeEspaco")
    Page<ReservaEspacoModel> findReservaEspacoByNomeEspaco(@Param("nomeEspaco") String nomeEspaco, Pageable pageable);
}
