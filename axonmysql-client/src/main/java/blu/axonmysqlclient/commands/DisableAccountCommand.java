package blu.axonmysqlclient.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class DisableAccountCommand {
    @TargetAggregateIdentifier
    private String id;
    private String status;
}
