package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    @Override
    public ResponseEntity getAvailableOffers(String clientDataId) {
        return null;
    }
}
