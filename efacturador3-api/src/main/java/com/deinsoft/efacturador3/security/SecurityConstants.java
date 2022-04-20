/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3.security;

/**
 *
 * @author EDWARD-PC
 */
public class SecurityConstants {
    public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer";
	
	public static final String ADMIN = "admin";
	
	public static final String ISSUER_INFO = "DEINSOFT";
	public static final String SUPER_SECRET_KEY = "Un4cL4V3C0mpl1c4d4";
	public static final long TOKEN_EXPIRATION_TIME = 30; // minutes
	public static final long TOKEN_REFRESH_EXPIRATION_TIME = 180;
}
