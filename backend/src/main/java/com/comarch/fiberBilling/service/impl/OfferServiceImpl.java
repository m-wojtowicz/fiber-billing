package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.model.api.response.GetAllOffers;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.model.entity.Offer;
import com.comarch.fiberBilling.repository.ClientDataRepository;
import com.comarch.fiberBilling.repository.OfferRepository;
import com.comarch.fiberBilling.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Override
    public ResponseEntity getAvailableOffers() {
        List<Offer> offers = offerRepository.findAll();
        List<GetAllOffers> allOffers = new ArrayList<>();
        offers.forEach(o -> {
            allOffers.add(GetAllOffers.builder().
                    id(o.getId()).
                    offerName(o.getOfferName()).
                    product(o.getProduct()).
                    build());
        });
        return ResponseEntity.ok(allOffers);
    }
}
