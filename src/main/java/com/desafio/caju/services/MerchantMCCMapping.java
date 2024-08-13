package com.desafio.caju.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MerchantMCCMapping {

    private final Map<String, String> merchantMCCMap;

    public MerchantMCCMapping() {
        merchantMCCMap = new HashMap<>();
        merchantMCCMap.put("UBER EATS SAO PAULO BR", "5812");
        merchantMCCMap.put("UBER TRIP SAO PAULO BR", "4121");
        merchantMCCMap.put("PAG*JoseDaSilva RIO DE JANEI BR", "6012");
        merchantMCCMap.put("PICPAY*BILHETEUNICO GOIANIA BR", "6012");
    }

    public Optional<String> getMCCByMerchant(String merchant) {
        return Optional.ofNullable(merchantMCCMap.get(merchant));
    }
}

