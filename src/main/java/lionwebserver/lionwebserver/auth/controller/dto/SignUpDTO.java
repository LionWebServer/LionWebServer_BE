package lionwebserver.lionwebserver.auth.controller.dto;

public record SignUpDTO(
        String userEmail,
        String userName,
        String password
) {
}

