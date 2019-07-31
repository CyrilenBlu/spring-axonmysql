package blu.axonmysqlclient.aggregates;

import blu.axonmysqlclient.commands.ChangeAccountHolderCommand;
import blu.axonmysqlclient.commands.CreateAccountCommand;
import blu.axonmysqlclient.commands.DisableAccountCommand;
import blu.axonmysqlclient.events.AccountCreatedEvent;
import blu.axonmysqlclient.events.AccountDisabledEvent;
import blu.axonmysqlclient.events.ChangeAccountHolderEvent;
import blu.axonmysqlclient.model.Status;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

//public void on() => Changing attribute variable values
//Constructor => Creating/Adding new values to attributes
@Aggregate
@Getter
@Slf4j
@SuppressWarnings("unused")
public class AccountAggregate {
    @AggregateIdentifier
    private String id;

    private String accountHolder;

    private Status status;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        AggregateLifecycle.apply(new AccountCreatedEvent(command.getId(), command.getAccountHolder(), command.getStatus()));
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

    @CommandHandler
    public void handle(DisableAccountCommand command) {
        AggregateLifecycle.apply(new AccountDisabledEvent(command.getId(), command.getStatus()));
    }

    @EventSourcingHandler
    public void on(AccountDisabledEvent event) {
        this.status = Status.valueOf(event.getStatus());
    }
}
