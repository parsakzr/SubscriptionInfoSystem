package InfoSys;

import java.util.ArrayList;
import java.util.Arrays;

public class CableProvider implements ServiceProvider{
    private String name;
    private ArrayList<SubscriptionPlan> subscriptionPlans;

    public CableProvider(String name) {
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
        return "CableProvider{" +
                "name='" + name + '\'' +
                ", subscriptionPlans=" + subscriptionPlans +
                '}';
    }
}
