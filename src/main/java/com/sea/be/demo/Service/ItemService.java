package com.sea.be.demo.Service;

import com.sea.be.demo.Dto.ItemRequest;
import com.sea.be.demo.Entity.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    void createItem(ItemRequest itemRequest);

    void updateItem(ItemRequest itemRequest, Long id);

    void deleteItem(Long id);

    List<Item> getAllItems();

    List<Item> getItemsByUserId(Long userId);

    List<Item> getItemsByCategory(Long categoryId);

    Item getItemById(Long id);
}
