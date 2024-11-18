package be.athumi.pricingstrategy

import be.athumi.Wine

/**
 * Pricing strategy for event wines.
 * These Wines we introduced specifically for special events (like the first trip to Mars)
 */
class EventWineStrategy : PricingStrategy {
    override fun update(wine: Wine) {
        wine.expiresInYears -= 1

        if (wine.expiresInYears < 0) {
            //When the event has passed (expiresInYears), it drops to zero to indicate removal
            wine.price = 0
        } else if (wine.expiresInYears <= 2) {
            //When there are only 2 years (or less) before expiration, the price increases with 4
            wine.price = (wine.price + 4).coerceAtMost(100)
        } else if (wine.expiresInYears <= 7) {
            //When there are only 7 years (or less) before expiration, the price increases with 2
            wine.price = (wine.price + 2).coerceAtMost(100)
        } else {
            wine.price = (wine.price + 1).coerceAtMost(100)
        }
    }
}