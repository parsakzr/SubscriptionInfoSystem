package InfoSys;

import java.util.ArrayList;

public class GSMProvider implements ServiceProvider{
    private String name;
    private ArrayList<SubscriptionPlan> subscriptionPlans;

    public GSMProvider(String name) {
        this.name = name;
        subscriptionPlans = new ArrayList<SubscriptionPlan>();
    }

    @Override
    public void addSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        subscriptionPlans.add(subscriptionPlan);
    }

    @Override
    public SubscriptionPlan findSubscriptionPlan(String name) {
        for (SubscriptionPlan subPlan: subscriptionPlans){
            if(subPlan.getName().equals(name))
                return subPlan;
        }
        //System.err.println("- NOT FOUND! SubscriptionPlan {name:"+name+"}");
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GSMProvider{" +
                "name='" + name + '\'' +
                ", subscriptionPlans=" + subscriptionPlans +
                '}';
    }
}
