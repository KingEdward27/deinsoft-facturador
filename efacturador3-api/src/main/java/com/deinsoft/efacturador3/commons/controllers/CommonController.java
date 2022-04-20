package com.deinsoft.efacturador3.commons.controllers;

import com.deinsoft.efacturador3.commons.service.CommonService;
import java.util.HashMap;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import com.deinsoft.efacturador3commons.service.*;

public class CommonController<E, S extends CommonService<E>> {

    @Autowired
    protected S service;

//    @Autowired
//    private Util util;
//
//    @Autowired
//    AuthenticationHelper auth;
//
//    @Autowired
//    private SecAuditlogService secAuditLogService;
//
//    @Autowired
//    private SecActionService secActionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/pagina")
	public ResponseEntity<?> listar(Pageable pageable){
		return ResponseEntity.ok().body(service.findAll(pageable));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<E> o = service.findById(id);
		if(o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
//    public ResponseEntity<?> viewList(List<E> entity, String nodeAction, HttpServletRequest request) {
//        LOGGER.info("Orgs: {}", util.getSession(request).getOrgs());
//        LOGGER.info("Companies: {}", util.getSession(request).getCompanies());
//        if (util.verifyNodeActions(nodeAction, request)) {
//            return ResponseEntity.status(HttpStatus.OK).body(entity);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tiene acceso a este recurso");
//    }
//
//    public ResponseEntity<?> view(E entity, String nodeAction, HttpServletRequest request) {
//
//        if (util.verifyNodeActions(nodeAction, request)) {
//            return ResponseEntity.status(HttpStatus.OK).body(entity);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tiene acceso a este recurso");
//    }

    public ResponseEntity<?> create(long rowId, @Valid @RequestBody E entity, String nodeAction, BindingResult result,
            HttpServletRequest request) {
//        LOGGER.info("Orgs: {}", util.getSession(request).getOrgs());
//        LOGGER.info("Companies: {}", util.getSession(request).getCompanies());
//        if (util.verifyNodeActions(nodeAction, request)) {
            if (result.hasErrors()) {
                return this.validar(result);
            }
//            String[] nodeActionSplitted = nodeAction.split("_");
//            if (util.verifyAuditable(nodeActionSplitted[0].toString())) {
//                saveAuditLog(rowId, entity, nodeAction);
//            }
            E entityDb = service.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
//        }

    }

    public ResponseEntity<?> delete(Long id, String nodeAction, HttpServletRequest request) {
//        LOGGER.info("Orgs: {}", util.getSession(request).getOrgs());
//        LOGGER.info("Companies: {}", util.getSession(request).getCompanies());
//        if (util.verifyNodeActions(nodeAction, request)) {
//            String[] nodeActionSplitted = nodeAction.split("_");
//            if (util.verifyAuditable(nodeActionSplitted[0].toString())) {
//                saveAuditLog(id, service.findById(id).get(), nodeAction);
//            }
            service.deleteById(id);
            return ResponseEntity.noContent().build();
//        }
    }

//    public ResponseEntity<?> print(String nodeAction, HttpServletRequest request, HttpHeaders headers,
//            ByteArrayInputStream stream) {
//        LOGGER.info("Orgs: {}", util.getSession(request).getOrgs());
//        LOGGER.info("Companies: {}", util.getSession(request).getCompanies());
//        if (util.verifyNodeActions(nodeAction, request)) {
//            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
//                    .body(new InputStreamResource(stream));
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tiene acceso a este recurso");
//    }

    public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result) {

        if (result.hasErrors()) {
            return this.validar(result);
        }
        E entityDb = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
    }

//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> eliminar(@PathVariable Long id){
//		service.deleteById(id);
//		return ResponseEntity.noContent().build();
//	}
    protected ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
//		final List<String> errors = result.getAllErrors().stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
    }

//    public boolean validatePermissionComplexMethods(String node, long id, HttpServletRequest request) {
//        LOGGER.info("Orgs: {}", util.getSession(request).getOrgs());
//        LOGGER.info("Companies: {}", util.getSession(request).getCompanies());
//        node = node + "_add";
//        if (id > 0) {
//            // edit
//            node = node + "_edit";
//        }
//        if (!util.verifyNodeActions(node, request)) {
//            return false;
//        }
//        return true;
//    }
//
//    public boolean validatePermissionComplexMethods(String node, HttpServletRequest request) {
//        LOGGER.info("Orgs: {}", util.getSession(request).getOrgs());
//        LOGGER.info("Companies: {}", util.getSession(request).getCompanies());
//        if (!util.verifyNodeActions(node, request)) {
//            return false;
//        }
//        return true;
//    }
//
//    public void saveAuditLog(long rowId, @Valid @RequestBody E entity, String nodeAction) {
//        Date created = new Date();
//        String createdby = auth.getLoggedUserdata().getName();
//        String tablename = entity.getClass().getName();
//        byte[] prevdata = null;
//        E entityTwo = null;
//        if (rowId > 0) {
//            entityTwo = service.findById(rowId).get();
//        }
//        if (entityTwo != null) {
//            prevdata = entityTwo.toString().getBytes();
//        }
//        byte[] newdata = entity.toString().getBytes();
//        String[] nodeActionSplitted = nodeAction.split("_");
//        String action = nodeActionSplitted[1].toString();
//        SecAction secAction = new SecAction();
//        secAction.setName(action);
//
//        LOGGER.info("newdata: {}", newdata);
//
//        List<SecAction> secActionTwo = secActionService.getSecActionByName(secAction);
//
//        long numberAction = secActionTwo.get(0).getId();
//        SecAuditlog secAuditLog = new SecAuditlog();
//        secAuditLog.setCreated(created);
//        secAuditLog.setCreatedby(createdby);
//        secAuditLog.setTablename(tablename);
//        secAuditLog.setPrevdata(prevdata);
//        secAuditLog.setNewdata(newdata);
//        secAuditLog.setAction(numberAction);
//        secAuditLog.setRowid(rowId);
//        // secAuditLog.setRowname(entity.toString());
//        secAuditLogService.save(secAuditLog);
//    }

}
