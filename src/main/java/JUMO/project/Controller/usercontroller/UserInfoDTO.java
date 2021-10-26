package JUMO.project.Controller.usercontroller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class UserInfoDTO {
    private boolean result;
    private String id;
    private Long balance;
    private String msg;
}
