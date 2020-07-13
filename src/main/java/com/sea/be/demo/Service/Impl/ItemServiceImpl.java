package com.sea.be.demo.Service.Impl;

import com.sea.be.demo.Dto.ItemRequest;
import com.sea.be.demo.Entity.Category;
import com.sea.be.demo.Entity.Item;
import com.sea.be.demo.Repository.CategoryRepository;
import com.sea.be.demo.Repository.ItemRepository;
import com.sea.be.demo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createItem(ItemRequest itemRequest) {
        Category category = categoryRepository.findById(itemRequest.getCategoryId()).orElse(null);
        Item item = Item.builder().name(itemRequest.getName()).category(category).description(itemRequest.
                getDescription()).price(itemRequest.getPrice()).userId(itemRequest.getUserId()).build();
        itemRepository.save(item);
    }

    @Override
    public void updateItem(ItemRequest itemRequest, Long id) {
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
    public List<Item> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public List<Item> getItemsByUserId(Long userId) {
        List<Item> items = itemRepository.findAllByUserId(userId);
        return items;
    }

    @Override
    public List<Item> getItemsByCategory(Long categoryId) {
        List<Item> items = itemRepository.findAllByCategoryId(categoryId);
        return items;
    }

    @Override
    public Item getItemById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new EntityNotFoundException("Item Not Found");
        }
        return item;
    }
}
