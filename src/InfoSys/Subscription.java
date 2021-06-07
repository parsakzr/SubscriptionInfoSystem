package InfoSys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Subscription {
    // Instead of Date class as shown in the UML, LocalDate is used.
    // Because it's more modern and versatile, easier to parse
    // and also JavaFX's DatePicker returns a LocalDate object
    private LocalDate StartDate;
    private SubscriptionPlan subscriptionPlan;

    public Subscription(LocalDate startDate, SubscriptionPlan subscriptionPlan) {
        StartDate = startDate;
        this.subscriptionPlan = subscriptionPlan;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }
    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }
    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }
    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "StartDate=" + StartDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) +
                ", subscriptionPlan=" + subscriptionPlan +
                '}';
    }
}
