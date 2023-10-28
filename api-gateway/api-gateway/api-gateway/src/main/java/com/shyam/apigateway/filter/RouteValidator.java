package com.shyam.apigateway.filter;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
    		"/auth/register",
    		"/auth/validate",
    		"/auth/sendVerificationCode",
    		"/auth/sendResetVerificationCode",
    		"/auth/updateCustomer",
    		"/auth/verifyAccount/**",
    		"/auth/verifyAccountForPasswordReset/**",
    		"/auth/generatetoken",
    		"/cart/**",
    		"/orders/**",
    		"/products/**",
    		"/transactionService/**",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
