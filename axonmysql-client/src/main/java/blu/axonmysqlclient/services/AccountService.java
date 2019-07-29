package blu.axonmysqlclient.services;

import blu.axonmysqlclient.commands.CreateAccountCommand;
import blu.axonmysqlclient.model.Account;
import blu.axonmysqlclient.queries.AccountById;
import blu.axonmysqlclient.queries.AccountList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    public CompletableFuture<String> createAccount(CreateAccountCommand command) {
        log.debug("Processing CreateAccountCommand command: {}", command);
        return commandGateway.send(command);
    }

    public List<Account> findAll() {
        log.debug("Processing FindAllCommand query");
        return queryGateway.query(new AccountList(),
                ResponseTypes.multipleInstancesOf(Account.class)).join();
    }

    public Account findById(String id) throws ExecutionException, InterruptedException {
        log.debug("Processing FindById query");
        return queryGateway.query(new AccountById(id),
                ResponseTypes.instanceOf(Account.class)).get();
    }
}
