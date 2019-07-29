package blu.axonmysqlclient.repositories;

import blu.axonmysqlclient.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
