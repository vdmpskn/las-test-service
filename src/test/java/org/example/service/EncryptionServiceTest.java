package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncryptionServiceTest {
    @Test
    void testEncryptDecryptSuccess() throws Exception {

        String encryptedData = EncryptionService.encrypt("test");

        String decryptedData = EncryptionService.decrypt(encryptedData);

        assertEquals("test", decryptedData);
    }

    @Test
    void testEncryptFailure() {
        assertThrows(Exception.class, () -> EncryptionService.encrypt(null));
    }

    @Test
    void testDecryptFailure() {
        assertThrows(Exception.class, () -> EncryptionService.decrypt("invalid"));
    }
}
