package com.ognice.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/22
 */
public class JWTTokenUtils {
    public static String createToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withClaim("dbfk", "hello")
                    .withIssuer("auth0")
                    .sign(algorithm);
            System.out.println(token);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt.getHeader().toString() + "--" + jwt.getPayload() + "--" + jwt.getSignature());
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void decodeToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            System.out.println(jwt.getHeader() + "--" + jwt.getPayload());
            System.out.println(jwt.getClaim("dbfk").asString());
        } catch (JWTDecodeException exception) {
            //Invalid token
        }
    }

    public static void main(String[] args) {
        JWTTokenUtils.decodeToken(JWTTokenUtils.createToken());
    }
}
