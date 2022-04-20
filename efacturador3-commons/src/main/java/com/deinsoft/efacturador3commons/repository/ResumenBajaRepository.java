/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.repository;

import com.deinsoft.efacturador3commons.model.ResumenBaja;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author EDWARD-PC
 */
public interface ResumenBajaRepository extends JpaRepository<ResumenBaja,Long> {
    List<ResumenBaja> findByNombreArchivo(String nombreArchivo);
}
