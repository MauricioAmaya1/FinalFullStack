package com.proyecto.proyecto.repository;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query(value = "select count(categorias.id)  from categorias left join productos on categorias.id = productos.categoria_id group by categorias.id" , nativeQuery = true)
    Optional<List<Integer>> listaCantidadCategorias() throws ResourceNotFoundException;
}
