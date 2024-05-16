package lionwebserver.lionwebserver.auth.controller.dto;

public record SignInDTO(
    String userEmail,
    String password
) {
}
