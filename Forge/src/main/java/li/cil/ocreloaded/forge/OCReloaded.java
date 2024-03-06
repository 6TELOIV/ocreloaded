package li.cil.ocreloaded.forge;


import li.cil.ocreloaded.minecraft.OCReloadedCommon;
import li.cil.ocreloaded.minecraft.block.BlockInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(OCReloadedCommon.MOD_ID)
public class OCReloaded {

    private final OCReloadedCommon common = new OCReloadedCommon();

    public OCReloaded() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void register(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.BLOCKS, this::registerBlocks);
        event.register(ForgeRegistries.Keys.ITEMS, this::registerBlockItems);
    }

    private void registerBlocks(RegisterEvent.RegisterHelper<Block> helper) {
        for (BlockInfo blockInfo : common.getBlockInfos()) {
            ResourceLocation blockResource = new ResourceLocation(OCReloadedCommon.MOD_ID, blockInfo.blockName());
            helper.register(blockResource, convertToBlock(blockInfo));
        }
    }

    private void registerBlockItems(RegisterEvent.RegisterHelper<Item> helper) {
        for (BlockInfo blockInfo : common.getBlockInfos()) {
            ResourceLocation blockResource = new ResourceLocation(OCReloadedCommon.MOD_ID, blockInfo.blockName());
            Block block = ForgeRegistries.BLOCKS.getValue(blockResource);
            helper.register(blockResource, new BlockItem(block, new Item.Properties()));
        }
    }

    private Block convertToBlock(BlockInfo blockInfo) {
        return new Block(BlockBehaviour.Properties.of());
    }
}
