package InfoSys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class InformationSystem {

    public static void printMainMenu(){
        System.out.println("--------------------------------");
        System.out.println("Welcome to the Menu!");
        System.out.println("1. Service Provider");
        System.out.println("2. Customer");
        System.out.println("0. Quit");
        System.out.println("Enter the option [0-9]:");
    }
    public static void printProviderMenu(){
        System.out.println("--------------------------------");
        System.out.println("1. Add a service provider");
        System.out.println("2. Select a provider");
        System.out.println("3. Print all service providers");
        System.out.println("0. Back");
        System.out.println("Enter the option [0-9]:");
    }
    public static void printCustomerMenu(){
        System.out.println("--------------------------------");
        System.out.println("1. Add a Customer");
        System.out.println("2. Select a customer");
        System.out.println("3. Print Customers");
        System.out.println("0. Back");
        System.out.println("Enter the option [0-9]:");
    }
    public static void printProviderOptions(){
        System.out.println("--------------------------------");
        System.out.println("1. Add a new subscription plan");
        System.out.println("2. find a subscription plan");
        System.out.println("3. Update the name");
        System.out.println("0. Back");
        System.out.println("Enter the option [0-9]:");
    }
    public static void printCustomerOptions(){
        System.out.println("--------------------------------");
        System.out.println("1. Update Citizenship Number");
        System.out.println("2. Update Name");
        System.out.println("3. Update Mail");
        System.out.println("4. Update Tel");
        System.out.println("0. Back");
        System.out.println("Enter the option [0-9]:");
    }

    public static void main(String[] args) {

        ArrayList<ServiceProvider> serviceProviders = new ArrayList<ServiceProvider>();
        ArrayList<Customer> customers = new ArrayList<Customer>();

        Scanner scanner = new Scanner(System.in);
        char option;

        // Menu()
        boolean isRunning = true;
        while (isRunning){
            printMainMenu();
            option = scanner.next().charAt(0);
            switch (option){ // Not using Enhanced Switch @ Java13
                case '1': // Provider
                    printProviderMenu();
                    option = scanner.next().charAt(0);
                    switch (option) {
                        case '1': // Adding a new Service Provider
                            ServiceProvider provider = null; // will be typecasted.
                            System.out.println("+ Enter the type of service:" + "(1. GSM  | 2. Cable)");
                            option = scanner.next().charAt(0); // Input type
                            System.out.println("+ Enter the name of the provider:");
                            String name = scanner.next(); // Input name

                            // Creating the object
                            if(option == '1') // GSMProvider
                                provider = new GSMProvider(name); // provider -> GSM
                            else if(option == '2')
                                provider = new CableProvider(name); // provider -> Cable
                            else
                                System.out.println("- Invalid type of service!");
                            // Adding SubPlans
                            if(provider != null) {
                                System.out.println("+ Enter the number of Subscription plans (You can also add later!)");
                                int numofplans = scanner.nextInt();
                                for (int i = 0; i < numofplans; i++) {
                                    System.out.println("++ Name of the Plan #" + i);
                                    String planName = scanner.next();
                                    SubscriptionPlan plan = new SubscriptionPlan(planName, provider);
                                    provider.addSubscriptionPlan(plan);
                                }
                            }
                            // Provider is now ready!
                            serviceProviders.add(provider);// add to the array
                            continue;

                        case '2': // Select a Provider to update
                            ServiceProvider selectedProvider = null;
                            System.out.println("+ Select a provider:");
                            for (int i = 0; i < serviceProviders.size(); i++) { // List all providers
                                System.out.println(i+1 + ". " + serviceProviders.get(i).getName());
                            }
                            int index = scanner.nextInt() - 1; // Input index of serviceProviders
                            if(index >= 0 && index < serviceProviders.size())
                                selectedProvider = serviceProviders.get(index);

                            if(selectedProvider != null) {
                                System.out.println(selectedProvider.getName() + " Selected...");
                                // What to do
                                printProviderOptions();
                                option = scanner.next().charAt(0);
                                if (option == '1'){ // Add a subscription plan
                                    System.out.println("+ Name of the plan:");
                                    String planName = scanner.next();
                                    SubscriptionPlan plan = new SubscriptionPlan(planName, selectedProvider);
                                    selectedProvider.addSubscriptionPlan(plan);
                                }
                                else if (option == '2'){ // Find subscription plan
                                    System.out.println("+ Name of the plan");
                                    String planName = scanner.next();
                                    SubscriptionPlan plan = selectedProvider.findSubscriptionPlan(planName);
                                    if(plan != null)
                                        System.out.println(plan);
                                    else
                                        System.out.println("- Plan not found!");
                                }
                                else if(option == '3'){
                                    System.out.println("+ Enter the new name");
                                    String newName = scanner.next();
                                    selectedProvider.setName(newName);
                                }
                                // else -> back
                            }
                            else
                                System.out.println("- Couldn't retrieve the provider!");
                            // back
                            continue;

                        case '3':
                            System.out.println("-- Printing providers...");
                            for (ServiceProvider serviceProvider: serviceProviders) {
                                System.out.println(serviceProvider); // print raw toString()
                            }
                            continue;
                        default:
                            System.out.println("Back to the Main Menu...");
                    }
                    continue;

                case '2': // Customer
                    printCustomerMenu();
                    option = scanner.next().charAt(0);
                    switch (option) {
                        case '1': // Adding a new customer
                            System.out.println("+ Enter the Citizenship Number:");
                            String citizenNo = scanner.next();
                            Customer newCustomer = new PossibleCustomer(citizenNo); // add sub to change it to Existing
                            System.out.println("+ Enter the name:");
                            String name = scanner.next();
                            newCustomer.setName(name);
                            // Subscription
                            System.out.println("Add Subscription? (y/n)");
                            option = scanner.next().charAt(0);
                            if(option == 'y' || option == 'Y'){
                                // Select a Provider
                                ServiceProvider selectedProvider = null;
                                System.out.println("+ Select a provider: (0. for None)");
                                for (int i = 0; i < serviceProviders.size(); i++) { // List all providers
                                    System.out.println(i+1 + ". " + serviceProviders.get(i).getName());
                                }
                                int index = scanner.nextInt() - 1; // Input index of serviceProviders
                                if(index >= 0 && index < serviceProviders.size()) // if index ok
                                    selectedProvider = serviceProviders.get(index);

                                System.out.println("+ Enter the subscription plan's name");
                                String planName = scanner.next();

                                SubscriptionPlan subscriptionPlan = null;
                                if (selectedProvider != null) {
                                    subscriptionPlan = selectedProvider.findSubscriptionPlan(planName);
                                    // can be an existing plan or null;
                                }
                                if(subscriptionPlan == null) // !selectedProvider || findSub() returned null;
                                    subscriptionPlan = new SubscriptionPlan(planName);
                                // subscriptionPlan -> Done
                                // Date...
                                System.out.println("Enter the start date of the plan:" + " (dd/MM/yyyy)");
                                String startDate_str = scanner.next(); // #!DEBUG
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate startDate = LocalDate.parse(startDate_str, formatter);

                                Subscription subscription = new Subscription(startDate, subscriptionPlan);

                                newCustomer = new ExistingCustomer(newCustomer, subscription);
                            } // option=='y' addSub() --> Done
                            // else option=='n' --> Done (Remains PossibleCustomer)

                            // add customer to the list
                            customers.add(newCustomer);
                            continue;
                        case '2': // --- Select Customer
                            Customer selectedCustomer = null;
                            System.out.println("+ Select a Customer");
                            for (int i = 0; i < customers.size(); i++) { // List all customers
                                System.out.println(i+1 + ". " + customers.get(i).getCitizenshipNr()
                                                        + ", " + customers.get(i).getName());
                            }
                            int index = scanner.nextInt() - 1;
                            if(index >= 0 && index < customers.size()){
                                selectedCustomer = customers.get(index);
                            }
                            if(selectedCustomer != null){
                                System.out.println(selectedCustomer.getName() + " Selected...");
                                // What to do
                                printCustomerOptions();
                                option = scanner.next().charAt(0);
                                if (option == '1'){ // Add a subscription plan
                                    System.out.println("+ Enter the new Citizenship No. :");
                                    String newCitizenNo = scanner.next();
                                    selectedCustomer.setCitizenshipNr(newCitizenNo);
                                }
                                else if (option == '2'){ // Find subscription plan
                                    System.out.println("+ Enter the new name:");
                                    String newName = scanner.next();
                                    selectedCustomer.setName(newName);
                                }
                                else if(option == '3'){
                                    System.out.println("+ Enter the mail:");
                                    String newMail = scanner.next();
                                    selectedCustomer.setMail(newMail);
                                }
                                else if(option == '4'){
                                    System.out.println("+ Enter the Tel:");
                                    String newTel = scanner.next();
                                    selectedCustomer.setMail(newTel);
                                }
                                // else -> back
                            }
                            continue;
                        case '3':// --- Printing Costumers ---
                            System.out.println("--- Printing Costumers ---");
                            for (Customer customer: customers) {
                                System.out.println(customer);
                            }
                            continue;

                        default:
                            System.out.println("Back to the Main Menu...");
                    }
                    continue; // End of CustomerMenu

                case '0':
                    isRunning = false;
                    continue;
                default:
                    System.out.println("Enter a valid option!");

            }
        }

        /* // Manual Test
        GSMProvider tcell = new GSMProvider("Turkcell");
        GSMProvider ttelekom = new GSMProvider("Turk Telekom");
        CableProvider tcell_superonline = new CableProvider("Turkcell Superonline");

        SubscriptionPlan plan2gb = new SubscriptionPlan("2GB");
        SubscriptionPlan plan4gb_tt = new SubscriptionPlan("4GB", tcell);
        SubscriptionPlan plan100mbps_tcso = new SubscriptionPlan("100Mbps", tcell_superonline);

        ttelekom.addSubscriptionPlan(plan4gb_tt);
        ttelekom.addSubscriptionPlan(plan2gb);
        tcell.addSubscriptionPlan(plan2gb);
        tcell_superonline.addSubscriptionPlan(plan100mbps_tcso);

        // Date today = DateUtils.toDate(LocalDate.parse("2021-06-05"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate day_1 = LocalDate.parse("01/06/2021", formatter);
        LocalDate day_now = LocalDate.now();
        //today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        Subscription sub4gb_tt = new Subscription(day_1, plan4gb_tt);
        Subscription sub2gb_tc = new Subscription(day_1, plan2gb);
        Subscription sub100mbps_tcso = new Subscription(day_now, plan100mbps_tcso);

        ExistingCustomer parsa = new ExistingCustomer("1111", sub4gb_tt);
        parsa.setName("Parsa");
        parsa.setTel("+1234");

        ExistingCustomer ali = new ExistingCustomer("2222", sub100mbps_tcso);
        ali.setName("Ali");

        System.out.println(ttelekom);
        System.out.println(tcell);
        System.out.println(parsa);
        System.out.println(ali);
        */


    }
}
