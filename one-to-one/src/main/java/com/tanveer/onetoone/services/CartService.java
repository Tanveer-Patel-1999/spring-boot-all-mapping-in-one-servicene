package com.tanveer.onetoone.services;

import com.tanveer.onetoone.entity.CartEntity;
import com.tanveer.onetoone.entity.ItemEntity;
import com.tanveer.onetoone.model.CartRequest;
import com.tanveer.onetoone.model.CartResponse;
import com.tanveer.onetoone.model.ItemRequest;
import com.tanveer.onetoone.repository.CartRepository;
import com.tanveer.onetoone.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    public CartResponse createCart(CartRequest cartRequest) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartName(cartRequest.getCartName());
        cartEntity.setCartType(cartRequest.getCartType());
        cartRepository.save(cartEntity);

        List<ItemEntity> itemEntities = new ArrayList<>();
        for (ItemRequest item : cartRequest.getItems()) {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setItemName(item.getItemName());
            itemEntity.setPrice(item.getPrice());
            itemEntity.setCart(cartEntity);

            itemRepository.save(itemEntity);
            itemEntities.add(itemEntity);
        }
        cartEntity.setItems(itemEntities);

        CartResponse response = new CartResponse();
        response.setId(cartEntity.getId());
        return response;
    }

    public CartRequest getById(Long id) {
        CartRequest request = new CartRequest();
        Optional<CartEntity> cartEntityOptional = cartRepository.findById(id);
        if (cartEntityOptional.isPresent()) {
            request.setCartName(cartEntityOptional.get().getCartName());
            request.setCartType(cartEntityOptional.get().getCartType());

            CartEntity cartEntity = cartEntityOptional.get();
            List<ItemEntity> items = cartEntity.getItems();
            List<ItemRequest> itemRequests = new ArrayList<>();
            for (ItemEntity itemEntity : items) {
                ItemRequest item = new ItemRequest();
                item.setItemName(itemEntity.getItemName());
                item.setPrice(itemEntity.getPrice());
                itemRequests.add(item);
            }
            request.setItems(itemRequests);
        }
        return request;
    }

    public List<CartRequest> getAll() {
        return cartRepository.findAll().stream()
                .map(cartEntity -> {
                    CartRequest request = new CartRequest();
                    request.setCartName(cartEntity.getCartName());
                    request.setCartType(cartEntity.getCartType());

                    List<ItemRequest> itemRequests = new ArrayList<>();
                    List<ItemEntity> items = cartEntity.getItems();

                    for (ItemEntity itemEntity : items) {
                        ItemRequest item = new ItemRequest();
                        item.setItemName(itemEntity.getItemName());
                        item.setPrice(itemEntity.getPrice());
                        itemRequests.add(item);
                    }

                    request.setItems(itemRequests);
                    return request;
                }).collect(Collectors.toList());

    }

    public CartRequest updateById(CartRequest cartRequest, Long id) {
        Optional<CartEntity> optionalCartEntity = cartRepository.findById(id);
        if (optionalCartEntity.isPresent()) {
            CartEntity cartEntity = optionalCartEntity.get();
            cartEntity.setCartName(cartRequest.getCartName());
            cartEntity.setCartType(cartRequest.getCartType());

            List<ItemEntity> itemEntities = new ArrayList<>();

            for (ItemRequest item : cartRequest.getItems()) {
                Optional<ItemEntity> itemEntityOptional = itemRepository.findById(item.getId());
                if (itemEntityOptional.isPresent()) {
                    ItemEntity itemEntity = itemEntityOptional.get();
                    itemEntity.setItemName(item.getItemName());
                    itemEntity.setPrice(item.getPrice());
                    itemEntity.setCart(cartEntity);
                    itemRepository.save(itemEntity);
                    itemEntities.add(itemEntity);
                }
            }

            cartEntity.getItems().clear();
            cartEntity.getItems().addAll(itemEntities);
            cartRepository.save(cartEntity);
        }
        return cartRequest;
    }

    public void deleteById(Long id) {
        Optional<ItemEntity> itemEntityOptional = itemRepository.findById(id);
        if (itemEntityOptional.isPresent()) {
            itemRepository.deleteById(id);
        }
    }
}

/*
=========================================
package com.tanveer.onetoone.services;

import com.tanveer.onetoone.entity.CartEntity;
import com.tanveer.onetoone.entity.ItemEntity;
import com.tanveer.onetoone.model.CartRequest;
import com.tanveer.onetoone.model.CartResponse;
import com.tanveer.onetoone.model.ItemRequest;
import com.tanveer.onetoone.repository.CartRepository;
import com.tanveer.onetoone.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    public CartResponse createCart(CartRequest cartRequest) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartName(cartRequest.getCartName());
        cartEntity.setCartType(cartRequest.getCartType());

        List<ItemEntity> itemEntities = cartRequest.getItems().stream()
                .map(itemRequest -> {
                    ItemEntity itemEntity = new ItemEntity();
                    itemEntity.setItemName(itemRequest.getItemName());
                    itemEntity.setPrice(itemRequest.getPrice());
                    itemEntity.setCart(cartEntity);
                    return itemEntity;
                })
                .collect(Collectors.toList());

        cartEntity.setItems(itemEntities);
        cartRepository.save(cartEntity);

        CartResponse response = new CartResponse();
        response.setId(cartEntity.getId());
        return response;
    }

    public CartRequest getById(Long id) {
        Optional<CartEntity> cartEntityOptional = cartRepository.findById(id);
        return cartEntityOptional.map(this::mapToCartRequest).orElse(null);
    }

    public List<CartRequest> getAll() {
        return cartRepository.findAll().stream()
                .map(this::mapToCartRequest)
                .collect(Collectors.toList());
    }

    public CartRequest updateById(CartRequest cartRequest, Long id) {
        Optional<CartEntity> optionalCartEntity = cartRepository.findById(id);
        if (optionalCartEntity.isPresent()) {
            CartEntity cartEntity = optionalCartEntity.get();
            cartEntity.setCartName(cartRequest.getCartName());
            cartEntity.setCartType(cartRequest.getCartType());

            List<ItemEntity> itemEntities = cartRequest.getItems().stream()
                    .map(itemRequest -> {
                        Optional<ItemEntity> itemEntityOptional = itemRepository.findById(itemRequest.getId());
                        return itemEntityOptional.map(itemEntity -> {
                            itemEntity.setItemName(itemRequest.getItemName());
                            itemEntity.setPrice(itemRequest.getPrice());
                            itemEntity.setCart(cartEntity);
                            return itemEntity;
                        }).orElse(null);
                    })
                    .collect(Collectors.toList());

            cartEntity.setItems(itemEntities);
            cartRepository.save(cartEntity);
        }
        return cartRequest;
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    private CartRequest mapToCartRequest(CartEntity cartEntity) {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setCartName(cartEntity.getCartName());
        cartRequest.setCartType(cartEntity.getCartType());

        List<ItemRequest> itemRequests = cartEntity.getItems().stream()
                .map(itemEntity -> {
                    ItemRequest itemRequest = new ItemRequest();
                    itemRequest.setItemName(itemEntity.getItemName());
                    itemRequest.setPrice(itemEntity.getPrice());
                    return itemRequest;
                })
                .collect(Collectors.toList());

        cartRequest.setItems(itemRequests);
        return cartRequest;
}
 */