import java.util.ArrayList;

interface Action {
    void perform(User user, Market market);
}



class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price  = price;
    }
}

class User {
    String  name;
    double  balance;
    ArrayList<String>  owned  = new ArrayList<>();
    ArrayList<Integer> shares = new ArrayList<>();

    User(String name, double balance) {
        this.name    = name;
        this.balance = balance;
    }

    void buy(String symbol, int qty, double price) {
        balance -= price * qty;
        int i = owned.indexOf(symbol);
        if (i == -1) { owned.add(symbol); shares.add(qty); }
        else shares.set(i, shares.get(i) + qty);
    }

    boolean sell(String symbol, int qty, double price) {
        int i = owned.indexOf(symbol);
        if (i == -1 || shares.get(i) < qty) return false;
        balance += price * qty;
        if (shares.get(i) == qty) { owned.remove(i); shares.remove(i); }
        else shares.set(i, shares.get(i) - qty);
        return true;
    }
}

class Market {
    ArrayList<Stock> stocks = new ArrayList<>();

    Market() {
        stocks.add(new Stock("AAPL",  178.50));
        stocks.add(new Stock("GOOG", 2731.25));
        stocks.add(new Stock("MSFT",  415.80));
        stocks.add(new Stock("TSLA",  245.30));
        stocks.add(new Stock("NVDA",  875.40));
    }

    Stock find(String symbol) {
        for (Stock s : stocks)
            if (s.symbol.equalsIgnoreCase(symbol)) return s;
        return null;
    }

    void fluctuate() {
        for (Stock s : stocks)
            s.price = Math.max(1.0, s.price + (Math.random() * 10 - 5));
    }
}


class ViewMarket implements Action {
    public void perform(User user, Market market) {
        System.out.println();
        System.out.println("  +--------+----------+");
        System.out.println("  | Symbol |  Price   |");
        System.out.println("  +--------+----------+");
        for (Stock s : market.stocks)
            System.out.printf("  | %-6s | $%-7.2f |%n", s.symbol, s.price);
        System.out.println("  +--------+----------+");
    }
}

class BuyStock implements Action {
    public void perform(User user, Market market) {
        System.out.print("  Symbol   : ");
        String symbol = System.console().readLine().toUpperCase();
        Stock stock = market.find(symbol);
        if (stock == null) { System.out.println("  >> Not found."); return; }

        System.out.print("  Quantity : ");
        int qty   = Integer.parseInt(System.console().readLine());
        double total = stock.price * qty;

        if (user.balance < total) {
            System.out.printf("  >> Need $%.2f but have $%.2f%n", total, user.balance);
            return;
        }

        user.buy(symbol, qty, stock.price);
        System.out.printf("  >> Bought %d %s | Spent $%.2f | Balance $%.2f%n",
                qty, symbol, total, user.balance);
    }
}

class SellStock implements Action {
    public void perform(User user, Market market) {
        System.out.print("  Symbol   : ");
        String symbol = System.console().readLine().toUpperCase();
        Stock stock = market.find(symbol);
        if (stock == null) { System.out.println("  >> Not found."); return; }

        int i = user.owned.indexOf(symbol);
        if (i == -1) { System.out.println("  >> You don't own this."); return; }
        System.out.println("  >> You own: " + user.shares.get(i) + " shares");

        System.out.print("  Quantity : ");
        int qty = Integer.parseInt(System.console().readLine());

        if (!user.sell(symbol, qty, stock.price))  {
            System.out.println("  >> Not enough shares."); return;
        }
        System.out.printf("  >> Sold %d %s | Earned $%.2f | Balance $%.2f%n",
                qty, symbol, stock.price * qty, user.balance);
    }
}

class ViewPortfolio implements Action {
    public void perform(User user, Market market) {
        System.out.println();
        System.out.println("  +--------+--------+----------+");
        System.out.printf ("  | %-6s | %-6s | %-8s |%n", "Symbol", "Shares", "Value");
        System.out.println("  +--------+--------+----------+");

        double total = 0;
        if (user.owned.isEmpty()) {
            System.out.println("  | No holdings.              |");
        } else {
            for (int i = 0; i < user.owned.size(); i++) {
                Stock s     = market.find(user.owned.get(i));
                double value = s.price * user.shares.get(i);
                total       += value;
                System.out.printf("  | %-6s | %-6d | $%-7.2f |%n",
                        user.owned.get(i), user.shares.get(i), value);
            }
        }

        System.out.println("  +--------+--------+----------+");
        System.out.printf ("  | Stocks  : $%-17.2f |%n", total);
        System.out.printf ("  | Cash    : $%-17.2f |%n", user.balance);
        System.out.printf ("  | Total   : $%-17.2f |%n", total + user.balance);
        System.out.println("  +--------+--------+----------+");
    }
}

class UpdateMarket implements Action {
    public void perform(User user, Market market) {
        market.fluctuate();
        System.out.println("  >> Prices updated.");
    }
}


class TradingPlatform {
    Action action;
    User   user;
    Market market = new Market();

    TradingPlatform(User user) { this.user = user; }

    void setAction(Action a) { action = a; }
    void run()               { action.perform(user, market); }
}


public class Main {
    public static void main(String[] args) {
        System.out.print("  Name    : ");
        String name = System.console().readLine();
        System.out.print("  Balance : $");
        double balance = Double.parseDouble(System.console().readLine());

        TradingPlatform platform = new TradingPlatform(new User(name, balance));

        while (true) {
            System.out.println();
            System.out.println("  ==========================");
            System.out.println("    STOCK TRADING PLATFORM  ");
            System.out.println("  ==========================");
            System.out.printf ("   %-10s $%.2f%n", platform.user.name, platform.user.balance);
            System.out.println("  ==========================");
            System.out.println("   [1] View Market          ");
            System.out.println("   [2] Buy                  ");
            System.out.println("   [3] Sell                 ");
            System.out.println("   [4] Portfolio            ");
            System.out.println("   [5] Update Prices        ");
            System.out.println("   [6] Exit                 ");
            System.out.println("  ==========================");
            System.out.print("   Choose: ");

            String choice = System.console().readLine();
            System.out.println();

            switch (choice) {
                case "1": platform.setAction(new ViewMarket());   break;
                case "2": platform.setAction(new BuyStock());     break;
                case "3": platform.setAction(new SellStock());    break;
                case "4": platform.setAction(new ViewPortfolio()); break;
                case "5": platform.setAction(new UpdateMarket()); break;
                case "6": System.out.println("  >> Goodbye!"); return;
                default:  System.out.println("  >> Invalid."); continue;
            }

            platform.run();
        }
    }
}
