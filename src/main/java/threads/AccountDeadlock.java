package threads;

public class AccountDeadlock {
    static class Account implements Comparable {
        Double balance;
        int id;

        public Account(int id, double balance) {
            this.balance = balance;
            this.id = id;
        }

        public Account() {

        }

        void withdraw(double amount) {
            balance -= amount;
        }

        void deposit(double amount) {
            balance += amount;
        }

        public double getBalance() {
            return balance;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    // Warning: deadlock-prone!
    public void transferMoney(Account fromAccount,
                              Account toAccount,
                              Double amount) {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        }
    }

    public static void transfer(Account from, Account to, double amount) {
        synchronized (from) {
            synchronized (to) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    //
    public static void transferOrdered(Account from, Account to, double amount) {
        Account first = from;
        Account second = to;
        if (first.compareTo(second) < 0) {
            // Swap them
            first = to;
            second = from;
        }
        synchronized (first) {
            synchronized (second) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }


}
