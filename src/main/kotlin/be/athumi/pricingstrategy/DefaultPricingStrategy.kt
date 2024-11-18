package be.athumi.pricingstrategy

import be.athumi.Wine

/**
 * Pricing strategy for generic wines.
 */
class DefaultPricingStrategy : PricingStrategy {
    override fun update(wine: Wine) {
        wine.expiresInYears -= 1

        if (wine.expiresInYears < 0) {
            //If the wine is expired, the price degrades twice as fast.
            wine.price = (wine.price - 2).coerceAtLeast(0)
        } else {
            wine.price = (wine.price - 1).coerceAtLeast(0)
        }
    }
}