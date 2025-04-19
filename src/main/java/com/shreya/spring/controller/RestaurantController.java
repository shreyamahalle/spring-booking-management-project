package com.shreya.spring.controller;

import com.shreya.spring.exception.CustomerException;
import com.shreya.spring.model.Restaurant;
import com.shreya.spring.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.SQLException;
import java.util.Scanner;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class RestaurantController {

    private final Scanner sc = new Scanner(System.in);
    Restaurant restaurant = new Restaurant();
    private RestaurantService restaurantService;

    public void run() {

        int option;
        do {
            System.out.println("\n---- Restaurant ----");
            System.out.println("1. Add Restaurant");
            System.out.println("2. View Restaurant Details");
            System.out.println("3. create Restaurant on db");
            System.out.println("4. delete Restaurant on db");
            System.out.println("5. Retrieve Restaurant");
            System.out.println("6. Update Restaurant");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            option = Integer.parseInt(sc.nextLine());
            try {
                switch (option) {
                    case 1:
                        RestaurantService.createRestaurant();
                        RestaurantService.displayRestaurant();
                        System.out.print("restaurant " + restaurant);
                        break;
                    case 2:
                        RestaurantService.displayRestaurant();
                        break;
                    case 3:
                        System.out.println("Performing create operation on Restaurant");
                        RestaurantService.insertRestaurant(new Restaurant(101, "Rai", "pune", "kharadi"));
                        break;
                    case 4:
                        System.out.println("delete Restaurant");
                        RestaurantService.deleteRestaurant();
                        break;
                    case 5:
                        System.out.println("Retrieve Restaurant");
                        RestaurantService.retrieveRestaurants().forEach(restaurant -> {
                            System.out.println("restaurant ID: " + restaurant.getRegisterNo() + ", name: " + restaurant.getName());
                        });
                        break;
                    case 6:
                        System.out.println("Update Restaurant");
                        RestaurantService.updateRestaurant();
                        break;

                    case 0:
                        System.out.println("Returning to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (CustomerException e) {
                System.out.println("Error: " + e.getClass());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } while (option != 0);
    }
}


