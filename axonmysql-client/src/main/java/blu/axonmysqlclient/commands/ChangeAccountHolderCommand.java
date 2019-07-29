package blu.axonmysqlclient.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class ChangeAccountHolderCommand {
    @TargetAggregateIdentifier
    private String id;
    private String accountHolder;
}
