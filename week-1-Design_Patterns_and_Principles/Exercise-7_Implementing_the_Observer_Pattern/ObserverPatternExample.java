import java.util.ArrayList;
import java.util.List;

public class ObserverPatternExample {

    interface Stock {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObservers();
    }

    static class StockMarket implements Stock {
        private List<Observer> observers = new ArrayList<>();
        private String stockName;
        private double stockPrice;

        public StockMarket(String stockName, double initialPrice) {
            this.stockName = stockName;
            this.stockPrice = initialPrice;
        }

        public void setStockPrice(double price) {
            this.stockPrice = price;
            System.out.println("\n" + stockName + " price updated to Rs." + price);
            notifyObservers();
        }

        public void registerObserver(Observer o) {
            observers.add(o);
        }

        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        public void notifyObservers() {
            for (Observer o : observers) {
                o.update(stockName, stockPrice);
            }
        }
    }

    interface Observer {
        void update(String stockName, double newPrice);
    }

    static class MobileApp implements Observer {
        private String appName;

        public MobileApp(String name) {
            this.appName = name;
        }

        public void update(String stockName, double newPrice) {
            System.out.println(appName + " Mobile App: " + stockName + " new price = Rs." + newPrice);
        }
    }

    static class WebApp implements Observer {
        private String appName;

        public WebApp(String name) {
            this.appName = name;
        }

        public void update(String stockName, double newPrice) {
            System.out.println(appName + " Web App: " + stockName + " updated to Rs." + newPrice);
        }
    }

    public static void main(String[] args) {
        StockMarket teslaStock = new StockMarket("TSLA", 1000.00);

        Observer mobileUser = new MobileApp("InvestorX");
        Observer webUser = new WebApp("StockTracker");

        teslaStock.registerObserver(mobileUser);
        teslaStock.registerObserver(webUser);

        teslaStock.setStockPrice(1025.75);
        teslaStock.setStockPrice(998.40);

        teslaStock.removeObserver(mobileUser);

        teslaStock.setStockPrice(1050.00);
    }
}
