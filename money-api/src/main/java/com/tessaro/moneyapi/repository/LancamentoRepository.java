package com.tessaro.moneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tessaro.moneyapi.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
