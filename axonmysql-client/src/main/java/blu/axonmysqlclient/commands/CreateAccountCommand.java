package blu.axonmysqlclient.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@AllArgsConstructor
@Data
public class CreateAccountCommand {
    private String id;
    private String accountHolder;
}
