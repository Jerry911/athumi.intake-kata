package be.athumi.pricingstrategy

import be.athumi.Wine

/**
 * Pricing strategy 'Alexander The Great' wine.
 * It is not a wine meant for consumption but a collectors item, and we don't want to change its price due to age
 */
class CollectorWineStrategy : PricingStrategy {
    override fun update(wine: Wine) {
        // No changes to price or expiration.
    }
}