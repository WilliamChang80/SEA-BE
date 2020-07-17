package com.sea.be.demo.Service.Impl;

import com.sea.be.demo.Dto.ItemRequest;
import com.sea.be.demo.Dto.ItemResponse;
import com.sea.be.demo.Dto.UserResponse;
import com.sea.be.demo.Entity.Category;
import com.sea.be.demo.Entity.Item;
import com.sea.be.demo.Entity.User;
import com.sea.be.demo.Repository.CategoryRepository;
import com.sea.be.demo.Repository.ItemRepository;
import com.sea.be.demo.Repository.UserRepository;
import com.sea.be.demo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    private CategoryRepository categoryRepository;

    private UserRepository userRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository,
                           UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createItem(@RequestBody ItemRequest itemRequest) {
        Category category = categoryRepository.findById(itemRequest.getCategoryId()).orElse(null);
        Item item = Item.builder().name(itemRequest.getName()).category(category).description(itemRequest.
                getDescription()).price(itemRequest.getPrice()).userId(itemRequest.getUserId()).build();
        itemRepository.save(item);
    }

    @Override
    public void updateItem(@RequestBody ItemRequest itemRequest, Long id) {
        Category category = categoryRepository.findById(itemRequest.getCategoryId()).orElse(null);
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new EntityNotFoundException("Item Not Found");
        }
        item.setName(itemRequest.getName()).setCategory(category).setDescription(itemRequest.getDescription())
                .setPrice(itemRequest.getPrice()).setUserId(itemRequest.getUserId());
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new EntityNotFoundException("Item Not Found");
        }
        itemRepository.delete(item);
    }

    @Override
    public List<ItemResponse>getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponse> itemResponses = items.stream().map(this::convertItemToResponse).collect(Collectors.toList());
        return itemResponses;
    }

    @Override
    public List<ItemResponse> getItemsByUserId(Long userId) {
        List<Item> items = itemRepository.findAllByUserId(userId);
        List<ItemResponse> itemResponses = items.stream().map(this::convertItemToResponse).collect(Collectors.toList());
        return itemResponses;
    }

    @Override
    public List<ItemResponse> getItemsByCategory(Long categoryId) {
        List<Item> items = itemRepository.findAllByCategoryId(categoryId);
        List<ItemResponse> itemResponses = items.stream().map(this::convertItemToResponse).collect(Collectors.toList());
        return itemResponses;
    }

    @Override
    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new EntityNotFoundException("Item Not Found");
        }
        return convertItemToResponse(item);
    }

    private ItemResponse convertItemToResponse(Item item) {
        User user= userRepository.findById(item.getUserId()).orElse(null);
        return ItemResponse.builder().id(item.getId()).category(item.getCategory()).description(item.getDescription())
                .name(item.getName()).price(item.getPrice()).user(UserResponse.builder().id(user.getId())
                        .username(user.getName()).build()).build();
    }
}
