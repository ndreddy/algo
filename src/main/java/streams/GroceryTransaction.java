package streams;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static streams.GroceryTransaction.Tx.GROCERY;

public class GroceryTransaction {

    public enum Tx {
        GROCERY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }

    //

    /**
     * find all transactions of type grocery and return a list of transaction IDs
     * sorted in decreasing order of transaction value.
     *
     * @param transactions
     * @return
     */
    public List<Integer> getHighValueGrocTxs(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getType() == GROCERY)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(toList());

    }


    public class Transaction {

        private int id;
        private int year;
        private int value;
        private Tx type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Transaction(Tx type, int year, int value) {
            this.year = year;
            this.value = value;
        }

        public int getYear() {
            return this.year;
        }

        public int getValue() {
            return this.value;
        }

        public String toString() {
            return "{" + this.type + ", " +
                    "year: " + this.year + ", " +
                    "value:" + this.value + "}";
        }

        public Tx getType() {
            return type;
        }

        public void setType(Tx type) {
            this.type = type;
        }
    }

}
