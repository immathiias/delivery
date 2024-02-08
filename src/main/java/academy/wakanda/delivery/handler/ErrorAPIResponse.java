package academy.wakanda.delivery.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ErrorAPIResponse {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String description;
}
