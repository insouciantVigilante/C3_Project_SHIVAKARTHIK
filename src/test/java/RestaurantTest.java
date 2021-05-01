import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RestaurantTest {

	Restaurant restaurant;
	LocalTime openingTime = LocalTime.parse("10:30:00");
	LocalTime closingTime = LocalTime.parse("22:00:00");
	List<String> menuItems = null;

	// REFACTOR ALL THE REPEATED LINES OF CODE

	// >>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	// -------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN
	// INTO ANY TROUBLE

	@Test
	void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {

		restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);
		assertEquals(true, restaurant.isRestaurantOpen());
		// WRITE UNIT TEST CASE HERE
	}

	@Test
	void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
		restaurant = new Restaurant("Amelie's cafe", "Chennai", LocalTime.now(), LocalTime.now());
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);
		assertEquals(false, restaurant.isRestaurantOpen());
		// WRITE UNIT TEST CASE HERE

	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	void adding_item_to_menu_should_increase_menu_size_by_1() {

		restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);

		int initialMenuSize = restaurant.getMenu().size();
		restaurant.addToMenu("Sizzling brownie", 319);
		assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
	}

	@Test
	void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

		restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);

		int initialMenuSize = restaurant.getMenu().size();
		restaurant.removeFromMenu("Vegetable lasagne");
		assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
	}

	@Test
	void removing_item_that_does_not_exist_should_throw_exception() {

		restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);

		assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
	}
	// <<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// <<<<<<<<<<<<<<<<<<<<<<<Order>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	@Test
	void adding_zero_items_to_the_menu_should_give_correct_order_value() {
		restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		menuItems = new ArrayList<>();
		assertEquals(0, restaurant.orderValue(menuItems));
	}

	@Test
	void orderValue_should_always_return_zero() {
		restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);
		menuItems = new ArrayList<>();
		menuItems.add("Sweet corn soup");
		menuItems.add("Vegetable lasagne");
		assertEquals(0, restaurant.orderValue(menuItems));
	}
}