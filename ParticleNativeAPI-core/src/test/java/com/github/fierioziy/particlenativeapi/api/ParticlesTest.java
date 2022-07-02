package com.github.fierioziy.particlenativeapi.api;

import com.github.fierioziy.particlenativeapi.api.types.*;
import com.github.fierioziy.particlenativeapi.api.utils.ParticleException;
import com.github.fierioziy.particlenativeapi.core.ParticleNativeCoreTest;
import org.bukkit.Material;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParticlesTest {

    private static ParticleNativeAPI api_1_7;
    private static ParticleNativeAPI api_1_8;
    private static ParticleNativeAPI api_1_13;
    private static ParticleNativeAPI api_1_15;
    private static ParticleNativeAPI api_1_17;
    private static ParticleNativeAPI api_1_18;
    private static ParticleNativeAPI api_1_19;

    @BeforeAll
    public static void prepareAPI() {
        api_1_7 = ParticleNativeCoreTest.getAPI_1_7();
        api_1_8 = ParticleNativeCoreTest.getAPI_1_8();
        api_1_13 = ParticleNativeCoreTest.getAPI_1_13();
        api_1_15 = ParticleNativeCoreTest.getAPI_1_15();
        api_1_17 = ParticleNativeCoreTest.getAPI_1_17();
        api_1_18 = ParticleNativeCoreTest.getAPI_1_18();
        api_1_19 = ParticleNativeCoreTest.getAPI_1_19();
    }

    @SuppressWarnings("deprecation")
    private void tryInvokePacketAndAssertException(String getter, ParticleNativeAPI api,
                                                   Object particleType) {
        Material material = Material.DIAMOND_BLOCK;
        if (api == api_1_7 || api == api_1_8) {
            material = Material.LEGACY_DIAMOND_BLOCK;
        }

        try {
            if (particleType instanceof ParticleType) {
                ((ParticleType) particleType).packet(true, 0D, 0D, 0D);
            }
            else if (particleType instanceof ParticleTypeBlock) {
                ((ParticleTypeBlock) particleType).of(material, 0);
            }
            else if (particleType instanceof ParticleTypeBlockMotion) {
                ((ParticleTypeBlockMotion) particleType).of(material, 0);
            }
            else if (particleType instanceof ParticleTypeItemMotion) {
                ((ParticleTypeItemMotion) particleType).of(material);
            }
            else if (particleType instanceof ParticleTypeDust) {
                ((ParticleTypeDust) particleType).color(255F, 0F, 0F, 2F);
            }
            else if (particleType instanceof ParticleTypeDustColorTransition) {
                ((ParticleTypeDustColorTransition) particleType).color(255F, 0F, 0F, 255F, 0F, 0F, 2F);
            }
            else if (particleType instanceof ParticleTypeVibration) {
                ((ParticleTypeVibration) particleType).packet(true, 0D, 0D, 0D, 0D, 0D, 0D, 1);
            }
            else if (particleType instanceof ParticleTypeSculkChargeMotion) {
                ((ParticleTypeSculkChargeMotion) particleType).roll(16D);
            }
            else if (particleType instanceof ParticleTypeShriek) {
                ((ParticleTypeShriek) particleType).delay(16);
            }
            else {
                fail("Unknown particle type: " + getter);
            }
        } catch (ParticleException e) {
            return;
        }
        fail("Expected ParticleException on " + getter + ", but it wasn't thrown");
    }

    private void verify_Particles_1_8_thatOnlyThoseAreValid(ParticleNativeAPI api,
                                                            String... particleNames)
            throws ReflectiveOperationException {
        List<String> particleNamesList = Arrays.asList(particleNames);

        for (Method getter : Particles_1_8.class.getDeclaredMethods()) {
            Object particleType = getter.invoke(api.getParticles_1_8());

            boolean expected = particleNamesList.contains(getter.getName());

            boolean actual = (boolean) particleType.getClass().getMethod("isValid")
                    .invoke(particleType);

            assertEquals(expected, actual, "Wrong particle type valid status of "
                    + getter.getName() + " in Particles_1_8");

            if (!actual) {
                tryInvokePacketAndAssertException(getter.getName(), api, particleType);
            }
        }
    }
    private void verify_Particles_1_13_thatOnlyThoseAreValid(ParticleNativeAPI api,
                                                             String... particleNames)
            throws ReflectiveOperationException {
        List<String> particleNamesList = Arrays.asList(particleNames);

        for (Method getter : Particles_1_13.class.getDeclaredMethods()) {
            Object particleType = getter.invoke(api.getParticles_1_13());

            boolean expected = particleNamesList.contains(getter.getName());

            boolean actual = (boolean) particleType.getClass().getMethod("isValid")
                    .invoke(particleType);

            assertEquals(expected, actual, "Wrong particle type valid status of "
                    + getter.getName() + " in Particles_1_13");

            if (!actual) {
                tryInvokePacketAndAssertException(getter.getName(), api, particleType);
            }
        }
    }

    @Test
    public void test_isValid_1_7() throws ReflectiveOperationException {
        verify_Particles_1_8_thatOnlyThoseAreValid(api_1_7,
                "EXPLOSION_NORMAL",
                "EXPLOSION_LARGE",
                "EXPLOSION_HUGE",
                "FIREWORKS_SPARK",
                "WATER_BUBBLE",
                "WATER_SPLASH",
                "WATER_WAKE",
                "SUSPENDED",
                "SUSPENDED_DEPTH",
                "CRIT",
                "CRIT_MAGIC",
                "SMOKE_NORMAL",
                "SMOKE_LARGE",
                "SPELL",
                "SPELL_INSTANT",
                "SPELL_MOB",
                "SPELL_MOB_AMBIENT",
                "SPELL_WITCH",
                "DRIP_WATER",
                "DRIP_LAVA",
                "VILLAGER_ANGRY",
                "VILLAGER_HAPPY",
                "TOWN_AURA",
                "NOTE",
                "PORTAL",
                "ENCHANTMENT_TABLE",
                "FLAME",
                "LAVA",
                "FOOTSTEP",
                "CLOUD",
                "REDSTONE",
                "SNOWBALL",
                "SNOW_SHOVEL",
                "SLIME",
                "HEART",
                "ITEM_CRACK",
                "BLOCK_CRACK",
                "BLOCK_DUST"
        );

        verify_Particles_1_13_thatOnlyThoseAreValid(api_1_7,
                "POOF",
                "EXPLOSION",
                "EXPLOSION_EMITTER",
                "FIREWORK",
                "BUBBLE",
                "SPLASH",
                "FISHING",
                "UNDERWATER",
                "CRIT",
                "ENCHANTED_HIT",
                "SMOKE",
                "LARGE_SMOKE",
                "EFFECT",
                "INSTANT_EFFECT",
                "ENTITY_EFFECT",
                "AMBIENT_ENTITY_EFFECT",
                "WITCH",
                "DRIPPING_WATER",
                "DRIPPING_LAVA",
                "ANGRY_VILLAGER",
                "HAPPY_VILLAGER",
                "MYCELIUM",
                "NOTE",
                "PORTAL",
                "ENCHANT",
                "FLAME",
                "LAVA",
                "CLOUD",
                "ITEM_SNOWBALL",
                "POOF",
                "ITEM_SLIME",
                "HEART",
                "ITEM",
                "BLOCK"
        );
    }

    @Test
    public void test_isValid_1_8() throws ReflectiveOperationException {
        verify_Particles_1_8_thatOnlyThoseAreValid(api_1_8,
                "BARRIER",
                "FALLING_DUST",
                "BLOCK_CRACK",
                "SPELL_MOB",
                "ITEM_CRACK",
                "FLAME",
                "NOTE",
                "REDSTONE"
        );

        verify_Particles_1_13_thatOnlyThoseAreValid(api_1_8,
                "BARRIER",
                "FALLING_DUST",
                "BLOCK",
                "ENTITY_EFFECT",
                "ITEM",
                "FLAME",
                "NOTE"
        );
    }

    @Test
    public void test_isValid_1_13() throws ReflectiveOperationException {
        verify_Particles_1_8_thatOnlyThoseAreValid(api_1_13,
                "BARRIER",
                "FALLING_DUST",
                "BLOCK_CRACK",
                "BLOCK_DUST",
                "SPELL_MOB",
                "ITEM_CRACK",
                "FLAME",
                "NOTE",
                "REDSTONE"// forward compatibility
        );

        verify_Particles_1_13_thatOnlyThoseAreValid(api_1_13,
                "BARRIER",
                "FALLING_DUST",
                "BLOCK",
                "ENTITY_EFFECT",
                "ITEM",
                "FLAME",
                "NOTE",
                "DUST"
        );
    }

    @Test
    public void test_isValid_1_15() throws ReflectiveOperationException {
        verify_Particles_1_8_thatOnlyThoseAreValid(api_1_15,
                "BARRIER",
                "FALLING_DUST",
                "BLOCK_CRACK",
                "BLOCK_DUST",
                "SPELL_MOB",
                "ITEM_CRACK",
                "FLAME",
                "NOTE",
                "REDSTONE"// forward compatibility
        );

        verify_Particles_1_13_thatOnlyThoseAreValid(api_1_15,
                "BARRIER",
                "FALLING_DUST",
                "BLOCK",
                "ENTITY_EFFECT",
                "ITEM",
                "FLAME",
                "NOTE",
                "DUST"
        );
    }

    @Test
    public void test_isValid_1_17() throws ReflectiveOperationException {
        verify_Particles_1_8_thatOnlyThoseAreValid(api_1_17,
                "BARRIER",
                "FALLING_DUST",
                "BLOCK_CRACK",
                "BLOCK_DUST",
                "SPELL_MOB",
                "ITEM_CRACK",
                "FLAME",
                "NOTE",
                "REDSTONE"// forward compatibility
        );

        verify_Particles_1_13_thatOnlyThoseAreValid(api_1_17,
                "BARRIER",
                "FALLING_DUST",
                "BLOCK",
                "ENTITY_EFFECT",
                "ITEM",
                "FLAME",
                "NOTE",
                "DUST",
                "DUST_COLOR_TRANSITION",
                "VIBRATION"
        );
    }

    @Test
    public void test_isValid_1_18() throws ReflectiveOperationException {
        verify_Particles_1_8_thatOnlyThoseAreValid(api_1_18,
                "BARRIER",
                "HEART",
                "FALLING_DUST",
                "BLOCK_CRACK",
                "BLOCK_DUST",
                "SPELL_MOB",
                "ITEM_CRACK",
                "FLAME",
                "NOTE",
                "REDSTONE"// forward compatibility
        );

        verify_Particles_1_13_thatOnlyThoseAreValid(api_1_18,
                "BARRIER",// forward compatibility
                "LIGHT",// forward compatibility
                "HEART",
                "FALLING_DUST",
                "BLOCK",
                "ENTITY_EFFECT",
                "ITEM",
                "FLAME",
                "NOTE",
                "DUST",
                "DUST_COLOR_TRANSITION",
                "VIBRATION",
                "BLOCK_MARKER"
        );
    }

    @Test
    public void test_isValid_1_19() throws ReflectiveOperationException {
        verify_Particles_1_8_thatOnlyThoseAreValid(api_1_19,
                "BARRIER",
                "HEART",
                "FALLING_DUST",
                "BLOCK_CRACK",
                "BLOCK_DUST",
                "SPELL_MOB",
                "ITEM_CRACK",
                "FLAME",
                "NOTE",
                "REDSTONE"// forward compatibility
        );

        verify_Particles_1_13_thatOnlyThoseAreValid(api_1_19,
                "BARRIER",// forward compatibility
                "LIGHT",// forward compatibility
                "HEART",
                "FALLING_DUST",
                "BLOCK",
                "ENTITY_EFFECT",
                "ITEM",
                "FLAME",
                "NOTE",
                "DUST",
                "DUST_COLOR_TRANSITION",
                "VIBRATION",
                "BLOCK_MARKER",
                "SCULK_CHARGE",
                "SHRIEK"
        );
    }

}
