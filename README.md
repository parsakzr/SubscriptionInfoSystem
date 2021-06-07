# Subscription Information System

* Parsa Kazerooni, 19011915 @ BLM2012-Gr. 3
* Description: Semester Project @ YTU::OOP

## Compilation

Console Application is under the package `InfoSys`, and the main method is defined in `InfoSys/InformationSystem.java`

To Compile the Project, on **Windows/\*NIX** platforms :

```shell
$ javac ./InfoSys/InformationSystem.java
$ cd ./InfoSys/
$ java InformationSystem
```

And the Precompiled Version is available under the `/out/Production/InfoSys` directory.



## Usage

* GUI isn't implemented, but intended. JavaFX would've been used. the empty application is under the `sample` package.

* The Console app is completely functional.

    The MainMenu looks like this:
    ```
    --------------------------------
    Welcome to the Menu!
    1. Service Provider
    2. Customer
    0. Quit
    Enter the option [0-9]:
    ```

    * to select every option in the whole program, you enter the digit related to the option. [0-9]

* String inputs such as names should be written **without space**. Instead of `scanner.nextLine()`, `scanner.next()` has been used.



## Unit Test

an alternative code for main method 
```java
public static void main(String[] args) {
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
}
```
And the Output is:

```java
GSMProvider{
    name='Turk Telekom',
    subscriptionPlans=[
        SubscriptionPlan{
            name='4GB', 
            serviceProvider=Turkcell
        }, 
        SubscriptionPlan{
            name='2GB', 
            serviceProvider=null
        }
    ]
}

GSMProvider{
    name='Turkcell', 
    subscriptionPlans=[
        SubscriptionPlan{
            name='2GB', 
            serviceProvider=null
        }   
    ]
}

ExistingCustomer{ 
    subscription=Subscription{
        StartDate=01/06/2021, 
        subscriptionPlan=SubscriptionPlan{
            name='4GB', 
            serviceProvider=Turkcell
        }
    }, 
    Customer{
        CitizenshipNr='1111', 
        name='Parsa', 
        tel='+1234', 
        mail='null'
    }
}
ExistingCustomer{ 
    subscription=Subscription{
        StartDate=06/07/2021, 
        subscriptionPlan=SubscriptionPlan{
            name='100Mbps', 
            serviceProvider=Turkcell Superonline
        }
    }, 
    Customer{
        CitizenshipNr='2222', 
        name='Ali', 
        tel='null', 
        mail='null'
    }
}
```

## License
[MIT](https://choosealicense.com/licenses/mit/)