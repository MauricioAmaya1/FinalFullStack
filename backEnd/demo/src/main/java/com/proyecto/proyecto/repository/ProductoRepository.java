package com.proyecto.proyecto.repository;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findAllByCiudadId(Long id) throws ResourceNotFoundException;
    List<Producto> findAllByCategoriaId(Long id) throws ResourceNotFoundException;
    @Query(value = "SELECT * FROM productos p ORDER BY RAND() LIMIT 8", nativeQuery = true)
    List<Producto> getRandomProducts();
    @Query(value = "select * from productos where id not in (select producto_id from reservas where (fecha_inicio < :fechaInicio and fecha_final > :fechaFinal) or " +
            "(fecha_inicio between :fechaInicio and :fechaFinal) or (fecha_final between :fechaInicio and :fechaFinal))", nativeQuery = true)
    List<Producto> findAllByRangeDate(Date fechaInicio, Date fechaFinal);
    @Query(value = "select * from productos where ciudad_id = :ciudadId and id not in (select producto_id from reservas where (fecha_inicio < :fechaInicio and fecha_final > :fechaFinal) or " +
            "(fecha_inicio between :fechaInicio and :fechaFinal) or (fecha_final between :fechaInicio and :fechaFinal))", nativeQuery = true)
    List<Producto> findAllByRangeDateAndCity(Date fechaInicio, Date fechaFinal, Long ciudadId);

}


