package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.picture.Picture;
import ee.bcs.eetsy.domain.picture.PictureResponse;
import ee.bcs.eetsy.domain.sub_group.SubGroup;
import ee.bcs.eetsy.domain.sub_group.SubGroupDto;
import org.mapstruct.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "itemId", target = "id")
    Item itemDtoToItem(ItemDto itemDto);

    ItemDto itemToItemDto(Item item);
    List<ItemDto> itemsToItemsDto(List<Item> items);

    ItemResponse itemToItemResponse(Item item);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    Item ItemNewSubGroupRequestToItem(ItemNewSubGroupRequest ItemNewSubGroupRequest);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    Item itemRequestToItem(ItemRequest itemRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemFromItemDto(ItemDto itemDto, @MappingTarget Item item);

    @Mapping(source = "sellerName", target = "seller.name")
    Item itemPagetDtoToItem(ItemPagetDto itemPagetDto);

    @Mapping(source = "seller.name", target = "sellerName")
    ItemPagetDto itemToItemPagetDto(Item item);

    @Mapping(source = "seller.name", target = "sellerName")
    @Mapping(source = "seller.id", target = "sellerId")
    @Mapping(source = "id", target = "itemId")
    ItemRequest itemToItemRequest(Item item);

    @Mapping(source = "sellerName", target = "seller.name")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemFromItemPagetDto(ItemPagetDto itemPagetDto, @MappingTarget Item item);
}
