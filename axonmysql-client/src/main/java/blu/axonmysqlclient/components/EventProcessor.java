package blu.axonmysqlclient.components;

import blu.axonmysqlclient.events.AccountCreatedEvent;
import blu.axonmysqlclient.events.AccountDisabledEvent;
import blu.axonmysqlclient.events.ChangeAccountHolderEvent;
import blu.axonmysqlclient.exceptions.ElementNotFoundException;
import blu.axonmysqlclient.model.Account;
import blu.axonmysqlclient.model.Status;
import blu.axonmysqlclient.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class EventProcessor {
    private final AccountRepository accountRepository;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        accountRepository.save(new Account(event.getId(), event.getAccountHolder(), Status.CREATED));
        log.info("An Account has been created! id={} holder={}", event.getId(), event.getAccountHolder());
    }

    @EventHandler
    public void on(ChangeAccountHolderEvent event) {
        Optional<Account> optAccount = accountRepository.findById(event.getId());
        if (optAccount.isPresent()) {
            optAccount.get().setAccountHolder(event.getAccountHolder());
            accountRepository.save(optAccount.get());
            log.info("An Account (holder) has been updated! id={} holder={}", event.getId(), event.getAccountHolder());
        } else throw new ElementNotFoundException("Entity not found!");
    }

    @EventHandler
    public void on(AccountDisabledEvent event) {
        Optional<Account> optAccount = accountRepository.findById(event.getId());
        if (!optAccount.isPresent())
            throw new ElementNotFoundException("Entity not found!");
        optAccount.get().setStatus(Status.DISABLED);
        accountRepository.save(optAccount.get());
        log.info("An Account (status) has been updated! id={} status={}", event.getId(), event.getStatus());
    }
}
