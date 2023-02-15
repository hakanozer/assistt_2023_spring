package com.works.controllers;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class DashboardController {

    final ProductService productService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(defaultValue = "0") int pageCount) {
        Page<Product> page = productService.list(pageCount);
        int[] pages = new int[page.getTotalPages()];
        model.addAttribute("list", page );
        model.addAttribute("pages", pages);
        return "dashboard";
    }

    @PostMapping("/productAdd")
    public String productAdd(Product product) {
        productService.save(product);
        return "redirect:/dashboard";
    }

    @GetMapping("/productDelete/{pid}")
    public String productDelete(@PathVariable String pid) {
        boolean deleteStatus = productService.delete(pid);
        return "redirect:/dashboard";
    }

}
