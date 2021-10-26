package JUMO.project.springsecurity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JsonResultDTO {
    private boolean result; // success, fail
    private String id;
    private Long balance;
    private String message; // if fail, set


    @Override
    public String toString() {
        return "JSONResult [result=" + result + ", message=" + message + "]";
    }
}
