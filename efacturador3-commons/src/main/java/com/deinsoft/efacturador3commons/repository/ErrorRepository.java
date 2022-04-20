/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deinsoft.efacturador3commons.model.Error;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author EDWARD-PC
 */
public interface ErrorRepository  extends JpaRepository<Error,String>{
    @Query(value ="SELECT E FROM ERROR E WHERE COD_CATAERRO = :#{#paramError.cod_cataerro}")
    Error consultarErrorById(@Param("paramError") Error paramError);
}
