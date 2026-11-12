package com.vagdedes.spartan.abstraction.configuration.implementation;

import com.vagdedes.spartan.abstraction.configuration.ConfigurationBuilder;

public class Advanced extends ConfigurationBuilder {

    public Advanced() {
        super("advanced");
    }

    @Override
    public void create() {
    }

    @Override
    public final String getString(String path) {
        return super.getString(path);
    }

    @Override
    public final String getColorfulString(String path) {
        return super.getColorfulString(path);
    }

}
