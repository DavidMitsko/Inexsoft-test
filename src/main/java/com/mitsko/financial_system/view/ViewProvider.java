package com.mitsko.financial_system.view;

import com.mitsko.financial_system.domain.enums.CommandName;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.view.impl.AccountViewImpl;
import com.mitsko.financial_system.view.impl.BankInfoViewImpl;
import com.mitsko.financial_system.view.impl.ExchangeRateViewImpl;
import com.mitsko.financial_system.view.impl.HelpInfoViewImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ViewProvider {

    private static final Logger logger = LoggerFactory.getLogger(ViewProvider.class);

    private static final ViewProvider instance = new ViewProvider();

    private final Map<CommandName, View> repository = new HashMap<>();

    public static ViewProvider getInstance() {
        return instance;
    }

    public ViewProvider(){
        repository.put(CommandName.GET_ALL_BANKS, new BankInfoViewImpl());
        repository.put(CommandName.GET_CLIENTS_ACCOUNTS, new AccountViewImpl());
        repository.put(CommandName.HELP, new HelpInfoViewImpl());
        repository.put(CommandName.GET_EXCHANGE_RATE, new ExchangeRateViewImpl());
    }

    public View getView(String name) throws ValidationException {
        CommandName commandName;
        View view;

        try {
            commandName = Arrays.stream(CommandName.values()).filter(c -> c.getCommand().equals(name))
                    .findFirst().orElseThrow(() -> new ValidationException("Wrong command"));
            view = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException | ValidationException e) {
            logger.error(e.getMessage());
            throw new ValidationException(e);
        }

        return view;
    }

}
