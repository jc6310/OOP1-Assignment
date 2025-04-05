package classes;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.Function;

public class Offers {

    int offerId;
    double discount;
    String offerName;
    boolean isAvailable;

    public Offers(String offerName, int offerId, double discount, boolean isAvailable) {
        this.offerId = offerId;
        this.offerName = offerName;
        this.discount = discount;
        this.isAvailable = isAvailable;
    }

    public double getDiscount() {
        return discount;
    }

    public String getOfferName() {
        return offerName;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    // [O1_OOP2] Consumer: Apply discount to a specific price
    public void applyDiscount(Consumer<Double> discountConsumer) {
        discountConsumer.accept(this.discount);
    }

    // [O1_OOP2] Predicate: Check if the discount is above a threshold
    public boolean isDiscountAboveThreshold(Predicate<Offers> discountPredicate) {
        // Check if the predicate is not null and evaluate it, otherwise return false
        return discountPredicate != null && discountPredicate.test(this);
    }

    // [O1_OOP2] Supplier: Get a discount message based on the offer
    public String getDiscountMessage(Supplier<String> discountMessageSupplier) {
        return discountMessageSupplier.get();
    }

    // [O1_OOP2] Function: Get the final price after discount
    public double getFinalPrice(double originalPrice, Function<Double, Double> discountFunction) {
        return discountFunction.apply(originalPrice);
    }

    @Override
    public String toString() {
        return "Offer ID: " + offerId + ", Discount: " + discount + "%, Name: " + offerName + ", Available: " + isAvailable;
    }
}
