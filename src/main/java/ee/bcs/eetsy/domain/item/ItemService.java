package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.picture.Picture;
import ee.bcs.eetsy.domain.picture.PictureDto;
import ee.bcs.eetsy.domain.picture.PictureRepository;
import ee.bcs.eetsy.domain.picture.PictureResponse;
import ee.bcs.eetsy.domain.picture.item_picture.ItemPicture;
import ee.bcs.eetsy.domain.picture.item_picture.ItemPictureRepository;
import ee.bcs.eetsy.domain.primary_group.PrimaryGroupRepository;
import ee.bcs.eetsy.domain.seller.Seller;
import ee.bcs.eetsy.domain.seller.SellerRepository;
import ee.bcs.eetsy.domain.sub_group.SubGroup;
import ee.bcs.eetsy.domain.sub_group.SubGroupDto;
import ee.bcs.eetsy.domain.sub_group.SubGroupRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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
    @Resource
    private PictureRepository pictureRepository;
    @Resource
    private ItemPictureRepository itemPictureRepository;
    @Resource
    private PrimaryGroupRepository primaryGroupRepository;

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


    public RequestResponse updateItem(ItemNewSubGroupRequest itemRequest) {
        RequestResponse response = new RequestResponse();
        Optional<Item> oldItem = itemRepository.findById(itemRequest.getItemId());
        if (oldItem.isEmpty()) {
            response.setError("Item with the Id " + itemRequest.getItemId() + " not found");
            return response;
        }
        //this is wrong, we are mapping a NEW item from the request instead of finding
        //the old one and updating it
        //Item item = itemMapper.itemRequestToItem(itemRequest);
        //this should have been a hint since why would i need to set the seller again
        // if im just changing a few parameters on the item
        //item.setSeller(oldItem.get().getSeller());
        Item item = itemRepository.findById(itemRequest.getItemId()).get();
        item.setDescription(itemRequest.getDescription());
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        itemRepository.save(item);
        addItemRequestPicturesToDatabase(itemRequest, item);
        response.setMessage("Item Updated");
        return response;
    }

    public RequestResponse deleteItem(Integer itemId) {
        RequestResponse response = new RequestResponse();
        if (itemRepository.findById(itemId).isEmpty()) {
            response.setError("Item with the Id " + itemId + " not found");
            return response;
        }
        itemRepository.deleteById(itemId);
        response.setMessage("Item Deleted");
        return response;
    }

    public RequestResponse addItem(ItemNewSubGroupRequest itemRequest) {
        RequestResponse response = new RequestResponse();
        Optional<Seller> seller = sellerRepository.findById(itemRequest.getSellerId());

        if (seller.isEmpty()) {
            response.setError("Seller with the Id " + itemRequest.getSellerId() + " not found");
            return response;
        }
        Item item = itemMapper.ItemNewSubGroupRequestToItem(itemRequest);
        item.setSeller(seller.get());
        item = itemRepository.save(item);
        Integer itemId = item.getId();

        String subGroupName = itemRequest.getSubGroupName();
        SubGroup subGroup = new SubGroup();
        subGroup.setName(subGroupName);
        subGroup.setItem(item);
        if (itemRequest.getSubGroupPictureData()==null)
        {
            SubGroup templateSubGroup = subGroupRepository.findFirstByName(subGroupName).get(0);
            subGroup.setPicture(templateSubGroup.getPicture());
        } else {
            Picture picture = new Picture();
            picture.setData(itemRequest.getSubGroupPictureData().getBytes(StandardCharsets.UTF_8));
            picture = pictureRepository.save(picture);
            subGroup.setPicture(picture);
        }
        subGroup.setPrimaryGroup(primaryGroupRepository.findById(itemRequest.getPrimaryGroupId()).get());
        subGroupRepository.save(subGroup);

        addItemRequestPicturesToDatabase(itemRequest, item);
        response.setMessage(item.getName() + " saved to sub group "+ subGroup.getName());
        return response;
    }

    private void addItemRequestPicturesToDatabase(ItemNewSubGroupRequest itemNewSubGroupRequest, Item item) {
        //We need an already existing item from the database to set picture relationships
        List<PictureResponse> pictureDtoList = itemNewSubGroupRequest.getPictures();
        for (PictureResponse pictureResponse : pictureDtoList) {
            Picture picture = new Picture();
            picture.setData(pictureResponse.getData().getBytes(StandardCharsets.UTF_8));

            ItemPicture itemPicture = new ItemPicture();
            itemPicture.setPicture(pictureRepository.save(picture));
            itemPicture.setItem(item);
            itemPictureRepository.save(itemPicture);
        }
        System.out.println(pictureDtoList.size() + " images added into relation with item id " + item.getId());
    }

}
