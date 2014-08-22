package io.nuun.plugin.configuration.common;

import io.nuun.kernel.spi.configuration.NuunProperty;

//@Nuun
public class ProxifiedHolder
{
    @NuunProperty("private1")
    private String    private1;
    
    @AnnoCustomProperty("private1")
    private String    private2;
    

//    @DummyMethod
    public String private1()
    {
        return private1;
    }
    
//    @DummyMethod
    public String private2()
    {
        return private2;
    }
}
