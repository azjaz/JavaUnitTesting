import com.epam.tamentoring.bo.DiscountUtility;
import com.epam.tamentoring.bo.OrderService;
import com.epam.tamentoring.bo.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MockingTest extends AbstractTest {

    @Mock
    DiscountUtility discountUtility;

    @InjectMocks
    OrderService orderService = new OrderService();

    @Test
    void checkDiscountUtility() {
        double returnedDiscount = 3.0;
        double totalPriceInCartBefore = user.getShoppingCart().getCartTotalPrice();
        UserAccount user = generateUserAccount();

        Mockito.when(discountUtility.calculateDiscount(user)).thenReturn(returnedDiscount);
        assertEquals(totalPriceInCartBefore - returnedDiscount, orderService.getOrderPrice(user));
        Mockito.verify(discountUtility, Mockito.times(1)).calculateDiscount(user);
        Mockito.verifyNoMoreInteractions(discountUtility);
    }
}
