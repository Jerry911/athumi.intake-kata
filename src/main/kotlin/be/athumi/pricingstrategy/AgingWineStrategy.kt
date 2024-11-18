package be.athumi.pricingstrategy

import be.athumi.Wine

/**
 * Pricing strategy for aging wines.
 * When it expires, it becomes even more valuable and increases its value twice as fast
 */
class AgingWineStrategy : PricingStrategy {
    override fun update(wine: Wine) {
        wine.expiresInYears -= 1

        if (wine.expiresInYears < 0) {
            wine.price = (wine.price + 2).coerceAtMost(100)
        } else {
            wine.price = (wine.price + 1).coerceAtMost(100)
        }
    }
}