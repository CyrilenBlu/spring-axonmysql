package blu.axonmysqlclient.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateAccountCommand {
    private String id;
    private String accountHolder;
    private String status;
}
