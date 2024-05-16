package lionwebserver.lionwebserver.auth.jwt.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
