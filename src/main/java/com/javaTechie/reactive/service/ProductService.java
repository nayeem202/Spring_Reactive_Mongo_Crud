package com.javaTechie.reactive.service;


import com.javaTechie.reactive.dto.ProductDto;
import com.javaTechie.reactive.entity.Product;
import com.javaTechie.reactive.repository.ProductRepository;
import com.javaTechie.reactive.util.Apputil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Flux<ProductDto> getProducts(){
        return productRepository.findAll().map(Apputil::entityToDto);
    }
    public Mono<ProductDto> getProduct (String id){
        return productRepository.findById(id).map(Apputil::entityToDto);
    }

    public Flux<ProductDto> getProductRange(double min, double max){
        return  productRepository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<ProductDto> saveProduct (Mono<ProductDto> productDtoMono){
       return productDtoMono.map(Apputil::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(Apputil::entityToDto);

    }
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id){
       return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(Apputil::dtoToEntity)
                .doOnNext(e -> e.setId(id)))
                        .flatMap(productRepository::save)
                        .map(Apputil::entityToDto);

    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }


}
