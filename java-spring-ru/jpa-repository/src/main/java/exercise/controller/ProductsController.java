package exercise.controller;

//import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping
    public List<Product> getProducts(@RequestParam Optional<Integer> min, @RequestParam Optional<Integer> max){
        if (min.isPresent() && max.isEmpty()){
            return productRepository
                    .findByPriceGreaterThan(min.get())
                    .stream()
                    .sorted(Comparator.comparing(p -> p.getPrice()))
                    .toList();}
        if (min.isEmpty() && max.isPresent()){
            return productRepository
                    .findByPriceLessThan(max.get())
                    .stream()
                    .sorted(Comparator.comparing(p -> p.getPrice()))
                    .toList();}
        if (min.isPresent() && max.isPresent()){
            return productRepository
                    .findByPriceBetween(min.get(), max.get())
                    .stream()
                    .sorted(Comparator.comparing(Product::getPrice))
                    .toList();
        } else return productRepository.findAll(Sort.by(Sort.Order.asc("price")));
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
