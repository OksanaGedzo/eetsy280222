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

    @GetMapping("/get/product/by/id")
    @Operation(summary = "Get'i ühe itemi andmed by id")
    public ItemRequest getProduct (@RequestParam Integer id) {
        ItemRequest itemRequest = itemService.findItemdById(id);
        return itemRequest;
    }

    @PostMapping("/add/item")
    @Operation(summary = "Add an item")
    public RequestResponse addItem (@RequestBody ItemNewSubGroupRequest itemRequest) {
        RequestResponse response = itemService.addItem(itemRequest);
        return response;
    }

    @PutMapping("/update/item")
    @Operation(summary = "Update an item")
    public RequestResponse updateItem (@RequestBody ItemNewSubGroupRequest itemRequest) {
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
