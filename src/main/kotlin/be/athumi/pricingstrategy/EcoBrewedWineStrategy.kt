package be.athumi.pricingstrategy

import be.athumi.Wine

/**
 * Pricing strategy for eco brewed wines.
 * Eco brewed wines degrade twice as fast as a regular wine (DefaultPricingStrategy)
 */
class EcoBrewedWineStrategy : PricingStrategy {
    override fun update(wine: Wine) {
        wine.expiresInYears -= 1

        if (wine.expiresInYears < 0) {
            //If the wine is expired, the price degrades four times as fast.
            wine.price = (wine.price - 4).coerceAtLeast(0)
        } else {
            //If the wine is not expired the price degrades twice as fast.
            wine.price = (wine.price - 2).coerceAtLeast(0)
        }
    }
}