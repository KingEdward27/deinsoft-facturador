/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3visor.controller;

import com.bolsadeideas.springboot.app.util.paginator.PageRender;
import com.deinsoft.efacturador3commons.model.FacturaElectronica;
import com.deinsoft.efacturador3commons.model.SecRoleUser;
import com.deinsoft.efacturador3commons.model.SecUser;
import com.deinsoft.efacturador3commons.service.FacturaElectronicaService;
import com.deinsoft.efacturador3commons.service.SecUserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashMap;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author EDWARD-PC
 */
@Controller
@SessionAttributes("facturaView")
public class FacturaViewController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private FacturaElectronicaService facturaElectronicaService;

    @Autowired
    private SecUserService secUserService;

    FacturaElectronica facturaSearch;

    public FacturaViewController(){
        facturaSearch = new FacturaElectronica();
    }
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(@RequestParam(name = "page", defaultValue = "0") int page, 
            Model model,
            Authentication authentication,
            HttpServletRequest request) {
        if (authentication != null) {
            logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logger.info("Utilizando forma estática SecurityContextHolder.getContext().getAuthentication(): Usuario autenticado: ".concat(auth.getName()));
        }

        if (hasRole("ROLE_ADMIN")) {
            logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso!"));
        } else {
            logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
        }

        SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "");

        if (securityContext.isUserInRole("ROLE_ADMIN")) {
            logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(" tienes acceso!"));
        } else {
            logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
        }

        if (request.isUserInRole("ROLE_ADMIN")) {
            logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(" tienes acceso!"));
        } else {
            logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
        }

        SecUser usuario = secUserService.getSecUserByName(auth.getName());

        if (usuario == null) {
            return "/login";
        }
        List<Integer> listEmpresaIds = new ArrayList<>();
        for (SecRoleUser secRoleUser : usuario.getListSecRoleUser()) {
            listEmpresaIds.add(secRoleUser.getEmpresa().getId());
        }
        List<FacturaElectronica> list = new ArrayList<>();
        model.addAttribute("titulo", "Listado de Comprobantes");
        model.addAttribute("facturaSearch", this.facturaSearch);
        model.addAttribute("facturas", list);
        return "listar";
    }
    void verDatos(Model model,SecUser usuario){
        List<Integer> listEmpresaIds = new ArrayList<>();
        for (SecRoleUser secRoleUser : usuario.getListSecRoleUser()) {
            listEmpresaIds.add(secRoleUser.getEmpresa().getId());
        }
        List<FacturaElectronica> list = new ArrayList<>();
        if (facturaSearch.getFechaIni() == null || !StringUtils.hasText(facturaSearch.getFechaIni())) {
            model.addAttribute("titulo", "Listado de Comprobantes");
            model.addAttribute("facturas", list);
            model.addAttribute("facturaSearch", this.facturaSearch);
        }
        LocalDate date1 = LocalDate.parse(this.facturaSearch.getFechaIni(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate date2 = LocalDate.parse(this.facturaSearch.getFechaFin(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        list = facturaElectronicaService.getByFechaEmisionBetweenAndEmpresaIdIn(date1, date2, listEmpresaIds);
        model.addAttribute("facturas", list);
        model.addAttribute("facturaSearch", this.facturaSearch);
    }
    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(value = "fechaIni") String fechaIni,
            @RequestParam(value = "fechaFin") String fechaFin,
            Model model,
            Authentication authentication,
            HttpServletRequest request) {
        logger.info("wtf");
        if (authentication != null) {
            logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logger.info("Utilizando forma estática SecurityContextHolder.getContext().getAuthentication(): Usuario autenticado: ".concat(auth.getName()));
        }

        if (hasRole("ROLE_ADMIN")) {
            logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso!"));
        } else {
            logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
        }

        SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "");

        if (securityContext.isUserInRole("ROLE_ADMIN")) {
            logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(" tienes acceso!"));
        } else {
            logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
        }

        if (request.isUserInRole("ROLE_ADMIN")) {
            logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(" tienes acceso!"));
        } else {
            logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
        }

        Pageable pageRequest = PageRequest.of(page, 25);
        SecUser usuario = secUserService.getSecUserByName(auth.getName());

        if (usuario == null) {
            return "/login";
        }
        
        
        model.addAttribute("titulo", "Listado de Comprobantes");
        
        this.facturaSearch.setFechaIni(fechaIni);
        this.facturaSearch.setFechaFin(fechaFin);
        verDatos(model, usuario);
        
//        model.addAttribute("facturaSearch", this.facturaSearch);
//        model.addAttribute("facturas", list);
//        model.addAttribute("page", pageRender);
        return "listar ::lista";
    }

    @RequestMapping(value = {"/resumendiario"}, method = RequestMethod.GET)
    public String resumenDiario(Model model,
            @ModelAttribute("facturaSearch") FacturaElectronica facturaSearch,
            Authentication authentication,
            HttpServletRequest request) {
        return "listar";
    }

    @RequestMapping(value = {"/resumenbaja"}, method = RequestMethod.GET)
    public String resumenBaja(Model model,
            @ModelAttribute("facturaSearch") FacturaElectronica facturaSearch,
            Authentication authentication,
            HttpServletRequest request) {
        return "listar";
    }

    @RequestMapping(value = {"/notacredito"}, method = RequestMethod.GET)
    public String notaCredito(Model model,
            @ModelAttribute("facturaSearch") FacturaElectronica facturaSearch,
            Authentication authentication,
            HttpServletRequest request) {
        return "listar";
    }

    @RequestMapping(value = {"/factura/xml/{id}"}, method = RequestMethod.POST)
    public String genXml(@PathVariable(value = "id") Long id, RedirectAttributes flash,Model model) {
        Map<String, Object> resultado = null;
        try {
            resultado = this.facturaElectronicaService.generarComprobantePagoSunat(id);
            if(resultado != null && resultado.get("xmlHash") != null){
                model.addAttribute("success", "XML generado correctamente");
            }else{
                model.addAttribute("warning", resultado.get("code") + "\n" + resultado.get("message"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new HashMap<>();
            resultado.put("code", "003");
            resultado.put("message", e.getMessage());
            model.addAttribute("error", resultado.get("code") + "\n" + resultado.get("message"));
        }
        
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecUser usuario = secUserService.getSecUserByName(auth.getName());

        if (usuario == null) {
            return "/login";
        }
        verDatos(model, usuario);
        
        flash.addFlashAttribute("success", resultado.get("code") + "\n" + resultado.get("message"));
        return "listar :: lista";
    }

    @RequestMapping(value = {"/factura/sunat"}, method = RequestMethod.POST)
    public String sendSunat(
            @RequestParam(value = "idComprobante") long idComprobante,
            Model model,
            RedirectAttributes flash,
            Authentication authentication,
            HttpServletRequest request){
        Map<String, Object> resultado = null;
        try {
            resultado = facturaElectronicaService.sendToSUNAT(idComprobante);
            if(resultado != null && resultado.get("status") != null){
                if(resultado.get("code").toString().equals("0")){
                    model.addAttribute("success", resultado.get("status") + "\n" + resultado.get("description"));
                }else{
                    model.addAttribute("warning", resultado.get("status") + "\n" + " Código SUNAT rechazo: "+ resultado.get("code"));
                }
                
            }else{
                model.addAttribute("warning", resultado.get("code") + "\n" + resultado.get("message"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new HashMap<>();
            resultado.put("code", "003");
            resultado.put("message", e.getMessage());
            model.addAttribute("error", resultado.get("code") + "\n" + resultado.get("message"));
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecUser usuario = secUserService.getSecUserByName(auth.getName());

        if (usuario == null) {
            return "/login";
        }
        verDatos(model, usuario);
        
        return "listar :: lista";
    }

    @RequestMapping(value = {"/sendsunat"}, method = RequestMethod.GET)
    public String sendSunatMasivo(Model model,
            @ModelAttribute("facturaSearch") FacturaElectronica facturaSearch,
            Authentication authentication,
            HttpServletRequest request) {
        return "redirect:/listar";
    }

    //            Page<FacturaElectronica> pageList = listConvertToPage1(list, pageRequest);
//            PageRender<FacturaElectronica> pageRender = new PageRender<FacturaElectronica>("/listar", pageList);
//            model.addAttribute("page", pageRender);
    public static <T> Page<T> listConvertToPage1(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = (int) (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }

    private boolean hasRole(String role) {

        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null) {
            return false;
        }

        Authentication auth = context.getAuthentication();

        if (auth == null) {
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        return authorities.contains(new SimpleGrantedAuthority(role));

        /*
		 * for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				logger.info("Hola usuario ".concat(auth.getName()).concat(" tu role es: ".concat(authority.getAuthority())));
				return true;
			}
		}
		
		return false;
         */
    }

    public FacturaElectronica getFacturaSearch() {
        return facturaSearch;
    }

    public void setFacturaSearch(FacturaElectronica facturaSearch) {
        this.facturaSearch = facturaSearch;
    }

}
