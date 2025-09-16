package org.example;

import exception.AccountNotFoundException;
import exception.NoFundsEnoughException;
import model.Investment;
import repository.AcountRepository;
import repository.InvestmentRepository;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private final static AcountRepository accountRepository = new AcountRepository();
    private final static InvestmentRepository investmentRepository = new InvestmentRepository();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Ola seja bem vindo ao DIO Bank");
        while(true){
            System.out.println("Selecione a operação desejada");
            System.out.println("1 - Criar uma conta");
            System.out.println("2 - Criar um investimento");
            System.out.println("3 - Fazer um investimento");
            System.out.println("4 - Depositar na conta");
            System.out.println("5 - Sacar da conta");
            System.out.println("6 - Transferencia entre contas");
            System.out.println("7 - Investir");
            System.out.println("8 - Sacar investimento");
            System.out.println("9 - Listar contas");
            System.out.println("10 - Listar investimentos");
            System.out.println("11 - Listar carteiras de investimento");
            System.out.println("12 - Atualizar investimentos");
            System.out.println("13 - Histórico de conta");
            System.out.println("14 - Histórico de conta");
            var option = scanner.nextInt();
            switch (option){
                case 1: createAccount();
                case 2: createInvestment();
                case 3:
                case 4: deposit();
                case 5: withdraw();
                case 6:
                case 7:
                case 8:
                case 9: accountRepository.list().forEach(System.out::println);
                case 10: investmentRepository.list().forEach(System.out::println);
                case 11: investmentRepository.listWallets().forEach(System.out::println);
                case 12:{
                    investmentRepository.updateAmount();
                    System.out.println("Investimentos Reajustados...");
                }
                case 13:
                case 14: System.exit(0);
                default:System.out.println("Opção Invalida...");

            }
        }
    }

    private static void createAccount(){
        System.out.println("Informe as chaves pix separadas por ';': ");
        var pix = Arrays.stream(scanner.next().split(";")).toList();
        System.out.println("Informe o valor inicial de depósito: ");
        var amount = scanner.nextLong();
        var wallet = accountRepository.create(pix, amount);
        System.out.println("Conta criada: "+wallet);
    }

    private static void createInvestment(){
        System.out.println("Informe a taxa do investimento: ");
        var tax = scanner.nextInt();
        System.out.println("Informe o valor inicial de depósito: ");
        var initialFunds = scanner.nextLong();
        var investment = investmentRepository.create(tax, initialFunds);
        System.out.println("Investimento criado: "+investment);
    }

    private static void withdraw(){
        System.out.println("Informe a chave pix para depósito: ");
        var pix = scanner.next();
        System.out.println("Informe o valor que será depositado: ");
        var amount = scanner.nextLong();
        try{
            accountRepository.withdraw(pix, amount);
        } catch (NoFundsEnoughException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
        accountRepository.withdraw(pix, amount);
    }

    private static void deposit(){
        System.out.println("Informe a chave pix para depósito: ");
        var pix = scanner.next();
        System.out.println("Informe o valor que será depositado: ");
        var amount = scanner.nextLong();
        accountRepository.deposit(pix, amount);
    }
}