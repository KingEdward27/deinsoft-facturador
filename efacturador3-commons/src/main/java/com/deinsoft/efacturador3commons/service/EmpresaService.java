/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.service;

import com.deinsoft.efacturador3commons.model.Empresa;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author EDWARD-PC
 */
public interface EmpresaService {
    
    public Empresa getEmpresaById(int id) ;
    public List<Empresa> getEmpresas();
    public Empresa save(Empresa empresa);
    public Empresa findByNumdoc(String numdoc);
    public Empresa findByToken(String token);
}
