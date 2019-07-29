package blu.axonmysqlclient.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class AccountCreatedEvent {
    private String id;
    private String accountHolder;
}
