package classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RewardsCard {

    private final Integer id;
    private final String name;
    private final String surname;
    final List<Offers> offer;
    private final LocalDate registeredDate;
    private final LocalDateTime lastLoggedInDateTime;

    public RewardsCard(String surname, String name, Integer id, List<Offers> offer) {
        this.surname = surname;
        this.name = name;
        this.id = id;
        this.offer = offer;
        this.registeredDate = setRegisteredDate();
        this.lastLoggedInDateTime = setRegisteredTimeDate();
    }

    private LocalDate setRegisteredDate() {
        return LocalDate.now();
    }
    private LocalDateTime setRegisteredTimeDate() {
        return LocalDateTime.now();
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
}
