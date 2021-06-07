package InfoSys;

public interface ServiceProvider {
    void addSubscriptionPlan(SubscriptionPlan subscriptionPlan);
    SubscriptionPlan findSubscriptionPlan(String name); // #TODO SubPlan or Void?
    String getName();
    void setName(String name);
}
