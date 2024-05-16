package lionwebserver.lionwebserver.auth.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lionwebserver.lionwebserver.auth.exception.AuthErrorCode;
import lionwebserver.lionwebserver.auth.exception.AuthException;
import lionwebserver.lionwebserver.auth.support.AuthenticationContext;
import lionwebserver.lionwebserver.auth.support.AuthenticationExtractor;
import lionwebserver.lionwebserver.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class SignInInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final AuthenticationContext authenticationContext;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = AuthenticationExtractor.extractAccessToken(request)
                .orElseThrow(() -> new AuthException(AuthErrorCode.UNAUTHORIZED));

        Long userId = jwtProvider.extractId(accessToken);
        authenticationContext.setAuthentication(userId);

        return true;
    }
}
