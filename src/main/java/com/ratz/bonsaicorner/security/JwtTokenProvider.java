package com.ratz.bonsaicorner.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ratz.bonsaicorner.DTO.security.TokenDTO;
import com.ratz.bonsaicorner.exceptions.InvalidJwtAuthenticationException;
import com.ratz.bonsaicorner.model.User;
import com.ratz.bonsaicorner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider implements UserDetailsService {


  Algorithm algorithm = null;

  @Value("${security.jwt.token.secret-key:secret}")
  private String secretKey = "secret";

  @Value("${security.jwt.token.expire-length:3600000}")
  private long validityInMilliseconds = 3600000; // 1h

  @Autowired
  private UserRepository userRepository;
  private UserDetailsService userDetailsService;


  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    algorithm = Algorithm.HMAC256(secretKey.getBytes());
  }

  public TokenDTO createAccessToken(String username, List<String> roles) {

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    String accessToken = getAccessToken(username, roles, now, validity);
    String refreshToken = getRefreshToken(username, roles, now);

    return new TokenDTO(username, true, now, validity, accessToken, refreshToken);
  }


  public TokenDTO refreshToken(String refreshToken) {
    if (refreshToken.contains("Bearer ")) refreshToken =
        refreshToken.substring("Bearer ".length());

    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJWT = verifier.verify(refreshToken);
    String username = decodedJWT.getSubject();
    List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

    return createAccessToken(username, roles);
  }

  private String getAccessToken(String username, List<String> roles, Date now, Date validity) {

    String issuerUrl = ServletUriComponentsBuilder
        .fromCurrentContextPath().build().toUriString();

    return JWT.create()
        .withClaim("roles", roles)
        .withIssuedAt(now)
        .withExpiresAt(validity)
        .withSubject(username)
        .withIssuer(issuerUrl)
        .sign(algorithm)
        .strip();
  }

  private String getRefreshToken(String username, List<String> roles, Date now) {

    Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));

    return JWT.create()
        .withClaim("roles", roles)
        .withIssuedAt(now)
        .withExpiresAt(validityRefreshToken)
        .withSubject(username)
        .sign(algorithm)
        .strip();
  }

  public Authentication getAuthentication(String token) {

    DecodedJWT decodedJWT = decodedToken(token);

    UserDetails userDetails = loadUserByUsername(decodedJWT.getSubject());

    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  private DecodedJWT decodedToken(String token) {

    Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
    JWTVerifier verifier = JWT.require(alg).build();
    DecodedJWT decodedJWT = verifier.verify(token);

    return decodedJWT;
  }

  public String resolveToken(HttpServletRequest req) {

    String bearerToken = req.getHeader("Authorization");

    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {

      return bearerToken.substring("Bearer ".length());
    }
    return null;
  }

  public boolean validateToken(String token) {

    DecodedJWT decodedJWT = decodedToken(token);

    try {

      if (decodedJWT.getExpiresAt().before(new Date())) {

        return false;
      }
      return true;

    } catch (Exception e) {

      throw new InvalidJwtAuthenticationException("Expired or invalid JWT token!");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByUsername(username).get();

    if (user != null) {
      return user;

    } else {

      throw new UsernameNotFoundException("User not found with the provided id.");
    }
  }
}
