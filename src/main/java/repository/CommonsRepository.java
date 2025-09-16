package repository;

import exception.NoFundsEnoughException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.AccountWallet;
import model.Money;
import model.MoneyAudit;
import model.Wallet;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static model.BankService.ACCOUNT;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonsRepository {

    public static void checkFundsForTransaction(final Wallet source, final long ammount){
        if(source.getFunds() < ammount) throw new NoFundsEnoughException("Sua conta não tem fundos suficientes para realizar essa transação");

    }

    public static List<Money> generateMoney(final UUID transactionId, final long funds, final String description){
        var history = new MoneyAudit(transactionId, ACCOUNT, description, OffsetDateTime.now());
        return Stream.generate(()-> new Money(history)).limit(funds).toList();
    }

}
