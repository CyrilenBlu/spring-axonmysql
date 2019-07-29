package blu.axonmysqlclient.aggregates;

import blu.axonmysqlclient.commands.ChangeAccountHolderCommand;
import blu.axonmysqlclient.commands.CreateAccountCommand;
import blu.axonmysqlclient.events.AccountCreatedEvent;
import blu.axonmysqlclient.events.ChangeAccountHolderEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

//Events => public void on()
// Commands => Constructor()
@Aggregate
@Getter
@Slf4j
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

    @CommandHandler
    public void handle(ChangeAccountHolderCommand command) {
        AggregateLifecycle.apply(new ChangeAccountHolderEvent(this.id, command.getAccountHolder()));
    }

    @EventSourcingHandler
    public void on(ChangeAccountHolderEvent event) {
        this.accountHolder = event.getAccountHolder();
    }

    public AccountAggregate() {
    }
}
