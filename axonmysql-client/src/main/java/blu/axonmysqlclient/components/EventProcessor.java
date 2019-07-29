package blu.axonmysqlclient.components;

import blu.axonmysqlclient.events.AccountCreatedEvent;
import blu.axonmysqlclient.events.ChangeAccountHolderEvent;
import blu.axonmysqlclient.exceptions.ElementNotFoundException;
import blu.axonmysqlclient.model.Account;
import blu.axonmysqlclient.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class EventProcessor {
    private final AccountRepository accountRepository;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        accountRepository.save(new Account(event.getId(), event.getAccountHolder()));
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
}
