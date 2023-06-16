package com.tanveer.onetoone.controller;

import com.tanveer.onetoone.model.CartRequest;
import com.tanveer.onetoone.model.CartResponse;
import com.tanveer.onetoone.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/one-to-many/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest cartRequest){
        CartResponse cartResponse = cartService.createCart(cartRequest);
        return new ResponseEntity<>(cartResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartRequest> getById(@PathVariable Long id){
        CartRequest request = cartService.getById(id);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartRequest>> getAll(){
        List<CartRequest> requests = cartService.getAll();
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartRequest> updateById(@RequestBody CartRequest cartRequest, @PathVariable Long id){
        CartRequest request = cartService.updateById(cartRequest,id);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/item/{item_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "item_id") Long id){
        cartService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cart_id}")
    public ResponseEntity<Void> deleteByCartId(@PathVariable(name = "cart_id") Long id){
        cartService.deleteByCartId(id);
        return ResponseEntity.ok().build();
    }
}
