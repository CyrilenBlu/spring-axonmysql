package blu.axonmysqlclient.components;

import blu.axonmysqlclient.events.AccountCreatedEvent;
import blu.axonmysqlclient.model.Account;
import blu.axonmysqlclient.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

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
}
