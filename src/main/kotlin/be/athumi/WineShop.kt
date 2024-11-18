package be.athumi

import be.athumi.pricingstrategy.*

class WineShop(var items: List<Wine>) {

    private val strategies = mapOf(
        "Bourdeaux Conservato" to AgingWineStrategy(),
        "Bourgogne Conservato" to AgingWineStrategy(),
        "Wine brewed by Alexander the Great" to CollectorWineStrategy(),
        "Event" to EventWineStrategy(),
        "EcoBrewed" to EcoBrewedWineStrategy()
    )

    private fun getStrategy(wine: Wine): PricingStrategy {
        return strategies[wine.name]
            ?: strategies.entries.firstOrNull { wine.name.startsWith(it.key) }?.value
            ?: DefaultPricingStrategy()
    }

    fun next() {
        for (wine in items) {
            val strategy = getStrategy(wine)
            strategy.update(wine)
        }
    }
}