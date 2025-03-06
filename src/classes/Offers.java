package classes;

public class Offers {

    int offerId;
    int discount;
    String offerName;

    public Offers(String offerName, int offerId, int discount) {
        this.offerId = offerId;
        this.offerName = offerName;
        this.discount = discount;
    }

    public double getPrice() {
        return discount;
    }

    public String getOfferName() {
        return offerName;
    }

    public int getOfferId() {
        return offerId;
    }
}
