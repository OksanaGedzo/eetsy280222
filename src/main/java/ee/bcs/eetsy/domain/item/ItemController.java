package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.sub_group.SubGroupItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController

public class ItemController {


    @Resource
    private ItemService itemService;


    @GetMapping("/items/")
    @Operation(summary = "Get'i kõik sub_groupi itemide andmed")
    public List<ItemDto> Items() {
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
    public ItemDto GetProduct (Integer id) {
        ItemDto itemDto = itemService.findItemdById(id);
        return itemDto;
    }



}
