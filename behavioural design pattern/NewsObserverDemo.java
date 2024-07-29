import java.util.ArrayList;
import java.util.List;


interface NewsSubject {
    void registerObserver(NewsObserver observer);
    void unregisterObserver(NewsObserver observer);
    void notifyObservers();
}


interface NewsObserver {
    void update(String article);
}


class NewsApp implements NewsSubject {
    private List<NewsObserver> observers = new ArrayList<>();
    private String latestArticle;

    @Override
    public void registerObserver(NewsObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(NewsObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (NewsObserver observer : observers) {
            observer.update(latestArticle);
        }
    }

    public void publishArticle(String article) {
        latestArticle = article;
        notifyObservers();
    }
}


class Subscriber implements NewsObserver {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String article) {
        System.out.println(name + " received notification: New article published - " + article);
    }
}


public class NewsObserverDemo {
    public static void main(String[] args) {
        NewsApp newsApp = new NewsApp();

        Subscriber subscriber1 = new Subscriber("John");
        Subscriber subscriber2 = new Subscriber("Alice");

        newsApp.registerObserver(subscriber1);
        newsApp.registerObserver(subscriber2);

        newsApp.publishArticle("Breaking News: New Technology Unveiled");
    }
}