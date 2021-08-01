package ru.gb.lesson2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        Cart cart = context.getBean(Cart.class);
        System.out.println("Enter request in the following format operation add/rem/sho,<userId>,<productId>");
        {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));) {
                String command = "";
                while (true) {
                    // Reading data using readLine
                    command = reader.readLine();
                    // Printing the read line
                    if ("exit".equals(command)) break;
                    String[] parts = command.split(",");
                    if (parts.length != 3) {
                        System.out.println("incorrect command");
                    }
                    long userId = Long.parseLong(parts[1]);
                    long productId = Long.parseLong(parts[2]);
                    switch (parts[0]) {
                        case "add":
                            cart.addProductToCart(userId, productId);
                            break;
                        case "rem":
                            cart.removeProduct(userId, productId);
                            break;
                        case "sho":
                            System.out.println(cart.getProducts(userId));
                    }
                    System.out.println(command);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cart.addProductToCart(1, 1);
        cart.addProductToCart(1, 2);
        System.out.println(cart.getProducts(1));

        context.close();
    }
}
