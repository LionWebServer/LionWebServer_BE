package lionwebserver.lionwebserver.auth.controller;

import lionwebserver.lionwebserver.auth.controller.dto.SignInDTO;
import lionwebserver.lionwebserver.auth.controller.dto.SignUpDTO;
import lionwebserver.lionwebserver.auth.jwt.dto.TokenResponse;
import lionwebserver.lionwebserver.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/api/auth/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO) {
        authService.signUp(signUpDTO);
        return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
    }

    @PostMapping("/api/auth/sign-in")
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInDTO signInDTO) {
        return ResponseEntity.ok(authService.signIn(signInDTO));
    }

}
