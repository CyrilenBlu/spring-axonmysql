package blu.axonmysqlclient.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeAccountHolderEvent {
    private String id;
    private String accountHolder;
}
