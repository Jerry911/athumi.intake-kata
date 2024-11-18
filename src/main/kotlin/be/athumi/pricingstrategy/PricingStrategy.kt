package be.athumi.pricingstrategy

import be.athumi.Wine

/**
 * Pricing rule for Wine
 * Each Wine will have its own implementation
 */
interface PricingStrategy {
    fun update(wine: Wine)
}