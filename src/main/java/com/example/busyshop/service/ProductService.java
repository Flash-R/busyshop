package com.example.busyshop.service;

import com.example.busyshop.Enum.Category;
import com.example.busyshop.dto.requestdto.ProductRequestDto;
import com.example.busyshop.dto.responsedto.ProductResponseDto;
import com.example.busyshop.exception.SellerNotFoundException;
import com.example.busyshop.model.Product;
import com.example.busyshop.model.Seller;
import com.example.busyshop.repository.ProductRepository;
import com.example.busyshop.repository.SellerRepository;
import com.example.busyshop.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        //check if seller exists
        Seller seller = sellerRepository.findByEmail(productRequestDto.getSellerEmail());
        if(seller == null)
            throw new SellerNotFoundException("Invalid Seller Email Id");
        //Dto -> Entity

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        //adding the current product to the seller product list
        seller.getProductList().add(product);
        //saving the content to the database
        Seller savedSeller = sellerRepository.save(seller); //saving parent also saves the child

        //get the latest saved product
        Product latestProduct = savedSeller.getProductList().get(savedSeller.getProductList().size() - 1);

        ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(latestProduct);
        productResponseDto.setSellerName(savedSeller.getName());

        return productResponseDto;




        //preparing the responseDto


    }

    public List<ProductResponseDto> getProductByCategoryAndPriceGreater(Category category, int price) {
        //find the product which match the filter
        List<Product> productList = productRepository.FindByCategoryAndPriceGreater(category,price);
        //convert the matched products to responseDto
        List<ProductResponseDto> responseDtoList = new ArrayList<>();

        for (Product p: productList ) {
            responseDtoList.add(ProductTransformer.ProductToProductResponseDto(p));
        }

        return  responseDtoList;
    }
}
