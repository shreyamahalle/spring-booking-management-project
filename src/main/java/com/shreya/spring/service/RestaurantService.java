package com.shreya.spring.service;

import com.shreya.spring.model.Customer;
import com.shreya.spring.model.Restaurant;
import com.shreya.spring.repository.RestaurantRepository;

import java.sql.SQLException;
import java.util.*;

public class RestaurantService {
    private static final RestaurantRepository restaurantRepository = new RestaurantRepository();
    private static final HashMap<Integer, Restaurant> restaurants = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);


    public static List<Restaurant> retrieveRestaurants() {
        return restaurantRepository.retrieveRestaurants();
    }

    public static void insertRestaurant(Restaurant restaurant) throws SQLException {
        restaurantRepository.addRestaurant(restaurant);
    }

    public static void Restaurant(Restaurant restaurant) {

        restaurantRepository.retrieveRestaurant(1, "abc");
    }

    public static void deleteRestaurant() {

        try {
            if (restaurantRepository.deleteRestaurant(1)) {
                System.out.println("Restaurant deleted successfully!");
            } else {
                System.out.println("Failed to delete Restaurant.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateRestaurant() throws SQLException {
        if (restaurantRepository.updateRestaurant(2, "shreya")) {
            System.out.println("Restaurant updated successfully ");
        } else {
            System.out.println("Failed to update Restaurant");
        }

    }

    public static void createRestaurant() {

        Restaurant restaurant = new Restaurant();
        restaurantRepository.createRestaurant(restaurant);
        restaurantRepository.displayRestaurant(restaurant);
        restaurantRepository.displayRestaurantToBeClosed("name");
        try {

            System.out.println("Please enter registerNo");
            int registerNo = Integer.parseInt(sc.nextLine());

            System.out.println("Please enter name");
            String name = sc.nextLine();

            System.out.println("Please enter city");
            String city = sc.nextLine();

            System.out.println("Please enter area");
            String area = sc.nextLine();

            restaurant.setRegisterNo(registerNo);
            restaurant.setName(name);
            restaurant.setCity(city);
            restaurant.setArea(area);
            restaurants.put(1, restaurant);
        } catch (Exception e) {
            System.out.println("Invalid input type correct data");
        }
    }

    public static void displayRestaurant() {
        try {
//            Set<Map.Entry<Integer, Restaurant>> entrySet = restaurants.entrySet();
//            for (Map.Entry<Integer, Restaurant> customerEntry : entrySet) {
//                System.out.println("Customer Info: " + restaurants);
//            }
            //java 8 features forEach loop..
            restaurants.forEach((Id, restaurants) -> System.out.println("restaurant Id " + Id + " = restaurant Id " + restaurants));

        } catch (Exception e) {
            System.out.println("Invalid input type correct data");
        }
    }

    public void printCustomer() {
        System.out.println(restaurants);
    }
}