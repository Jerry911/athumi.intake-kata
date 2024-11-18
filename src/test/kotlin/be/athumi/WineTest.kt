package be.athumi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WineTest {
    @Test
    fun `tasting or testing wine`() {
        assertThat(Wine("name", 0, 0)).isNotNull
    }

    @Test
    fun `Wine should listen to its name`() {
        val wine = Wine("name", 0, 0)
        assertEquals(wine.name,"name", "Whine name should be 'name'")
        wine.name = "new name"
        assertEquals(wine.name,"new name", "Whine name should be 'new name'")
    }

    @Test
    fun `a shop without wine is no shop, is it`() {
        val shop = WineShop(listOf(Wine("name", 0, 0)))

        assertThat(shop).isNotNull

        shop.next()

        assertThat(shop).isNotNull
    }

    @Test
    fun `test default wine price decreases`() {
        val wines = listOf(Wine("Default Wine", 10, 5))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(9, wines[0].price)
        assertEquals(4, wines[0].expiresInYears)
    }

    @Test
    fun `test default wine price does not go below zero`() {
        val wines = listOf(Wine("Default Wine", 1, 1))
        val wineShop = WineShop(wines)

        wineShop.next()
        wineShop.next()

        assertEquals(0, wines[0].price)
        assertEquals(-1, wines[0].expiresInYears)
    }

    @Test
    fun `test aging wine price increases before expiration`() {
        val wines = listOf(Wine("Bourdeaux Conservato", 10, 5))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(11, wines[0].price)
        assertEquals(4, wines[0].expiresInYears)
    }

    @Test
    fun `test aging wine price increases twice as fast after expiration`() {
        val wines = listOf(Wine("Bourdeaux Conservato", 10, 0))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(12, wines[0].price)
        assertEquals(-1, wines[0].expiresInYears)
    }

    @Test
    fun `test aging wine price does not exceed 100`() {
        val wines = listOf(Wine("Bourgogne Conservato", 99, 5))
        val wineShop = WineShop(wines)

        wineShop.next()
        wineShop.next()

        assertEquals(100, wines[0].price)
    }

    @Test
    fun `test Alexander the Great wine does not change`() {
        val wines = listOf(Wine("Wine brewed by Alexander the Great", 80, 10))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(80, wines[0].price)
        assertEquals(10, wines[0].expiresInYears)
    }

    @Test
    fun `test event wine price increases normally`() {
        val wines = listOf(Wine("Event Wine", 10, 10))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(11, wines[0].price)
        assertEquals(9, wines[0].expiresInYears)
    }

    @Test
    fun `test event wine price increases by 2 when 7 years or less`() {
        val wines = listOf(Wine("Event Wine", 10, 7))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(12, wines[0].price)
        assertEquals(6, wines[0].expiresInYears)
    }

    @Test
    fun `test event wine price increases by 4 when 2 years or less`() {
        val wines = listOf(Wine("Event Wine", 10, 2))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(14, wines[0].price)
        assertEquals(1, wines[0].expiresInYears)
    }

    @Test
    fun `test event wine price drops to zero after expiration`() {
        val wines = listOf(Wine("Event Wine", 10, 0))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(0, wines[0].price)
        assertEquals(-1, wines[0].expiresInYears)
    }

    @Test
    fun `test eco-brewed wine price degrades twice as fast`() {
        val wines = listOf(Wine("EcoBrewed Wine", 10, 5))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(8, wines[0].price)
        assertEquals(4, wines[0].expiresInYears)
    }

    @Test
    fun `test eco-brewed wine price degrades four times as fast after expiration`() {
        val wines = listOf(Wine("EcoBrewed Wine", 10, 0))
        val wineShop = WineShop(wines)

        wineShop.next()

        assertEquals(6, wines[0].price)
        assertEquals(-1, wines[0].expiresInYears)
    }

    @Test
    fun `test eco-brewed wine price does not go below zero`() {
        val wines = listOf(Wine("EcoBrewed Wine", 3, 1))
        val wineShop = WineShop(wines)

        wineShop.next()
        wineShop.next()

        assertEquals(0, wines[0].price)
        assertEquals(-1, wines[0].expiresInYears)
    }
}