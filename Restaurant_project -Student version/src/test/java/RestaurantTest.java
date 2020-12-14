import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant SpyRestaurant = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.parse("16:00:00");
        Mockito.when(SpyRestaurant.getCurrentTime()).thenReturn(currentTime);
        boolean output = SpyRestaurant.isRestaurantOpen();
        assertTrue(output);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant SpyRestaurant = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.parse("22:30:00");
        Mockito.when(SpyRestaurant.getCurrentTime()).thenReturn(currentTime);
        boolean output = SpyRestaurant.isRestaurantOpen();
        assertFalse(output);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //calculation of bill part//
    @Test
    public void returning_the_cost_of_selected_Menu_items () {
        List<Item> selectedMenu = null;
        Item item1 = new Item("Wadapav", 30);
        Item item2 = new Item("Puri bhaji", 60);
        Item item3 = new Item("Misal pav", 70);
        selectedMenu.add(item1);
        selectedMenu.add(item2);
        selectedMenu.add(item3);
    }

    @Test
    public void adding_item_to_menu_should_display_bill() {

        List<Item> menu = restaurant.getMenu();
        int totalPrice = 388;
        int actualTotalPrice = restaurant.displaybill(menu);
        assertEquals(totalPrice, actualTotalPrice);
    }
}