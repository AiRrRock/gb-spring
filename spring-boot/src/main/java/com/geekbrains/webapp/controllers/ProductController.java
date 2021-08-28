package com.geekbrains.webapp.controllers;

import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.services.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    private ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/products")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productDAO.findAll());
        return "products";
    }

    @GetMapping("/product/{id}")
    public String showProduct(Model model, @PathVariable Long id) {
        model.addAttribute("product", productDAO.findByIdWithOrders(id));
        return "product_info";
    }

    @GetMapping("/product/increment/{id}")
    public String incrementProduct(Model model, @PathVariable Long id) {
        Product p = productDAO.findById(id);
        int price = p.getPrice();
        p.setPrice(++price);
        model.addAttribute("products", productDAO.findAll());
        return "products";
    }

    @GetMapping("/product/decrement/{id}")
    public String decrementProduct(Model model, @PathVariable Long id) {
        Product p = productDAO.findById(id);
        int price = p.getPrice();
        p.setPrice(--price);
        model.addAttribute("products", productDAO.findAll());
        return "products";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(Model model, @PathVariable Long id) {
        productDAO.deleteById(id);
        model.addAttribute("products", productDAO.findAll());
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String showCreateForm() {
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam Long id, @RequestParam String name, @RequestParam int score) {
        Product product = new Product(id, name, score);
        productDAO.saveOrUpdate(product);
        return "redirect:/products";
    }
}
