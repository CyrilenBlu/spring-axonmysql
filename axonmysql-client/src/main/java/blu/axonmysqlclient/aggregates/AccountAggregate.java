package blu.axonmysqlclient.aggregates;

import blu.axonmysqlclient.commands.CreateAccountCommand;
import blu.axonmysqlclient.events.AccountCreatedEvent;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Getter
public class AccountAggregate {
    @AggregateIdentifier
    private String id;

    private String accountHolder;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        AggregateLifecycle.apply(new AccountCreatedEvent(command.getId(), command.getAccountHolder()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.accountHolder = event.getAccountHolder();
    }

    public AccountAggregate() {
    }
}
