package blu.axonmysqlclient.controllers;

import blu.axonmysqlclient.commands.ChangeAccountHolderCommand;
import blu.axonmysqlclient.commands.CreateAccountCommand;
import blu.axonmysqlclient.commands.DisableAccountCommand;
import blu.axonmysqlclient.model.Account;
import blu.axonmysqlclient.model.Status;
import blu.axonmysqlclient.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService service;

    @GetMapping("/list")
    public List<Account> listAccounts() {
        log.info("Executing FindAll Query");
        return service.findAll();
    }

    @GetMapping("/account/{id}")
    public Account showAccount(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        log.info("Executing FindById Query");
        return service.findById(id);
    }

    @PostMapping("/add")
    public CompletableFuture<String> createAccount(@RequestBody Map<String,String> request) {
        CreateAccountCommand command = new CreateAccountCommand(UUID.randomUUID().toString(),
                request.get("accountHolder"),
                String.valueOf(Status.CREATED));
        log.info("Executing command: {}", command);
        return service.createAccount(command);
    }

    @PatchMapping("/account/{id}/changeholder")
    public CompletableFuture<String> changeAccountHolder(@PathVariable("id") String id, @RequestBody Map<String, String> request) {
        ChangeAccountHolderCommand command = new ChangeAccountHolderCommand(id, request.get("accountHolder"));
        log.info("Executing command: {}", command);
        return service.changeAccountHolder(command);
    }

    @DeleteMapping("/account/{id}/disable")
    public CompletableFuture<String> disableAccount(@PathVariable("id") String id) {
        DisableAccountCommand command = new DisableAccountCommand(id, String.valueOf(Status.DISABLED));
        log.info("Executing command: {}", command);
        return service.disableAccount(command);
    }
}
