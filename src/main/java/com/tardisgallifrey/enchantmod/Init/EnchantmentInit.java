package com.tardisgallifrey.enchantmod.Init;

import com.tardisgallifrey.enchantmod.EnchantModMain;
import com.tardisgallifrey.enchantmod.enchants.BridgeEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS,
                    EnchantModMain.MOD_ID);

    public static final RegistryObject<Enchantment> BRIDGE =
            ENCHANTMENTS.register("bridge",
                    BridgeEnchantment::new);
}
