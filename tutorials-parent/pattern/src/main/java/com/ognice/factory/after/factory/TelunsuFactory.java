package com.ognice.factory.after.factory;

import com.ognice.factory.domain.Milk;
import com.ognice.factory.domain.TelunsuMilk;

public class TelunsuFactory implements Factory {
    public Milk getMilk() {
        return new TelunsuMilk();
    }
}
