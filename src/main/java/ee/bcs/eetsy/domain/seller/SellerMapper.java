package ee.bcs.eetsy.domain.seller;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SellerMapper {
    Seller sellerDtoToSeller(SellerDto sellerDto);

    SellerDto sellerToSellerDto(Seller seller);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSellerFromSellerDto(SellerDto sellerDto, @MappingTarget Seller seller);
}
