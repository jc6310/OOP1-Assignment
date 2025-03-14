package classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RewardsCard {

    private final Integer id;
    private final String name;
    private final String surname;
    private final LocalDate registeredDate;
    private final LocalDateTime lastLoggedInDateTime;
    final List<Offers> offers;

    public RewardsCard(String surname, String name) {
        this.id = setRewardId();
        this.surname = surname;
        this.name = name;
        this.offers = new ArrayList<>();
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
        // [06_OOP2] Date/Time API
        return LocalDate.now();
    }

    private LocalDateTime setRegisteredTimeDate() {
        // [06_OOP2] Date/Time API
        return LocalDateTime.now();
    }

    private int setRewardId() {
        Supplier<Integer> randomValue = () -> (int)(Math.random() * 10100010);;
        return randomValue.get();
    }

    // Method to add offers to the Reward
    public void addOffer(Offers offer) {
        offers.add(offer);
    }

    public List<Offers> getOffers() {
        return offers;
    }
}
