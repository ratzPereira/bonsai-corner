package com.ratz.bonsaicorner.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


//  private ApiKey apiKey() {
//    return new ApiKey(APIConstants.JWT, APIConstants.AUTHORIZATION_BEARER, APIConstants.HEADER);
//  }

//  private ApiInfo apiInfo() {
//
//    return new ApiInfo(
//        APIConstants.API_INFO_TITLE,
//        APIConstants.API_INFO_DESCRIPTION,
//        APIConstants.API_INFO_VERSION,
//        APIConstants.API_INFO_TERMS_OF_SERVICE_URL,
//        new Contact(APIConstants.CONTACT_NAME, APIConstants.CONTACT_URL, APIConstants.CONTACT_EMAIL),
//        APIConstants.API_INFO_LICENCE,
//        APIConstants.API_INFO_LICENCE_URL,
//        Collections.emptyList()
//    );
//  }
//
//  @Bean
//  public Docket api() {
//
//    return new Docket(DocumentationType.SWAGGER_2)
//        .apiInfo(apiInfo())
//        //.securityContexts(Arrays.asList(securityContext()))
//        //.securitySchemes(Arrays.asList(apiKey()))
//        .select()
//        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//        .paths(PathSelectors.any())
//        .build();
//
//  }

//  private SecurityContext securityContext() {
//
//    return SecurityContext.builder().securityReferences(defaultAuth()).build();
//  }
//
//  private List<SecurityReference> defaultAuth() {
//
//    AuthorizationScope authorizationScope = new AuthorizationScope(APIConstants.AUTHORIZATION_SCOPE, APIConstants.AUTHORIZATION_SCOPE_DESCRIPTION);
//    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//    authorizationScopes[0] = authorizationScope;
//
//    return Arrays.asList(new SecurityReference(APIConstants.JWT, authorizationScopes));
//  }
}
