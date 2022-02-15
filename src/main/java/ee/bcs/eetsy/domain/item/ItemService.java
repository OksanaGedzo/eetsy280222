package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.sub_group.SubGroupRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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




}
