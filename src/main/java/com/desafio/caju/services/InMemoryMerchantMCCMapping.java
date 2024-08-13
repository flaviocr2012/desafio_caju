package com.desafio.caju.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryMerchantMCCMapping extends MerchantMCCMapping {
    private final Map<String, String> merchantMCCMap = new HashMap<>();

    public void addMapping(String merchant, String mcc) {
        merchantMCCMap.put(merchant, mcc);
    }

    @Override
    public Optional<String> getMCCByMerchant(String merchant) {
        return Optional.ofNullable(merchantMCCMap.get(merchant));
    }
}

