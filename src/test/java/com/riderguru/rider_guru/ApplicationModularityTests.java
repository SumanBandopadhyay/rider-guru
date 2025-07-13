package com.riderguru.rider_guru;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.modulith.core.ApplicationModules;

class ApplicationModularityTests {
    
    @Test
    void bootstrapApplicationModules() {
        var modules = ApplicationModules.of(RiderGuruApplication.class);

        modules.verify();
        Assertions.assertFalse(modules.stream().count() == 0);
    }

}
