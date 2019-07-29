package blu.axonmysqlclient.components;

import blu.axonmysqlclient.exceptions.ElementNotFoundException;
import blu.axonmysqlclient.model.Account;
import blu.axonmysqlclient.queries.AccountById;
import blu.axonmysqlclient.queries.AccountList;
import blu.axonmysqlclient.repositories.AccountRepository;
import blu.axonmysqlclient.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class QueryProcessor {
    private final AccountRepository accountRepository;

    @QueryHandler
    public List<Account> on(AccountList list) {
        log.info("Current Account Listings: {}", AccountService.accounts);
        AccountService.accounts = accountRepository.findAll();
        log.info("Updated Account Listings: {}", AccountService.accounts);
        return accountRepository.findAll();
    }

    @QueryHandler
    public Account on(AccountById account) {
        Optional<Account> optAccount = accountRepository.findById(account.getId());
        if (optAccount.isPresent()) {
            return optAccount.get();
        } else throw new ElementNotFoundException("Entity not found!");
    }
}
