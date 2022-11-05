import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AbstractTest {

    protected List<Product> listOfProducts;
    protected ShoppingCart cart;

    @BeforeEach
    protected void generateTestData() {
        listOfProducts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listOfProducts.add(generateProduct(i));
        }
        cart = new ShoppingCart(listOfProducts);
    }

    protected Product generateProduct(int id) {
        Product product = new Product();
        product.setId(id);
        product.setName("product" + id);
        product.setPrice(Math.random() * 100);
        product.setQuantity(1);
        return product;
    }

    protected UserAccount generateUserAccount() {
        UserAccount user = new UserAccount();
        user.setName(UUID.randomUUID().toString());
        user.setSurname("Ivanov");
        user.setDateOfBirth("1999-01-01");
        user.setShoppingCart(cart);
        return user;
    }

    @AfterEach
    protected void killProductsList() {
        listOfProducts = null;
        cart = null;
    }
}
