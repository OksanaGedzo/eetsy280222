package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.sub_group.SubGroup;
import ee.bcs.eetsy.domain.sub_group.SubGroupDto;
import ee.bcs.eetsy.domain.sub_group.SubGroupRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@Service
public class ItemService {

    @Resource
    private ItemRepository itemRepository;

    @Resource
    private SubGroupRepository subGroupRepository;

    @Resource
    private ItemMapper itemMapper;

    public List<ItemDto> findAllItems() {
        List<Item> allItems = itemRepository.findAll();
        List<ItemDto> allItemsDtos = itemMapper.itemsToItemsDto(allItems);
        return allItemsDtos;
    }

//    public ItemDto findItemByName(String name) {
//        Item itemByName = itemRepository.findByName(name);
//        ItemDto itemByNameDto  = itemMapper.itemToItemDto(itemByName);
//        return itemByNameDto;
//    }

    public ItemDto findItemdById(Integer id) {
        Optional<Item> itemById = itemRepository.findById(id);
        ItemDto itemDto = itemMapper.itemToItemDto(itemById.get());
        return itemDto;
    }




}
