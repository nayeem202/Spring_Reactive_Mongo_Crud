package com.javaTechie.reactive.repository;

import com.javaTechie.reactive.dto.ProductDto;
import com.javaTechie.reactive.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

import java.util.Random;

@Repository
public interface ProductRepository extends ReactiveMongoRepository <Product, String>{
    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);

}
