package controller.converter;

import com.mitsko.financialsystem.controller.converter.Converter;
import com.mitsko.financialsystem.domain.dto.*;
import com.mitsko.financialsystem.domain.enums.ClientType;
import com.mitsko.financialsystem.domain.enums.Currency;
import com.mitsko.financialsystem.exception.ValidationException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ConverterTests {

    private String bankParameters;
    private String wrongBankParameters;
    private BankDto expectedBankDtoResult;

    private String clientParameters;
    private ClientDto expectedClientDtoResult;

    private String accountParameters;
    private AccountDto expectedAccountDtoResult;

    private String transactionParameters;
    private TransactionDto expectedTransactionDtoResult;

    private String transactionSearchParameters;
    private TransactionSearchDto expectedTransactionSearchDtoResult;

    @Before
    public void init() {
        this.bankParameters = "First 1 4";
        this.wrongBankParameters = "Second23";
        this.expectedBankDtoResult = new BankDto();
        this.expectedBankDtoResult.setName("First");
        this.expectedBankDtoResult.setIndividualsCommission(1);
        this.expectedBankDtoResult.setLegalEntitiesCommission(4);

        this.clientParameters = "Vasya Pupkin 18 LEGAL_ENTITY";
        this.expectedClientDtoResult = new ClientDto();
        this.expectedClientDtoResult.setName("Vasya");
        this.expectedClientDtoResult.setSurname("Pupkin");
        this.expectedClientDtoResult.setAge(18);
        this.expectedClientDtoResult.setClientType(ClientType.LEGAL_ENTITY);

        this.accountParameters = "f46d5120-37ec-4738-b54a-0036096d5d78 163c7e48-32d6-4ba7-8b81-fe88ba64591e USD 5000";
        this.expectedAccountDtoResult = new AccountDto();
        this.expectedAccountDtoResult.setClientUuid("f46d5120-37ec-4738-b54a-0036096d5d78");
        this.expectedAccountDtoResult.setBankUuid("163c7e48-32d6-4ba7-8b81-fe88ba64591e");
        this.expectedAccountDtoResult.setCurrency(Currency.USD);
        this.expectedAccountDtoResult.setBalance(new BigDecimal(5000));

        this.transactionParameters = "9396975f-f637-4999-bb86-dec4eb3aee9e cba882e3-2c34-4d25-a205-57f9643f78f6 300";
        this.expectedTransactionDtoResult = new TransactionDto();
        this.expectedTransactionDtoResult.setSenderAccountUuid("9396975f-f637-4999-bb86-dec4eb3aee9e");
        this.expectedTransactionDtoResult.setRecipientAccountUuid("cba882e3-2c34-4d25-a205-57f9643f78f6");
        this.expectedTransactionDtoResult.setAmount(new BigDecimal(300));

        this.transactionSearchParameters = "3e777313-bcb6-45c7-8d76-77214a4b0cfd";
        this.expectedTransactionSearchDtoResult = new TransactionSearchDto();
        this.expectedTransactionSearchDtoResult.setClientUuid("3e777313-bcb6-45c7-8d76-77214a4b0cfd");

    }

    @Test
    public void getBankDtoTest() {
        BankDto actualResult = Converter.getBankDto(bankParameters);

        assertEquals(expectedBankDtoResult, actualResult);
    }
    @Test(expected = ValidationException.class)
    public void getBankDtoExceptionTest() {
        BankDto actualResult = Converter.getBankDto(wrongBankParameters);
    }

    @Test
    public void getClientDtoTest() {
        ClientDto actualResult = Converter.getClientDto(clientParameters);
        assertEquals(expectedClientDtoResult, actualResult);
    }

    @Test
    public void getAccountDtoTest() {
        AccountDto actualResult = Converter.getAccountDto(accountParameters);
        assertEquals(expectedAccountDtoResult, actualResult);
    }

    @Test
    public void getTransactionDtoTest() {
        TransactionDto actualResult = Converter.getTransactionDto(transactionParameters);
        assertEquals(expectedTransactionDtoResult.getAmount(), actualResult.getAmount());
        assertEquals(expectedTransactionDtoResult.getSenderAccountUuid(), actualResult.getSenderAccountUuid());
        assertEquals(expectedTransactionDtoResult.getRecipientAccountUuid(), actualResult.getRecipientAccountUuid());
    }

    @Test
    public void getTransactionSearchDtoTest() {
        TransactionSearchDto actualResult = Converter.getTransactionSearchDto(transactionSearchParameters);
        assertEquals(expectedTransactionSearchDtoResult.getClientUuid(), actualResult.getClientUuid());
    }

}
