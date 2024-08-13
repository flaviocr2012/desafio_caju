package com.desafio.caju.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MerchantMCCMappingTest {

    private InMemoryMerchantMCCMapping merchantMCCMapping;

    @BeforeEach
    public void setUp() {
        merchantMCCMapping = new InMemoryMerchantMCCMapping();
        merchantMCCMapping.addMapping("UBER EATS SAO PAULO BR", "5812");
        merchantMCCMapping.addMapping("UBER TRIP SAO PAULO BR", "4121");
        merchantMCCMapping.addMapping("PAG*JoseDaSilva  RIO DE JANEI BR", "6012");
        merchantMCCMapping.addMapping("PICPAY*BILHETEUNICO GOIANIA BR", "6012");
    }

    @Test
    public void testGetMCCByMerchant_ShouldReturnMCCWhenMerchantExists() {
        String merchant = "UBER EATS SAO PAULO BR";
        String expectedMCC = "5812";

        Optional<String> result = merchantMCCMapping.getMCCByMerchant(merchant);

        assertTrue(result.isPresent(), "MCC should be present for known merchant");
        assertEquals(expectedMCC, result.get(), "MCC should match the expected value");
    }

    @Test
    public void testGetMCCByMerchant_ShouldReturnEmptyWhenMerchantDoesNotExist() {
        String merchant = "UNKNOWN MERCHANT";

        Optional<String> result = merchantMCCMapping.getMCCByMerchant(merchant);

        assertTrue(result.isEmpty(), "MCC should be empty for unknown merchant");
    }
}
