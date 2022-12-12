package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.AddressDTO;
import com.comarch.fiberBilling.model.dto.ClientDataDTO;
import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.model.dto.OrderItemDTO;
import com.comarch.fiberBilling.model.entity.Address;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.model.entity.ClientType;
import com.comarch.fiberBilling.model.entity.Order;
import com.comarch.fiberBilling.model.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-14T12:24:26+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDTO orderItemToOrderItemDto(OrderItem orderItem) {
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

    @Override
    public OrderItem orderItemDtoToOrderItem(OrderItemDTO orderItemDTO) {
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

    @Override
    public List<OrderItemDTO> orderItemsListToOrderItemsDtosList(List<OrderItem> orderItems) {
        if ( orderItems == null ) {
            return null;
        }

        List<OrderItemDTO> list = new ArrayList<OrderItemDTO>( orderItems.size() );
        for ( OrderItem orderItem : orderItems ) {
            list.add( orderItemToOrderItemDto( orderItem ) );
        }

        return list;
    }

    @Override
    public List<OrderItem> orderItemsDtosListToOrderItemsList(List<OrderItemDTO> orderItemDTOS) {
        if ( orderItemDTOS == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( orderItemDTOS.size() );
        for ( OrderItemDTO orderItemDTO : orderItemDTOS ) {
            list.add( orderItemDtoToOrderItem( orderItemDTO ) );
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
}
