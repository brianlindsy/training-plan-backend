package com.trainingplans.utils;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

@Component
public class GoogleUtils {
	
	@Value("${google.client.id}")
	private String clientId;

	public Payload validateAndReturnIdTokenPayload(String idTokenString) throws GeneralSecurityException, IOException {
		
		NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
		new JacksonFactory();
		JsonFactory factory = JacksonFactory.getDefaultInstance();

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
				// Specify the CLIENT_ID of the app that accesses the backend:
				.setAudience(Collections.singletonList(clientId))
				// Or, if multiple clients access the backend:
				//.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		// (Receive idTokenString by HTTPS POST)
		
		System.out.println(verifier.toString());

		GoogleIdToken idToken = verifier.verify(idTokenString);
		if (idToken != null) {
			return idToken.getPayload();

		} else {
			System.out.println("Invalid ID token.");
			return null;
		}
	}

}
