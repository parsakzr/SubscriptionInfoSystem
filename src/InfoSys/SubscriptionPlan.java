package InfoSys;

public class SubscriptionPlan {
    private String name;
    private ServiceProvider serviceProvider;

    public SubscriptionPlan(String name) {
        this.name = name;
    }

    public SubscriptionPlan(String name, ServiceProvider serviceProvider) {
        this.name = name;
        this.serviceProvider = serviceProvider;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }
    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public String toString() {
        String providerName= serviceProvider==null? null:serviceProvider.getName();
        return "SubscriptionPlan{" +
                "name='" + name + '\'' +
                ", serviceProvider=" + providerName +
                '}';
    }
}
