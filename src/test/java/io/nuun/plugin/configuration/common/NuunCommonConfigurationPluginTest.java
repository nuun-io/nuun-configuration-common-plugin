package io.nuun.plugin.configuration.common;

import static io.nuun.kernel.core.NuunCore.createKernel;
import static io.nuun.kernel.core.NuunCore.newKernelConfiguration;
import static org.fest.assertions.Assertions.assertThat;
import io.nuun.kernel.api.Kernel;
import io.nuun.kernel.core.AbstractPlugin;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;

public class NuunCommonConfigurationPluginTest
{

    static Injector      injector;
    
    @BeforeClass
    public static void init()
    {
        
        Kernel kernel = createKernel(
                //
                newKernelConfiguration() //
                .params( //
                        Kernel.NUUN_ROOT_PACKAGE , NuunCommonConfigurationPluginTest.class.getPackage().getName()
                        ) //
                .plugins(
                        new AbstractPlugin()
                        {
                            
                            @Override
                            public String name()
                            {
                                return "internal plugin";
                            }
                            
                            @Override
                            public String pluginPackageRoot() {
                                return NuunCommonConfigurationPluginTest.class.getPackage().getName();
                            };
                            
                            @Override
                            public Object dependencyInjectionDef() {
                                
                                return new AbstractModule()
                                {
                                    
                                    @Override
                                    protected void configure()
                                    {
                                        bind(Holder.class);
                                    }
                                };
                                
                            };
                        }
                        )
                
                
                );
        
        
        kernel.init();
        kernel.start();
        
        injector = kernel.getObjectGraph().as(Injector.class);
    }
    
    
    @Test
    public void property_should_be_reachable_via_annotation()
    {
        Holder holder = injector.getInstance(Holder.class);

//
//
//        assertThat(holder.moduleAnno).isNotNull();
//        assertThat(holder.moduleAnno).isEqualTo("Hi!");
        
        assertThat(holder.id).isNotNull();
        assertThat(holder.id).isEqualTo(123l);
        assertThat(holder.password).isNotNull();
        assertThat(holder.password).isEqualTo("mypassword");

        assertThat(holder._int).isNotNull();
        assertThat(holder._int).isEqualTo(1);
//        assertThat(holder._Integer).isNotNull();
//        assertThat(holder._Integer).isEqualTo(1);
        assertThat(holder.__Integer).isNotNull();
        assertThat(holder.__Integer).isEqualTo("1");

        assertThat(holder._long).isNotNull();
        assertThat(holder._long).isEqualTo(2);
        assertThat(holder._Long).isNotNull();
        assertThat(holder._Long).isEqualTo(2);
        assertThat(holder.__Long).isNotNull();
        assertThat(holder.__Long).isEqualTo("2");

        assertThat(holder._short).isNotNull();
        assertThat(holder._short).isEqualTo((short) 3);
        assertThat(holder._Short).isNotNull();
        assertThat(holder._Short).isEqualTo((short) 3);
        assertThat(holder.__Short).isNotNull();
        assertThat(holder.__Short).isEqualTo("3");

        assertThat(holder._byte).isNotNull();
        assertThat(holder._byte).isEqualTo((byte) 4);
        assertThat(holder._Byte).isNotNull();
        assertThat(holder._Byte).isEqualTo((byte) 4);
        assertThat(holder.__Byte).isNotNull();
        assertThat(holder.__Byte).isEqualTo("4");

        assertThat(holder._float).isNotNull();
        assertThat(holder._float).isEqualTo(5.5f);
        assertThat(holder._Float).isNotNull();
        assertThat(holder._Float).isEqualTo(5.5f);
        assertThat(holder.__Float).isNotNull();
        assertThat(holder.__Float).isEqualTo("5.5");

        assertThat(holder._double).isNotNull();
        assertThat(holder._double).isEqualTo(6.6d);
        assertThat(holder._Double).isNotNull();
        assertThat(holder._Double).isEqualTo(6.6d);
        assertThat(holder.__Double).isNotNull();
        assertThat(holder.__Double).isEqualTo("6.6");

        assertThat(holder._char).isNotNull();
        assertThat(holder._char).isEqualTo('g');
        assertThat(holder._Character).isNotNull();
        assertThat(holder._Character).isEqualTo('g');
        assertThat(holder.__Character).isNotNull();
        assertThat(holder.__Character).isEqualTo("g");

        assertThat(holder._booleanTrue).isNotNull();
        assertThat(holder._booleanTrue).isEqualTo(true);
        assertThat(holder._BooleanTrue).isNotNull();
        assertThat(holder._BooleanTrue).isEqualTo(true);
        assertThat(holder._BooleanTrue).isNotNull();
        assertThat(holder.__BooleanTrue).isEqualTo("true");

        assertThat(holder._booleanFalse).isNotNull();
        assertThat(holder._booleanFalse).isEqualTo(false);
        assertThat(holder._BooleanFalse).isNotNull();
        assertThat(holder._BooleanFalse).isEqualTo(false);
        assertThat(holder._BooleanFalse).isNotNull();
        assertThat(holder.__BooleanFalse).isEqualTo("false");
        // use of converter
        assertThat(holder.__special).isNotNull();
        assertThat(holder.__special.content ).hasSize(7);
        assertThat(holder.__special.content ).containsOnly("epo" , "jemba", "the" , "master" , "of" , "the" , "universe");
        
        assertThat(holder.__nothing).isNotNull();
        assertThat(holder.__nothing).isEqualTo("");

        assertThat(holder.private1()).isNotNull();
        assertThat(holder.private1()).isEqualTo("private1");
        assertThat(holder.private2()).isNotNull();
        assertThat(holder.private2()).isEqualTo("private2");
        assertThat(holder.private1()).isNotNull();
        assertThat(holder.private1()).isEqualTo("private1");
        assertThat(holder.private2()).isNotNull();
        assertThat(holder.private2()).isEqualTo("private2");
    }
    
//    @Test
//    public void property_should_be_reachable_via_annotation_on_a_proxified_class()
//    {
//        ProxifiedHolder holder = injector.getInstance(ProxifiedHolder.class);
//        System.err.println("holder = " + holder);
//        assertThat(holder.private1()).isNotNull();
//        assertThat(holder.private1()).isEqualTo("private1");
//        assertThat(holder.private2()).isNotNull();
//        assertThat(holder.private2()).isEqualTo("private1");
//    }

}
