package com.anto.security.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import org.springframework.security.oauth2.provider.token.TokenStore;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Resource(name = "tokenStore")
	TokenStore tokenStore;

//	@Resource(name = "tokenServices")
//	ConsumerTokenServices tokenServices;

	@GetMapping("/user")
	public Principal retrievePrincipal(Principal principal) {
		return principal;
	}
	
	@GetMapping("/username")
	public String retrieveUsername(Principal principal) {
		return principal.getName();
	}

//	@PreAuthorize("#oauth2.hasScope('write')")
//	@GetMapping("/scope")
//	public String prueba() {
//		return "Pasamos la prueba";
//	}

	@GetMapping("/tokens")
	public List<String> getTokens(String client_id, String user) {
		List<String> tokenValues = new ArrayList<String>();
		Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(client_id, user);
		if (tokens != null) {
			for (OAuth2AccessToken token : tokens) {
				tokenValues.add(token.getValue());
			}
		}
		return tokenValues;
	}

//	@PostMapping("/token/revoke")
//	public String revokeToken(@RequestHeader("Authorization") String auth) {
//		tokenServices.revokeToken(auth.substring(7));
//		return auth.substring(7);
//	}

}