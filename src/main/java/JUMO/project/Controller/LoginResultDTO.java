package JUMO.project.Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResultDTO {
    private boolean result;
    private String id;
    private Long balance;
    private String msg;
    private String token;
}
