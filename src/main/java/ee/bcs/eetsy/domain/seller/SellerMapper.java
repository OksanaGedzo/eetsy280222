package ee.bcs.eetsy.domain.seller;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SellerMapper {
    Seller sellerDtoToSeller(SellerDto sellerDto);

    SellerDto sellerToSellerDto(Seller seller);

    @Mapping(source="id", target = "id")
    @Mapping( source="name", target = "name")
    SellerBasicDto sellerToSellerBasicDto(Seller seller);
    List<SellerBasicDto>sellersToSellerBasicDtos(List<Seller> sellers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSellerFromSellerDto(SellerDto sellerDto, @MappingTarget Seller seller);
}
