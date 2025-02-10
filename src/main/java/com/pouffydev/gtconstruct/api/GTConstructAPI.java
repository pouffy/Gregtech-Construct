package com.pouffydev.gtconstruct.api;

import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.common.material.IMaterialLinkRegistryManager;

public class GTConstructAPI {
    /**
     * Will always be available
     */
    public static GTConstruct instance;
    /**
     * Will be available at the Construction stage
     */
    public static IMaterialLinkRegistryManager materialLinkManager;
}
