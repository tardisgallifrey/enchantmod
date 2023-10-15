package com.tardisgallifrey.enchantmod.Init;

import com.tardisgallifrey.enchantmod.EnchantModMain;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS,
                    EnchantModMain.MOD_ID);

    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)){
            BLOCKS.getEntries().forEach( (blockRegistryObject) -> {
                Block block = blockRegistryObject.get();
                Item.Properties properties =
                        new Item.Properties()
                                .tab(ItemInit
                                        .ModCreativeTab
                                        .instance);
                Supplier<Item> blockItemFactory =
                        () -> new BlockItem(block, properties);
                event.register(ForgeRegistries.Keys.ITEMS,
                        blockRegistryObject.getId(),
                        blockItemFactory);
            });
        }
    }


}
