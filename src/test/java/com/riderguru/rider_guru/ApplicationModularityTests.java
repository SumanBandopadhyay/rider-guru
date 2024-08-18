package com.riderguru.rider_guru;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ApplicationModularityTests {
    
    @Test
    void bootstrapApplicationModules() {
        var modules = ApplicationModules.of(RiderGuruApplication.class);

		System.out.println(modules);    }

}
