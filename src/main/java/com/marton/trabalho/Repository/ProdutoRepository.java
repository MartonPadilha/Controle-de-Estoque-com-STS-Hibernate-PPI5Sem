package com.marton.trabalho.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marton.trabalho.Model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
