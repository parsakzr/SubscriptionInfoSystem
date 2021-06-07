package InfoSys;

public class ExistingCustomer extends Customer{
    private Subscription subscription;

    public ExistingCustomer(String citizenshipNr) {
        super(citizenshipNr);
    }

    public ExistingCustomer(String citizenshipNr, Subscription subscription) {
        super(citizenshipNr);
        this.subscription = subscription;
    }

    // To convert PossibleCustomer to Existing customer
    public ExistingCustomer(Customer customer, Subscription subscription){ // Copy constructor
        super(customer.getCitizenshipNr());
        this.subscription = subscription;
        super.setName(customer.getName());
        super.setMail(customer.getMail());
        super.setTel(customer.getTel());
    }

    public Subscription getSubscription() {
        return subscription;
    }
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "ExistingCustomer{ " +
                "subscription=" + subscription +
                ", " +
                super.toString() +
                "}";
    }
}
