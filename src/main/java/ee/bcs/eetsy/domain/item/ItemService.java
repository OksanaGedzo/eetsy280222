package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.seller.Seller;
import ee.bcs.eetsy.domain.seller.SellerRepository;
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
    @Resource
    private SellerRepository sellerRepository;

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

    public ItemPagetDto findItemdById(Integer id) {
        Optional<Item> itemById = itemRepository.findById(id);
        ItemPagetDto itemPagetDto = itemMapper.itemToItemPagetDto(itemById.get());
        return itemPagetDto;
    }


    public RequestResponse updateItem(ItemDto itemDto) {
        RequestResponse response = new RequestResponse();
        Optional<Item> oldItem = itemRepository.findById(itemDto.getItemId());
        if (oldItem.isEmpty()){
            response.setError("Item with the Id " + itemDto.getItemId() + " not found" );
            return response;
        }
        Item item = itemMapper.itemDtoToItem(itemDto);
        item.setSeller(oldItem.get().getSeller());
        itemRepository.save(item);
        response.setMessage("Item Updated");
        return response;
    }

    public RequestResponse deleteItem(Integer itemId){
        RequestResponse response = new RequestResponse();
        if(itemRepository.findById(itemId).isEmpty()){
            response.setError("Item with the Id " + itemId + " not found" );
            return response;
        }
        itemRepository.deleteById(itemId);
        response.setMessage("Item Deleted");
        return response;
    }

    public RequestResponse addItem(ItemRequest itemRequest) {
        RequestResponse response = new RequestResponse();
        Optional<Seller> seller = sellerRepository.findById(itemRequest.getSellerId());
        if(seller.isEmpty()){
            response.setError("Seller with the Id " + itemRequest.getSellerId() + " not found" );
            return response;
        }
        Item item = itemMapper.itemRequestToItem(itemRequest);
        item.setSeller(seller.get());
        response.setMessage("Item Added");
        return response;
    }
}
