package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.AddressDTO;
import com.comarch.fiberBilling.model.dto.ClientDataDTO;
import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.model.dto.OrderItemDTO;
import com.comarch.fiberBilling.model.dto.OrderItemParameterDTO;
import com.comarch.fiberBilling.model.dto.ParameterDTO;
import com.comarch.fiberBilling.model.dto.ParameterDetailDTO;
import com.comarch.fiberBilling.model.entity.Address;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.model.entity.ClientType;
import com.comarch.fiberBilling.model.entity.Order;
import com.comarch.fiberBilling.model.entity.OrderItem;
import com.comarch.fiberBilling.model.entity.OrderItemParameter;
import com.comarch.fiberBilling.model.entity.Parameter;
import com.comarch.fiberBilling.model.entity.ParameterDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-14T12:24:26+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
public class OrderItemParameterMapperImpl implements OrderItemParameterMapper {

    @Override
    public OrderItemParameterDTO entityToDto(OrderItemParameter orderItemParameter) {
        if ( orderItemParameter == null ) {
            return null;
        }

        OrderItemParameterDTO.OrderItemParameterDTOBuilder orderItemParameterDTO = OrderItemParameterDTO.builder();

        orderItemParameterDTO.id( orderItemParameter.getId() );
        orderItemParameterDTO.orderItem( orderItemToOrderItemDTO( orderItemParameter.getOrderItem() ) );
        orderItemParameterDTO.parameterDetail( parameterDetailToParameterDetailDTO( orderItemParameter.getParameterDetail() ) );

        return orderItemParameterDTO.build();
    }

    @Override
    public OrderItemParameter dtoToEntity(OrderItemParameterDTO orderItemParameterDTO) {
        if ( orderItemParameterDTO == null ) {
            return null;
        }

        OrderItemParameter.OrderItemParameterBuilder orderItemParameter = OrderItemParameter.builder();

        orderItemParameter.id( orderItemParameterDTO.getId() );
        orderItemParameter.orderItem( orderItemDTOToOrderItem( orderItemParameterDTO.getOrderItem() ) );
        orderItemParameter.parameterDetail( parameterDetailDTOToParameterDetail( orderItemParameterDTO.getParameterDetail() ) );

        return orderItemParameter.build();
    }

    @Override
    public List<OrderItemParameterDTO> entityListToDtoList(List<OrderItemParameter> orderItemParameters) {
        if ( orderItemParameters == null ) {
            return null;
        }

        List<OrderItemParameterDTO> list = new ArrayList<OrderItemParameterDTO>( orderItemParameters.size() );
        for ( OrderItemParameter orderItemParameter : orderItemParameters ) {
            list.add( entityToDto( orderItemParameter ) );
        }

        return list;
    }

    @Override
    public List<OrderItemParameter> dtoListToEntityList(List<OrderItemParameterDTO> orderItemParameterDTOS) {
        if ( orderItemParameterDTOS == null ) {
            return null;
        }

        List<OrderItemParameter> list = new ArrayList<OrderItemParameter>( orderItemParameterDTOS.size() );
        for ( OrderItemParameterDTO orderItemParameterDTO : orderItemParameterDTOS ) {
            list.add( dtoToEntity( orderItemParameterDTO ) );
        }

        return list;
    }

    protected AddressDTO addressToAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        addressDTO.id( address.getId() );
        addressDTO.country( address.getCountry() );
        addressDTO.city( address.getCity() );
        addressDTO.street( address.getStreet() );
        addressDTO.streetNumber( address.getStreetNumber() );
        addressDTO.houseNumber( address.getHouseNumber() );
        addressDTO.zipCode( address.getZipCode() );
        addressDTO.postOffice( address.getPostOffice() );

        return addressDTO.build();
    }

    protected ClientTypeDTO clientTypeToClientTypeDTO(ClientType clientType) {
        if ( clientType == null ) {
            return null;
        }

        ClientTypeDTO.ClientTypeDTOBuilder clientTypeDTO = ClientTypeDTO.builder();

        clientTypeDTO.id( clientType.getId() );
        clientTypeDTO.type( clientType.getType() );

        return clientTypeDTO.build();
    }

    protected ClientDataDTO clientDataToClientDataDTO(ClientData clientData) {
        if ( clientData == null ) {
            return null;
        }

        ClientDataDTO.ClientDataDTOBuilder clientDataDTO = ClientDataDTO.builder();

        clientDataDTO.id( clientData.getId() );
        clientDataDTO.name( clientData.getName() );
        clientDataDTO.surname( clientData.getSurname() );
        clientDataDTO.emailAddress( clientData.getEmailAddress() );
        clientDataDTO.address( addressToAddressDTO( clientData.getAddress() ) );
        clientDataDTO.login( clientData.getLogin() );
        clientDataDTO.clientType( clientTypeToClientTypeDTO( clientData.getClientType() ) );

        return clientDataDTO.build();
    }

    protected OrderDTO orderToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO.OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.id( order.getId() );
        orderDTO.orderStatus( order.getOrderStatus() );
        orderDTO.orderStartDate( order.getOrderStartDate() );
        orderDTO.orderEndDate( order.getOrderEndDate() );
        orderDTO.businessKey( order.getBusinessKey() );
        orderDTO.clientData( clientDataToClientDataDTO( order.getClientData() ) );

        return orderDTO.build();
    }

    protected OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDTO.OrderItemDTOBuilder orderItemDTO = OrderItemDTO.builder();

        orderItemDTO.id( orderItem.getId() );
        orderItemDTO.order( orderToOrderDTO( orderItem.getOrder() ) );
        orderItemDTO.orderItemName( orderItem.getOrderItemName() );
        orderItemDTO.activationDate( orderItem.getActivationDate() );
        orderItemDTO.status( orderItem.getStatus() );

        return orderItemDTO.build();
    }

    protected ParameterDTO parameterToParameterDTO(Parameter parameter) {
        if ( parameter == null ) {
            return null;
        }

        ParameterDTO.ParameterDTOBuilder parameterDTO = ParameterDTO.builder();

        parameterDTO.id( parameter.getId() );
        parameterDTO.name( parameter.getName() );

        return parameterDTO.build();
    }

    protected ParameterDetailDTO parameterDetailToParameterDetailDTO(ParameterDetail parameterDetail) {
        if ( parameterDetail == null ) {
            return null;
        }

        ParameterDetailDTO.ParameterDetailDTOBuilder parameterDetailDTO = ParameterDetailDTO.builder();

        parameterDetailDTO.id( parameterDetail.getId() );
        parameterDetailDTO.parameter( parameterToParameterDTO( parameterDetail.getParameter() ) );
        parameterDetailDTO.value( parameterDetail.getValue() );
        parameterDetailDTO.priceRegular( parameterDetail.getPriceRegular() );
        parameterDetailDTO.priceBusiness( parameterDetail.getPriceBusiness() );
        parameterDetailDTO.activeFrom( parameterDetail.getActiveFrom() );
        parameterDetailDTO.activeTo( parameterDetail.getActiveTo() );

        return parameterDetailDTO.build();
    }

    protected Address addressDTOToAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.id( addressDTO.getId() );
        address.country( addressDTO.getCountry() );
        address.city( addressDTO.getCity() );
        address.street( addressDTO.getStreet() );
        address.streetNumber( addressDTO.getStreetNumber() );
        address.houseNumber( addressDTO.getHouseNumber() );
        address.zipCode( addressDTO.getZipCode() );
        address.postOffice( addressDTO.getPostOffice() );

        return address.build();
    }

    protected ClientType clientTypeDTOToClientType(ClientTypeDTO clientTypeDTO) {
        if ( clientTypeDTO == null ) {
            return null;
        }

        ClientType.ClientTypeBuilder clientType = ClientType.builder();

        clientType.id( clientTypeDTO.getId() );
        clientType.type( clientTypeDTO.getType() );

        return clientType.build();
    }

    protected ClientData clientDataDTOToClientData(ClientDataDTO clientDataDTO) {
        if ( clientDataDTO == null ) {
            return null;
        }

        ClientData.ClientDataBuilder clientData = ClientData.builder();

        clientData.id( clientDataDTO.getId() );
        clientData.name( clientDataDTO.getName() );
        clientData.surname( clientDataDTO.getSurname() );
        clientData.emailAddress( clientDataDTO.getEmailAddress() );
        clientData.address( addressDTOToAddress( clientDataDTO.getAddress() ) );
        clientData.login( clientDataDTO.getLogin() );
        clientData.clientType( clientTypeDTOToClientType( clientDataDTO.getClientType() ) );

        return clientData.build();
    }

    protected Order orderDTOToOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderDTO.getId() );
        order.orderStatus( orderDTO.getOrderStatus() );
        order.orderStartDate( orderDTO.getOrderStartDate() );
        order.orderEndDate( orderDTO.getOrderEndDate() );
        order.businessKey( orderDTO.getBusinessKey() );
        order.clientData( clientDataDTOToClientData( orderDTO.getClientData() ) );

        return order.build();
    }

    protected OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.id( orderItemDTO.getId() );
        orderItem.order( orderDTOToOrder( orderItemDTO.getOrder() ) );
        orderItem.orderItemName( orderItemDTO.getOrderItemName() );
        orderItem.activationDate( orderItemDTO.getActivationDate() );
        orderItem.status( orderItemDTO.getStatus() );

        return orderItem.build();
    }

    protected Parameter parameterDTOToParameter(ParameterDTO parameterDTO) {
        if ( parameterDTO == null ) {
            return null;
        }

        Parameter.ParameterBuilder parameter = Parameter.builder();

        parameter.id( parameterDTO.getId() );
        parameter.name( parameterDTO.getName() );

        return parameter.build();
    }

    protected ParameterDetail parameterDetailDTOToParameterDetail(ParameterDetailDTO parameterDetailDTO) {
        if ( parameterDetailDTO == null ) {
            return null;
        }

        ParameterDetail.ParameterDetailBuilder parameterDetail = ParameterDetail.builder();

        parameterDetail.id( parameterDetailDTO.getId() );
        parameterDetail.parameter( parameterDTOToParameter( parameterDetailDTO.getParameter() ) );
        parameterDetail.value( parameterDetailDTO.getValue() );
        parameterDetail.priceRegular( parameterDetailDTO.getPriceRegular() );
        parameterDetail.priceBusiness( parameterDetailDTO.getPriceBusiness() );
        parameterDetail.activeFrom( parameterDetailDTO.getActiveFrom() );
        parameterDetail.activeTo( parameterDetailDTO.getActiveTo() );

        return parameterDetail.build();
    }
}
