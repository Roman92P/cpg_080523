package com.pivovarit.rental.domain;

class MoviePriceCalculator {
    private final long priceNew;
    private final long priceRegular;
    private final long priceOld;

    public MoviePriceCalculator(
      long priceNew,
      long priceRegular,
      long priceOld) {
        this.priceNew = priceNew;
        this.priceRegular = priceRegular;
        this.priceOld = priceOld;
    }

    public long getPriceFor(Movie movie) {
        switch (movie.getType()) {
            case NEW:
                return priceNew;
            case REGULAR:
                return priceRegular;
            case OLD:
                return priceOld;
            default:
                throw new IllegalArgumentException("unknown type");
        }
    }
}
