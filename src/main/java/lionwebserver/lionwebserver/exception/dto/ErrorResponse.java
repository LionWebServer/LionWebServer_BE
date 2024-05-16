package lionwebserver.lionwebserver.exception.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        int exceptionCode,
        String message,
        LocalDateTime timestamp
) {
}
