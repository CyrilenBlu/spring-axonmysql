package blu.axonmysqlclient.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDisabledEvent {
    private String id;
    private String status;
}
