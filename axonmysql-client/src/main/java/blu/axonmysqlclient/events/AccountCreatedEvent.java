package blu.axonmysqlclient.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountCreatedEvent {
    private String id;
    private String accountHolder;
    private String status;
}
