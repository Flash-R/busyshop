package com.example.busyshop.controller;

import com.example.busyshop.Enum.Category;
import com.example.busyshop.dto.requestdto.ProductRequestDto;
import com.example.busyshop.dto.responsedto.ProductResponseDto;
import com.example.busyshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class productController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("get-product-by-category-and-price-greater")
    public ResponseEntity getProductByCategoryAndPriceGreater(@RequestParam("category") Category category,
                                                              @RequestParam("price") int price){
        List<ProductResponseDto> responseDtoList = productService.getProductByCategoryAndPriceGreater(category, price);
        return new ResponseEntity(responseDtoList, HttpStatus.FOUND);

    }
}
