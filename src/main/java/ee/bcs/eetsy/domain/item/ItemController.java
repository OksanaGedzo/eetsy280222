package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.sub_group.SubGroupItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController

public class ItemController {


    @Resource
    private ItemService itemService;


    @GetMapping("/items/")
    @Operation(summary = "Get'i kõik sub_groupi itemide andmed")
    public List<ItemDto> items() {
        List<ItemDto> items = itemService.findAllItems();
        return items;
    }

//    @GetMapping("/item/by/name")
//    @Operation(summary = "Get'i itemi andmed by name")
//    public ItemDto ItemByName(String name) {
//        ItemDto itemByNameDto = itemService.findItemByName(name);
//        return itemByNameDto;
//    }

    @GetMapping("/get/product/by/id")
    @Operation(summary = "Get'i ühe itemi andmed by id")
    public ItemPagetDto getProduct (@RequestParam Integer id) {
        ItemPagetDto itemDto = itemService.findItemdById(id);
        return itemDto;
    }

    @PostMapping("/add/item")
    @Operation(summary = "Add an item")
    public RequestResponse addItem (@RequestBody ItemRequest itemRequest) {
        RequestResponse response = itemService.addItem(itemRequest);
        return response;
    }

    @PutMapping("/update/item")
    @Operation(summary = "Update an item")
    public RequestResponse updateItem (@RequestBody ItemRequest itemRequest) {
        RequestResponse response = itemService.updateItem(itemRequest);
        return response;
    }

    @DeleteMapping("/delete/item")
    @Operation(summary = "Delete an item")
    public RequestResponse deleteItem (@RequestParam Integer itemId) {
        RequestResponse response = itemService.deleteItem(itemId);
        return response;
    }

}
