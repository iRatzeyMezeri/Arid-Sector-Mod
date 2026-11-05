package aridmod.content;

import mindustry.content.Blocks;
import aridmod.content.AridSectorBlocks;
import mindustry.world.meta.*;

public class AridSectorAttributes {
    public static void load(){
        Attribute.add("clay-concentration");
    }

    public static void setAttributes(){
        AridSectorBlocks.clayWall.attributes.set(Attribute.get("clay-concentration"), 2);
        Blocks.redStoneWall.attributes.set(Attribute.get("clay-concentration"), 1.8f);
        AridSectorBlocks.flintWall.attributes.set(Attribute.get("clay-concentration"), 1.5f);
        AridSectorBlocks.navystoneWall.attributes.set(Attribute.get("clay-concentration"), 0f);
    }
}