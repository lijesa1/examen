package com.codigo.LilianaSalazar.dao;

import com.codigo.LilianaSalazar.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
