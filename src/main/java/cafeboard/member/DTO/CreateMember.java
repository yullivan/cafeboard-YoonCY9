package cafeboard.member.DTO;

import jakarta.validation.constraints.NotNull;

public record CreateMember(
        @NotNull String name,
        @NotNull String password) {
}
