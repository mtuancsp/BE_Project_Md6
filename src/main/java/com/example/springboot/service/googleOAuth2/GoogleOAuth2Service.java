package com.example.springboot.service.googleOAuth2;

import com.example.springboot.dto.response.JwtAuthenticationResponse;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.JwtService;
import com.example.springboot.service.user.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class GoogleOAuth2Service {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    private static final String CLIENT_ID = "696354615975-8ivuevdnm3gh9anklroh9kq3mfh4nkog.apps.googleusercontent.com";

    public static void test(String accessToken) {
        try {
            // Verify the Google ID token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new GsonFactory()
            )
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(accessToken);
            System.out.println(verifier);
            if (idToken != null) {
                // Token is valid
                Payload payload = idToken.getPayload();

                // Get user identifier
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);

                String email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                System.out.println("Email: " + email + "\nEmail verified: " + emailVerified + "\nName: " + name + "\nPicture URL: " + pictureUrl + "\nLocale: " + locale + "\nFamily name: " + familyName + "\nGiven name: " + givenName + "\nPhone number: ");

            } else {
                // Token is invalid
                throw new RuntimeException("Invalid Google ID token.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xác thực token: " + e.getMessage());
        }
    }

    public JwtAuthenticationResponse ggOAuth2Login(String accessToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new GsonFactory()
            )
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(accessToken);
            if (idToken != null) {
                Payload payload = idToken.getPayload();

                String email = payload.getEmail();
                Optional<User> userOptional = userRepository.findByEmail(email);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    if (user.isBlocked()) {
                        return null;
                    }
                    var response = jwtService.responseJWT(user);
                    return response;
                }

                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                User newUser = new User();
                newUser.setUsername(email);
                newUser.setEmail(email);
                newUser.setProfileImage(pictureUrl);
                newUser.setFirstName(givenName);
                newUser.setLastName(familyName);
                newUser.setAddress(locale);
                newUser.setRole(Role.USER);
                userRepository.save(newUser);

                var response = jwtService.responseJWT(newUser);
                return response;

            } else {
                // Token is invalid
                throw new RuntimeException("Invalid Google ID token.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xác thực token: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        test("eyJhbGciOiJSUzI1NiIsImtpZCI6IjkxMWUzOWUyNzkyOGFlOWYxZTlkMWUyMTY0NmRlOTJkMTkzNTFiNDQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI2OTYzNTQ2MTU5NzUtOGl2dWV2ZG5tM2doOWFua2xyb2g5a3EzbWZoNG5rb2cuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI2OTYzNTQ2MTU5NzUtOGl2dWV2ZG5tM2doOWFua2xyb2g5a3EzbWZoNG5rb2cuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDI2MDg4Mjc1MjU2NzkyMjE5NTAiLCJlbWFpbCI6Im1pbmh0dWFuMjY3QGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYmYiOjE2OTE0NTYwNzQsIm5hbWUiOiJNIFQiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFjSFR0Zkxtbk4tLUFJN0E5aHNickR3NDZCLWZ6TWNRdlN2SGlNT1RvUHprd3R6cTJFPXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6Ik0iLCJmYW1pbHlfbmFtZSI6IlQiLCJsb2NhbGUiOiJ2aSIsImlhdCI6MTY5MTQ1NjM3NCwiZXhwIjoxNjkxNDU5OTc0LCJqdGkiOiJlNDRjNzdlYmM1NjY0MmVmNTljNWZiYWFiNmQ1YzAwYWRlOGMwNTJlIn0.Kg5expRnqaNuOs3M_H0dTQcsNaZ3Q7MrYZc9h6EEqdpoQ6hel7-_SJIrVJGd1n_enQrLyH2m6UIVzoC8g6o5gBTIWKPXW1RYXoh-7U62CJxUsTCVwHfKRHCXHtTt3Nj7ZyUZDMMFg4HQlgYxO1PitWB3vzDlRkw1HAcmBtEsCG2AButCFPcRirnvVhFb9sg92SOPiur240EUpQRL9MdPSXlkf9Qr3grqHKBjzmCKrEFcZOKo62_X4PyMwHBVwoZefIm9iBBrdLwaafWw-wIEZU8oLzsJYcGsWK7EulYstpjpnIdcpzg1VuyzegMLiq39P4oCD6FdxN5rQ14UIQN2Iw");
    }
}

