package pl.edu.wszib.wallet.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.wallet.dao.IExpenseDAO;
import pl.edu.wszib.wallet.dao.IUserDao;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.wallet.controllers",
        "pl.edu.wszib.wallet.services.impl",
        "pl.edu.wszib.wallet.session"
})
public class TestConfiguration {

    @Bean
    public IExpenseDAO expenseDAO() {
        return Mockito.mock(IExpenseDAO.class);
    }

    @Bean
    public IUserDao userDao(){
        return Mockito.mock(IUserDao.class);
    }
}