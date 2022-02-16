package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.sub_group.SubGroup;
import ee.bcs.eetsy.domain.sub_group.SubGroupDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ItemMapper {
    Item itemDtoToItem(ItemDto itemDto);

    ItemDto itemToItemDto(Item item);

    List<ItemDto> itemsToItemsDto(List<Item> items);




    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemFromItemDto(ItemDto itemDto, @MappingTarget Item item);
}
