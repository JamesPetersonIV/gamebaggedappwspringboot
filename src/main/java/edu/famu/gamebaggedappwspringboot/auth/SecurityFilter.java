package edu.famu.gamebaggedappwspringboot.auth;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.auth.models.Credentials;
import edu.famu.gamebaggedappwspringboot.auth.models.FirebaseUser;
import edu.famu.gamebaggedappwspringboot.auth.models.SecurityProperties;
import edu.famu.gamebaggedappwspringboot.auth.services.CookieUtils;
import edu.famu.gamebaggedappwspringboot.auth.services.SecurityService;
import edu.famu.gamebaggedappwspringboot.models.Users;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    SecurityService securityService;

    @Autowired
    SecurityProperties restSecProps;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    SecurityProperties securityProps;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        verifyToken(request);
        filterChain.doFilter(request, response);
    }

    private void verifyToken(HttpServletRequest request) throws ExecutionException, InterruptedException {
        String session = null;
        FirebaseToken decodedToken = null;
        Credentials.CredentialType type = null;
        boolean strictServerSessionEnabled = securityProps.getFirebaseProps().isEnableStrictServerSession();
        Cookie sessionCookie = cookieUtils.getCookie("session");
        String token = securityService.getBearerToken(request);
        logger.info(token);
        try {
            if (sessionCookie != null) {
                session = sessionCookie.getValue();
                decodedToken = FirebaseAuth.getInstance().verifySessionCookie(session,
                        securityProps.getFirebaseProps().isEnableCheckSessionRevoked());
                type = Credentials.CredentialType.SESSION;

            } else if (!strictServerSessionEnabled) {
                if (token != null && !token.equalsIgnoreCase("undefined")) {
                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                    type = Credentials.CredentialType.ID_TOKEN;
                }
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            log.error("Firebase Exception:: ", e.getLocalizedMessage());
        }
        FirebaseUser users = firebaseTokenToUserDto(decodedToken);
        if (users != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(users,
                    new Credentials(type, decodedToken, token, session), null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private FirebaseUser firebaseTokenToUserDto(FirebaseToken decodedToken) throws ExecutionException, InterruptedException {
        FirebaseUser users = null;
        if (decodedToken != null) {
            users = new FirebaseUser();
            users.setUid(decodedToken.getUid());
            users.setName(decodedToken.getName());
            users.setEmail(decodedToken.getEmail());
            users.setPicture(decodedToken.getPicture());
            users.setIssuer(decodedToken.getIssuer());
            users.setEmailVerified(decodedToken.isEmailVerified());

            Firestore db = FirestoreClient.getFirestore();
            Query query = db.collection("users").whereEqualTo("uid", decodedToken.getUid());
            ApiFuture<QuerySnapshot> qs = query.get();
            if(!qs.get().isEmpty())
            {
                Users u = qs.get().getDocuments().get(0).toObject(Users.class);
                users.setUsers(u);
            }
        }
        return users;
    }

}