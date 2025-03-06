package classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RewardsCard {

    private final Integer id;
    private final String name;
    private final String surname;
    final List<Offers> offer;
    private final LocalDate registeredDate;
    private final LocalDateTime lastLoggedInDateTime;

    public RewardsCard(String surname, String name, List<Offers> offer) {
        this.id = setRewardId();
        this.surname = surname;
        this.name = name;
        this.offer = offer;
        this.registeredDate = setRegisteredDate();
        this.lastLoggedInDateTime = setRegisteredTimeDate();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public LocalDateTime getRegisteredTimeDate() {
        return lastLoggedInDateTime;
    }

    private LocalDate setRegisteredDate() {
        return LocalDate.now();
    }

    private LocalDateTime setRegisteredTimeDate() {
        return LocalDateTime.now();
    }

    private int setRewardId() {
        Supplier<Integer> randomValue = () -> (int)(Math.random() * 10100010);;
        return randomValue.get();
    }

    private Consumer setOffers() {

        Consumer<String> print = x -> System.out.println(x);
        print.accept("java");
        return print;
    }
}
