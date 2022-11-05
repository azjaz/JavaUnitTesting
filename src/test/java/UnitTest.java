import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.UserAccount;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnitTest extends AbstractTest{

    @Test
    void addProductToCart() {
        int id = 15;
        Product newProduct = generateProduct(id);
        cart.addProductToCart(newProduct);
        Product productFromCart = cart.getProductById(id);
        assertEquals(productFromCart.getName(), newProduct.getName());
        assertEquals(productFromCart.getQuantity(), newProduct.getQuantity());
        assertEquals(productFromCart.getPrice(), newProduct.getPrice());
    }

    @Test
    void removeProductFromCart() {
        int id = 2;
        cart.removeProductFromCart(listOfProducts.get(id));
        assertEquals(0, cart.getProducts().stream().filter(product -> product.getId() == id).count());
    }

    @Test
    void getProductTotalPrice() {
        int id = 18;
        cart.addProductToCart(generateProduct(id));
        double totalPriceInCart = cart.getCartTotalPrice();
        double totalPriceOfProducts = listOfProducts.stream().mapToDouble(Product::getPrice).sum();
        assertEquals(totalPriceInCart, totalPriceOfProducts);
    }

    @Test
    void checkUserOrderPrice() {
        UserAccount user = generateUserAccount();
        double orderPrice = user.getShoppingCart().getCartTotalPrice();
        assertEquals(orderPrice, cart.getCartTotalPrice());
    }

    @Test
    void checkUnknownProductId() {
        int id = 12;
        assertThrows(ProductNotFoundException.class, () -> {cart.getProductById(id);});
    }

    @Test
    void checkProductIdPresence() {
        int id = 2;
        Optional<Product> productById = listOfProducts.stream().filter(product -> product.getId() == id).findFirst();
        assertEquals(cart.getProductById(id).getName(), productById.get().getName());
    }
}
