/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.repository;

import com.deinsoft.efacturador3commons.model.Parametro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author EDWARD-PC
 */
public interface ParametroRepository  extends JpaRepository<Parametro,String>{
   
  @Query(value ="SELECT P FROM PARAMETRO P WHERE ID_PARA = :#{#paramParametro.id_para} AND COD_PARA = :#{#paramParametro.cod_para}")
  List<Parametro> consultarParametroById(@Param("paramParametro") Parametro paramParametro);
  
  @Query(value ="SELECT P FROM PARAMETRO P WHERE ID_PARA = :#{#paramParametro.id_para}")
  List<Parametro> consultarParametro(@Param("paramParametro") Parametro paramParametro);
  
}
