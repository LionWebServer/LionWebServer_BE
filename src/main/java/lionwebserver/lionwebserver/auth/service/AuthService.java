package lionwebserver.lionwebserver.auth.service;

import lionwebserver.lionwebserver.auth.controller.dto.SignInDTO;
import lionwebserver.lionwebserver.auth.controller.dto.SignUpDTO;
import lionwebserver.lionwebserver.auth.domain.User;
import lionwebserver.lionwebserver.auth.exception.AuthErrorCode;
import lionwebserver.lionwebserver.auth.exception.AuthException;
import lionwebserver.lionwebserver.auth.jwt.JwtProvider;
import lionwebserver.lionwebserver.auth.jwt.dto.TokenResponse;
import lionwebserver.lionwebserver.auth.repository.UserRepository;
import lionwebserver.lionwebserver.redis.RedisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RedisDao redisDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;
    public void signUp(SignUpDTO signUpDTO) {
        String userEmail = signUpDTO.userEmail();
        String userName = signUpDTO.userName();
        String password = signUpDTO.password();


        if(userEmail == null || password == null){
            throw new AuthException(AuthErrorCode.INVALID_INPUT);
        }

        User user = new User();
        user.setUserEmail(userEmail);
        user.setUserName(userName);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    public TokenResponse signIn(SignInDTO signInDTO) {
        String userEmail = signInDTO.userEmail();
        String password = signInDTO.password();

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new AuthException(AuthErrorCode.UNAUTHORIZED));

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new AuthException(AuthErrorCode.INVALID_INPUT);
        }

        return jwtProvider.createTokens(user.getId());
    }
}
