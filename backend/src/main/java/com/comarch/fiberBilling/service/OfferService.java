package com.comarch.fiberBilling.service;

import org.springframework.http.ResponseEntity;

public interface OfferService {
    ResponseEntity getAvailableOffers(String clientDataId);

}

